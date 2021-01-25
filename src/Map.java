/**
 * The implementation of a simplified Map interface rom the textbook
 * @param <K> key element
 * @param <V> value element
 */
public interface Map<K, V> {

    /**
     * @return count of entries in the Map
     */
    int size();

    /**
     * Tests if the Map contains no entries
     * @return true if the map stores no entries
     */
    boolean isEmpty();

    /**
     * Gives the value of the entry whose key element is the given key
     * @param key element of the entry
     * @return value of the element with the key of the given key
     */
    V get(K key);

    /**
     * Insert an entry with properties key-value pair and return null if an entry with this key
     * doesn't already exists, update its value if exists and return the old value.
     * @param key key of the entry to be added (or updated if already exists)
     * @param value value of the entry to be added (or updated if already exists)
     * @return null if an entry with this key is not exists, return the old value if exists
     */
    V put(K key, V value);

    /**
     * Remove the entry  with the given key and return its value
     * @param key of the entry to be removed
     * @return the value of the removed entry
     */
    V remove(K key);

    /**
     * Return an iterable set of keys of the entries in the map
     * @return Set that contains the keys of the Map
     */
    Iterable<K> keySet();

    /**
     * @return an iterable collection of the values of the entries stored in this Map
     */
    Iterable<V> values();

    /**
     * @return Set of Entries stored in this Map
     */
    Iterable<Entry<K, V>> entrySet();
}
