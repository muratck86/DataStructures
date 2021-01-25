import java.util.ArrayList;

/**
 * ProbeHashMap class that uses linear probing for collision resolution
 * This class has array of Entry underlying data structure.
 * @param <K> key element of the entry
 * @param <V> value element of the entry
 */
public class ProbeHashMap<K, V> extends AbstractHashMap<K, V> {
    private MapEntry<K, V>[] table; //declare the array which is the main table
    private MapEntry<K, V> DEFUNCT = new MapEntry<>(null, null);

    /*Constructors*/
    public ProbeHashMap() {super(); }
    public ProbeHashMap(int cap) {super(cap); }
    public ProbeHashMap(int cap, int p) {super(cap, p); }

    /**
     * Create the main table which is an array of entries
     */
    protected void createTable() {
        table = (MapEntry<K, V>[]) new MapEntry[capacity];
    }

    /**
     * Checks an index of the table if it is null or defunct
     * @param i int index to test
     * @return true if the cell/slot is available
     */
    private boolean isAvailable(int i) {
        return (table[i] == null || table[i] == DEFUNCT);
    }

    /**
     * Returns the available index to insert a new entry, or the index with k key
     * @param h int hash code
     * @param k key element
     * @return int the available index number to insert an entry
     */
    private int findSlot(int h, K k) {
        int available = -1;
        int i = h;
        do {
            if (isAvailable(i)) {
                if (available == -1) available = i;
                if (table[i] == null) break;
            } else if (table[i].getKey().equals(k))
                return i;
            i = (i + 1) % capacity;
        } while (i != h);
        return -(available + 1);
    }

    /**
     * Gives the value of the entry with the given hash code and key element or null if
     * there is no such entry
     * @param h int hash code of the entry
     * @param k key element of the entry
     * @return V value
     */
    protected V bucketGet(int h, K k) {
        int i = findSlot(h, k);
        if (i < 0) return null;
        return table[i].getValue();
    }

    /**
     * Associates key k with value v in bucket with hash value h; returns old value
     * @param h int hash code of the new entry
     * @param k K key element of the new entry
     * @param v V value element of the new entry, or the new value of the existing entry
     * @return null or old value
     */
    protected V bucketPut(int h, K k, V v) {
        int i = findSlot(h, k);
        if (i >= 0)
            return table[i].setValue(v);
        table[-(i + 1)] = new MapEntry<>(k, v);
        n++;
        return null;
    }

    /**
     * Removes entry having key k from bucket with hash value h (if any).
     * @param h int hash code of the entry to be removed
     * @param k K key element of the entry to be removed
     * @return returns the removed value of the entry
     */
    protected V bucketRemove(int h, K k) {
        int i = findSlot(h, k);
        if (i < 0) return null;
        V removed = table[i].getValue();
        table[i] = DEFUNCT;
        n--;
        return removed;
    }

    /**
     * Returns an iterable collection of all key-value entries of the map.
     * @return Iterable collection of all the entries
     */
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();
        for (int h = 0; h < capacity; h++)
            if (!isAvailable(h)) buffer.add(table[h]);
        return buffer;
    }
 }
