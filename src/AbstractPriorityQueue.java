import java.util.Comparator;

/**
 * The AbstractPriorityQueue class. This provides a nested PQEntry class that composes
 * a key and a value into a single object, and support for managing a comparator. The isEmpty method
 * is also implemented.
 * @param <K> key element
 * @param <V> value element
 */
public abstract class AbstractPriorityQueue<K, V> implements PriorityQueue<K, V> {
    /*-------------------Nested PQEntry class------------------*/
    /**
     * Implementation of Entry interface with additional setters
     * @param <K> key
     * @param <V> value
     */
    protected static class PQEntry<K, V> implements Entry<K, V> {
        private K k;
        private V v;

        /**
         * Constructor of an entry object
         * @param key element
         * @param value element
         */
        public PQEntry(K key, V value){
            k = key;
            v = value;
        }

        /**
         *
         * @return the key element of the entry
         */
        public K getKey() {return k; }

        /**
         *
         * @return the value element of the entry
         */
        public V getValue() {return v;}

        /**
         * update the key element of this entry
         * @param key new key element
         */
        protected void setKey(K key) {k = key;}

        /**
         * update teh value element of this entry
         * @param value new value element
         */
        protected void setValue(V value) {v = value;}
    } /*----------End od Nested PQEntry class----------------------*/

    /**
     * instance variable which is a comparator to define the ordering of keys in the priority queue
     */
    private Comparator<K> comp;

    /**
     * Creates an empty priority queue using the given comparator c to order the keys
     * @param c comparator
     */
    protected AbstractPriorityQueue(Comparator<K> c) {comp =  c; }

    /**
     * Creates an empty priority queue using the natural ordering of the keys to order them
     */
    protected AbstractPriorityQueue() {this(new DefaultComparator<K>());}

    /**
     * Method to order the entries according to their keys
     * @param a first entry
     * @param b second entry
     * @return -1 if a is less (or before), 0 if a == b and 1 if a is greater or (after)
     */
    protected int compare(Entry<K, V> a, Entry<K, V> b){
        return comp.compare(a.getKey(), b.getKey());
    }

    /**
     * Method to check if a key type is valid
     * @param key element to be compared to itself
     * @return true if the key is a valid key
     * @throws IllegalArgumentException if the key is not valid
     */
    protected boolean checkKey(K key) throws IllegalArgumentException {
        try {
            return(comp.compare(key, key) == 0);
        } catch (ClassCastException ex) {
            throw new IllegalArgumentException("Incompatible key");
        }
    }

    /**
     * @return true if this priority queue is empty.
     */
    public boolean isEmpty() {return size() == 0;}

}
