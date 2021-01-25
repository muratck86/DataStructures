import java.util.ArrayList;
import java.util.Comparator;

/**
 * An implementation of the SortedTableMap class. The AbstractSortedMap base class
 * provides the utility method, compare, based on a given comparator.
 * @param <K> key element of entry
 * @param <V> value element of entry
 */
public class SortedTableMap<K, V> extends AbstractSortedMap<K, V>  {
    private ArrayList<MapEntry<K,V>> table = new ArrayList<>(); //the table is based on ArrayList
    public SortedTableMap() {super(); }

    /**
     * Constructor, create an empty SortedTableMap using a given comparator to order keys.
     * @param comp comparator
     */
    public SortedTableMap(Comparator<K> comp) {super(comp); } //Order keys using the given comparator

    /**
     * Gives the least index for range low-high (inclusive) storing an entry with a key
     * greater than or equal to k or else index high + 1
     * @param key K element of entry
     * @param low start index of searching
     * @param high end index of searching
     * @return int index of entry with key or  high + 1 if does not find the key.
     */
    private int findIndex(K key, int low, int high) {
        if (high < low) return high + 1;
        int mid = (low + high) / 2;
        int comp = compare(key, table.get(mid));
        if (comp == 0) return mid;
        else if (comp < 0) return findIndex(key, low, mid - 1);
        else return findIndex(key, mid + 1, high);
    }

    /**
     * Another version of findIndex method that searches the entire table.
     * @param key of the entity to search
     * @return index number
     */
    private int findIndex(K key) {return findIndex(key, 0, table.size() - 1); }

    /**
     * @return the number of the entries int the map
     */
    public int size() {return table.size(); }

    /**
     * Gives the value of the entry with the given key if it exists and gives null otherwise.
     * @param key element of the entry
     * @return value of the entry
     */
    public V get(K key) {
        int i = findIndex(key);
        if (i == size() || compare(key, table.get(i)) != 0) return null;
        return table.get(i).getValue();
    }

    /**
     * Insert an entry with the given key-value pair and return null if there is no
     * entry with this key. Otherwise, update the value of the entry with this key and return the old value.
     * @param key key of the entry to be added (or updated if already exists)
     * @param value value of the entry to be added (or updated if already exists)
     * @return old value of the existing entry or null.
     */
    public V put(K key, V value) {
        int i = findIndex(key);
        if (i < size() && compare(key, table.get(i)) == 0) return table.get(i).setValue(value);
        table.add(i, new MapEntry<K, V>(key, value));
        return null;
    }

    /**
     * Removes the entry having key k (if any) and returns its associated value.
     * @param key of the entry to be removed
     * @return value of the removed entry or null (if there is no such entry)
     */
    public V remove(K key) {
        int i = findIndex(key);
        if (i == size() || compare(key, table.get(i)) != 0) return null;
        return table.remove(i).getValue();
    }

    /**
     * Utility returns the entry at index j, or else null if j is out of bounds.
     * @param i index of the entry to return.
     * @return Entry with the given index or null if the index is greater than or equal to the size of the table
     */
    private Entry<K, V> safeEntry(int i) {
        if (i < 0 || i >= table.size()) return null;
        return table.get(i);
    }

    /**
     * Returns the entry having the least key (or null if map is empty).
     * @return Entry having the least key.
     */
    public Entry<K, V> firstEntry() {return safeEntry(0); }

    /**
     * Returns the entry having the greatest key (or null if map is empty).
     * @return Entry having the greatest key.
     */
    public Entry<K, V> lastEntry() {return safeEntry(table.size() - 1); }

    /**
     * Returns the entry with least key greater than or equal to given key(if any)
     * @param key the key of the entry to compare
     * @return the entry which has the minimum key greater than or equal to the given key
     */
    public Entry<K, V> ceilingEntry(K key) {return safeEntry(findIndex(key)); }

    /**
     *Returns the entry with greatest key less than or equal to given key (if any).
     * @param key the key of the entry to compare
     * @return the entry which has the max key less than or equal to the given key.
     */
    public Entry<K, V> floorEntry(K key) {
        int i = findIndex(key);
        if (i == size() || !key.equals(table.get(i).getKey()))
            i--;
        return safeEntry(i);
    }

    /**
     * Returns the entry with greatest key strictly less than given key (if any).
     * @param key the key of the entry to compare
     * @return the entry which has the max key less than the given key.
     */
    public Entry<K, V> lowerEntry(K key) {return safeEntry(findIndex(key) - 1); }

    /**
     * Returns the entry with least key strictly greater than given key (if any).
     * @param key the key of the entry to compare
     * @return the entry which has the min key greater than the given key.
     */
    public Entry<K, V> higherEntry(K key) {
        int i = findIndex(key);
        if (i < size() && key.equals(table.get(i).getKey()))
            i++;
        return safeEntry(i);
    }

    // support for snapshot iterators for entrySet() and subMap() follow

    /**
     * Gives an iterable snapshot of the entries stored in the table between the given indexes
     * @param startIndex starting index of the iterable
     * @param stop end index of the iterable
     * @return an iterable snapshot of the table.
     */
    private Iterable<Entry<K, V>> snapshot(int startIndex, K stop) {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();
        int i = startIndex;
        while (i < table.size() && (stop == null || compare(stop, table.get(i)) > 0))
            buffer.add(table.get(i++));
        return buffer;
    }

    /**
     * Gives an iterable set of all the entries created by the snapshot() method.
     * @return iterable set of entries.
     */
    public Iterable<Entry<K, V>> entrySet() {return snapshot(0, null); }

    /**
     * Gives an iterable set of entries between fromKey-toKey created by the snapshot() method.
     * @param fromKey starting key value
     * @param toKey ending key value
     * @return an iterable sub-set of entries between the given keys.
     */
    public Iterable<Entry<K, V>> subMap(K fromKey, K toKey) {return snapshot(findIndex(fromKey), toKey); }
}
