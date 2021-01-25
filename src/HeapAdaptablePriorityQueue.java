import java.util.Comparator;

/**
 * An implementation of an adaptable priority queue extends HeapPriorityQueue class
 * which is based on array
 * @param <K> key element
 * @param <V> value element
 */
public class HeapAdaptablePriorityQueue<K, V> extends HeapPriorityQueue<K, V> implements AdaptablePriorityQueue<K, V> {

    /*-----------------Nested AdaptablePQEntry class----------------*/

    /**
     * Extended PQEntry class to include location information. In this version every entry
     * has an additional index property.
     * @param <K> key element
     * @param <V> value element
     */
    protected static class AdaptablePQEntry<K, V> extends PQEntry<K, V> {
        private int index;
        public AdaptablePQEntry(K key, V value, int j){
            super(key, value);
            index = j;
        }

        /**
         * @return int index number of this entry
         */
        public int getIndex() {return index;}

        /**
         * Updates the index number of this entry
         * @param j int new value of the index
         */
        public void setIndex(int j) {index = j; }
    } /*----------------End of HeapAdaptablePQEntry class-------------------*/

    /**
     * Creates an empty adaptable priority queue using natural ordering of keys
     */
    public HeapAdaptablePriorityQueue() {super(); }

    /**
     * Creates an empty adaptable priority queue using the given comparator to order keys
     * @param comp Comparator object
     */
    public HeapAdaptablePriorityQueue(Comparator<K> comp) {super(comp); }

    //Protected utilities...

    /**
     * Validates an entry to ensure it is an object of AdaptablePQEntry which is a location-aware
     * entry, it has an index property, and validates the index if it is a correct index.
     * @param entry Entry to be validated
     * @return AdaptablePQEntry object if the given Entry is valid.
     * @throws IllegalArgumentException if the given Entry is invalid.
     */
    protected AdaptablePQEntry<K, V> validate(Entry<K, V> entry) throws IllegalArgumentException {
        if (!(entry instanceof AdaptablePQEntry))
            throw new IllegalArgumentException("Invalid entry");
        AdaptablePQEntry<K, V> locator = (AdaptablePQEntry<K, V>) entry;
        int j = locator.getIndex();
        if (j >= heap.size() || heap.get(j) != locator)
            throw new IllegalArgumentException("Invalid entry");
        return locator;
    }

    /**
     * Swap the positions of the Entries in the given indexes and updates their index properties
     * @param i int index of a node
     * @param j int index of other node
     */
    protected void swap(int i, int j) {
        super.swap(i, j);
        ((AdaptablePQEntry<K, V>) heap.get(i)).setIndex(i);
        ((AdaptablePQEntry<K, V>) heap.get(j)).setIndex(j);
    }

    /**
     * Restores the heap property up-heaping or down-heaping the entry at index j
     * @param j index of the entry to be bubbled
     */
    protected void bubble(int j) {
        if (j > 0 && compare(heap.get(j), heap.get(parent(j))) < 0)
            upHeap(j);
        else downHeap(j);
    }

    /**
     * Insert an entry created from key-value pair and return the this entry
     * @param key of the entry to be inserted
     * @param value of the entry to be inserted
     * @return newly inserted entry
     * @throws IllegalArgumentException if key is invalid
     */
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K, V> newest = new AdaptablePQEntry<>(key,value, heap.size());
        heap.add(newest);
        upHeap(heap.size() - 1);
        return newest;
    }

    /**
     * Remove from the priority queue and return the given entry
     * @param entry to be removed and returned
     * @throws IllegalArgumentException if the entry is invalid i.e. already removed before
     */
    public void remove(Entry<K, V> entry) throws IllegalArgumentException {
        AdaptablePQEntry<K, V> locator = validate(entry);
        int j = locator.getIndex();
        if (j == heap.size() - 1)
            heap.remove(heap.size() - 1);
        else {
            swap(j, heap.size() - 1);
            heap.remove(heap.size() - 1);
            bubble(j);
        }
    }

    /**
     * Change the key of an entry with the given key.
     * @param entry whose key is to be updated
     * @param key the new key
     * @throws IllegalArgumentException if the key or the entry is invalid.
     */
    public void replaceKey(Entry<K, V> entry, K key) throws IllegalArgumentException {
        AdaptablePQEntry<K, V> locator = validate(entry);
        checkKey(key);
        locator.setKey(key);
        bubble(locator.getIndex());
    }

    /**
     * Change the value element of the given entry with the given value.
     * @param entry whode value element to be updated
     * @param value the new value element which is going to take place of the current one.
     * @throws IllegalArgumentException  if the entry is invalid.
     */
    public void replaceValue(Entry<K, V> entry, V value) throws IllegalArgumentException {
        AdaptablePQEntry<K, V> locator = validate(entry);
        locator.setValue(value);
    }
}
