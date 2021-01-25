import java.util.Comparator;

/**
 * An implementation of a priority queue using an unsorted list. Based on the
 * LinkedPositionalList structure as inner collection.
 * @param <K> key element
 * @param <V> value element
 */
public class UnsortedPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {
    /*primary collection of the entries*/
    private LinkedPositionalList<Entry<K, V>> list = new LinkedPositionalList<>();

    /**
     * Constructor with natural ordering
     */
    public UnsortedPriorityQueue() {super();}

    /**
     * Constructor with a given comparator to order keys
     * @param c Comparator object.
     */
    public UnsortedPriorityQueue(Comparator<K> c) {super(c);}

    /**
     * Gives the Position of the entry whose key is the minimum.
     * @return the Position of the entry with the minimum key
     */
    private Position<Entry<K, V>> findMin(){
        Position<Entry<K, V>> small = list.first();
        for (Position<Entry<K, V>> walk: list.positions()){
            if (compare(walk.getElement(), small.getElement()) < 0)
                small = walk;
        }
        return small;
    }

    /**
     * Create an Entry with the given values and insert it to the last position of the priority queue
     * @param key of the entry to be inserted
     * @param value of the entry to be inserted
     * @return the entry inserted
     * @throws IllegalArgumentException if the key is invalid
     */
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K, V> newest = new PQEntry<>(key, value);
        list.addLast(newest);
        return newest;
    }

    /**
     * Gives the entry whose key is the minimum.
     * @return the entry whose key is the minimum.
     */
    public Entry<K, V> min() {
        if (list.isEmpty())
            return null;
        return findMin().getElement();
    }

    /**
     * Gives and removes the entry whose key is the minimum.
     * @return the entry with the minimum key.
     */
    public Entry<K, V> removeMin() {
        if (list.isEmpty())
            return null;
        return list.remove(findMin());
    }

    /**
     * Gives the count of entries stored in the priority queue
     * @return int count of the entries
     */
    public int size() {return list.size();}
}
