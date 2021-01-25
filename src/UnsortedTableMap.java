import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of Map structure based on ArrayLists in an unsorted constructor
 * @param <K> key element
 * @param <V> value element
 */
public class UnsortedTableMap<K, V> extends AbstractMap<K, V> {
    private ArrayList<MapEntry<K, V>> table = new ArrayList<>();

    /**
     * Default constructor
     */
    public UnsortedTableMap() {}

    /**
     * Checks a key if the entry with this key is already in the map.
     * @param key to be checked
     * @return -1 if there is no entry with the given key, and the index number of the entry if there is
     */
    private int findIndex(K key) {
        int n = table.size();
        for (int i = 0; i < n; i++)
            if (table.get(i).getKey().equals(key))
                return i;
            return -1;
    }

    /**
     * @return gives the count of the entries in the map
     */
    public int size() {return table.size(); }

    /**
     * Gives the value of the entry with the given key
     * @param key element of the entry
     * @return value element of the entry
     */
    public V get(K key) {
        int i = findIndex(key);
        if (i == -1)
            return null;
        return table.get(i).getValue();
    }

    /**
     * Adds an entry with the given key-value pair if there is no already an entry with the key,
     * otherwise updates the value of the entry with the given key and return the old value.
     * @param key key of the entry to be added (or updated if already exists)
     * @param value value of the entry to be added (or updated if already exists)
     * @return null if there is no already entry with the given key, otherwise the old value of the entry.
     */
    public V put(K key, V value) {
        int i = findIndex(key);
        if (i == -1) {
            table.add(new MapEntry<>(key, value));
            return null;
        } else
            return table.get(i).setValue(value);
    }

    /**
     * Remove the entry with the given key from the map.
     * @param key of the entry to be removed
     * @return the value of the removed entry, null if there is no such entry with the given key
     */
    public V remove(K key) {
        int i = findIndex(key);
        int n = size();
        if (i == -1) return null;
        V removed = table.get(i).getValue();
        if (i != n -1)
            table.set(i, table.get(n - 1));
        table.remove(n - 1);
        return removed;
    }

    /**
     * Supporting classes for creating iterators.
     */
    private class EntryIterator implements Iterator<Entry<K, V>> {
        private int i = 0;
        public boolean hasNext() {return i < table.size(); }
        public Entry<K, V> next() {
            if (i == table.size()) throw new NoSuchElementException();
            return table.get(i++);
        }

        /**
         * The remove method of the interface is not supported.
         */
        public void remove() {throw new UnsupportedOperationException(); }
    }

    private class EntryIterable implements Iterable<Entry<K, V>> {
        public Iterator<Entry<K, V>> iterator() {return new EntryIterator(); }
    }

    /**
     * Gives an Iterable of entries based on a Set structure.
     * @return iterable of entries
     */
    public Iterable<Entry<K, V>> entrySet() {return new EntryIterable(); }
}
