/**
 * Interface for the priority queue ADT
 * @param <K> type of key
 * @param <V> type of value
 */
public interface PriorityQueue<K, V> {
    /**
     * @return int count of entries
     */
    int size();

    /**
     * @return true if this priority queue is empty (size == 0)
     */
    boolean isEmpty();

    /**
     * Insert an entry to this priority queue
     * @param key of the entry to be inserted
     * @param value of the entry to be inserted
     * @return the entry inserted to the priority queue
     * @throws IllegalArgumentException if the key-value pair is not valid
     */
    Entry<K, V> insert(K key, V value) throws IllegalArgumentException;

    /**
     * Gives bu not removes the entry
     * @return the entry with the minimum key
     */
    Entry<K, V> min();

    /**
     *
     * @return and remove the entry with the minimum key
     */
    Entry<K, V> removeMin();
}
