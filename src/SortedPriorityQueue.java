import java.util.Comparator;

/**
 * An implementation of a priority queue using an unsorted list. Based on the
 * LinkedPositionalList structure as inner collection.
 * @param <K> key element
 * @param <V> value element
 */
public class SortedPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {
    /*primary collection of the entries*/
    private PositionalList<Entry<K, V>> list = new LinkedPositionalList<>();

    /**
     * Constructor with natural ordering
     */
    public SortedPriorityQueue() {super();}

    /**
     * Constructor with a given comparator to order keys
     * @param c Comparator object.
     */
    public SortedPriorityQueue(Comparator<K> c) {super(c);}

    /**
     * Create an Entry with the given values and insert it to the priority queue in respect to the
     * ordering.
     * @param key of the entry to be inserted
     * @param value of the entry to be inserted
     * @return the entry inserted
     * @throws IllegalArgumentException if the key is invalid
     */
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K, V> newest = new PQEntry<>(key, value);
        Position<Entry<K, V>> walk = list.last();

        while (walk != null && compare(newest, walk.getElement()) < 0)
            walk = list.before(walk);
        if (walk == null)
            list.addFirst(newest);
        else list.addAfter(walk, newest);
        return newest;
    }

    /**
     * Gives the entry whose key is the minimum. In this implementation this is always the first
     * position.
     * @return the entry whose key is the minimum.
     */
    public Entry<K, V> min() {
        if (list.isEmpty())
            return null;
        return list.first().getElement();
    }

    /**
     * Gives and removes the entry whose key is the minimum.
     * @return the entry with the minimum key.
     */
    public Entry<K, V> removeMin() {
        if (list.isEmpty())
            return null;
        return list.remove(list.first());
    }

    /**
     * Gives the count of entries stored in the priority queue
     * @return int count of the entries
     */
    public int size() {
        return list.size();
    }
}
