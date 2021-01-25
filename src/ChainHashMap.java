import java.util.ArrayList;

/**
 * A hash map implementation using separate chaining. In this implementation every cell
 * of the table and the table itself is also are UnsortedTableMap.
 * @param <K> key element of the entries
 * @param <V> value element of the entries
 */
public class ChainHashMap<K, V> extends AbstractHashMap<K, V> {
    private UnsortedTableMap<K, V>[] table;
    public ChainHashMap() {super(); }
    public ChainHashMap(int cap) {super(cap); }
    public ChainHashMap(int cap, int p) {super(cap, p); }

    /**
     * Create the main table of the hash map
     */
    protected void createTable() {
        table = (UnsortedTableMap<K, V>[]) new UnsortedTableMap[capacity];
    }

    /**
     * Gives the entry with the hash code of h and key element of k, uses the
     * get() method of the underlying UnsortedTableMap
     * @param h int hash code of the entry
     * @param k key element of the entry
     * @return V value of the entry
     */
    protected V bucketGet(int h, K k) {
        UnsortedTableMap<K, V> bucket = table[h];
        if (bucket == null) return null;
        return bucket.get(k);
    }

    /**
     * Insert an entry to the hash map using the given hash code, key  and value
     * If there is an entry with the given hash code and key already exists, then update its value
     * This method uses the put() method of the underlying UnsortedTableMap structure and
     * re-calculates the size of the table.
     * @param h int hash code of the new entry
     * @param k K key element of the new entry
     * @param v V value element of the new entry, or the new value of the existing entry
     * @return null or the old value element of the existing entry
     */
    protected V bucketPut(int h, K k, V v) {
        UnsortedTableMap<K, V> bucket = table[h];
        if (bucket == null)
            bucket = table[h] = new UnsortedTableMap<>();
        int oldSize = bucket.size();
        V oldValue = bucket.put(k, v);
        n += (bucket.size() - oldSize);
        return oldValue;
    }

    /**
     * Remove an entry from the hash map using the remove() method of the underlying
     * UnsortedTableMap and re-calculate the size of the main table.
     * @param h int hash code of the entry to be removed
     * @param k K key element of the entry to be removed
     * @return V value element of the removed entry
     */
    protected V bucketRemove(int h, K k) {
        UnsortedTableMap<K, V> bucket = table[h];
        if (bucket == null) return null;
        int oldSize = bucket.size();
        V removed = bucket.remove(k);
        n -= (oldSize - bucket.size());
        return removed;
    }

    /**
     * Return an iterable set of entries
     * @return Iterable set of entries
     */
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();
        for (int h = 0; h < capacity; h++)
            if (table[h] != null)
                for (Entry<K, V> entry : table[h].entrySet())
                    buffer.add(entry);
        return buffer;
    }
}
