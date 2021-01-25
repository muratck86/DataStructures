import java.util.ArrayList;
import java.util.Random;

/**
 * An abstract base class for hash table implementations, extending the AbstractMap class
 * @param <K> key element of the entry
 * @param <V> value element of the entry
 */
public abstract class AbstractHashMap<K, V>  extends AbstractMap<K, V> {
    protected int n = 0; //number of entries in the HashMap
    protected int capacity; //length of the Table
    private int prime; //prime factor
    private long scale, shift; //scaling and shifting factors

    /**
     * The constructor creates an empty hash map with the given prime factor and table length
     * Creates random scale and shift factors.
     * @param cap int length of the table
     * @param p int prime factor
     */
    public AbstractHashMap(int cap, int p) {
        prime = p;
        capacity = cap;
        Random random = new Random();
        scale = random.nextInt(prime - 1) + 1;
        shift = random.nextInt(prime);
        createTable();
    }

    /**
     * The constructor creates an empty hash map with the given table length
     * uses default prime value
     * @param cap int length of the table
     */
    public AbstractHashMap(int cap) {this(cap, 109345121); }

    /**
     * Default constructor creates an empty hash map with table length of 17
     */
    public AbstractHashMap() {this(17); }

    /**
     * @return int the count of elements in the hash map
     */
    public int size() {return  n; }

    /**
     * Give the value of the entry with the given key
     * @param key element of the entry
     * @return V value of the entry
     */
    public V get(K key) {return bucketGet(hashValue(key), key); }

    /**
     * Remove the entry with the given key.
     * @param key of the entry to be removed
     * @return V value of the removed entry
     */
    public V remove(K key) {return bucketRemove(hashValue(key), key); }

    /**
     * Insert to or update the hash map with the given key-value pair.
     * @param key key of the entry to be inserted (or updated if already exists)
     * @param value value of the entry to be inserted (or updated if already exists)
     * @return null if the operation result with insert, the old V value otherwise
     */
    public V put(K key, V value) {
        V answer = bucketPut(hashValue(key), key, value);
        if (n > capacity / 2)
            resize(2 * capacity - 1);
        return answer;
    }

    /**
     * Compute a hash value for the given key usin the MAD formula. (multiply, add, divide)
     * @param key K type element to be hash coded.
     * @return int hash code
     */
    private int hashValue(K key) {
        return (int) ((Math.abs(key.hashCode() * scale + shift) % prime) % capacity);
    }

    /**
     * grow or shrink the hash map capacity (table length) by the given newCap value
     * @param newCap new capacity of the table.
     */
    private void resize(int newCap) {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>(n);
        for (Entry<K, V> e : entrySet())
            buffer.add(e);
        capacity = newCap;
        createTable();
        n = 0;
        for (Entry<K, V> e: buffer)
            put(e.getKey(), e.getValue());
    }

    /**
     * Creates the underlying data structure for the hash map
     */
    protected abstract void createTable();

    /**
     * Access the entry stored in the hash map with the given hash code and key
     * @param h int hash code of the entry
     * @param k key element of the entry
     * @return V value of the entry
     */
    protected abstract V bucketGet(int h, K k);

    /**
     * Insert an entry to the hash map using the given hash code, key  and value
     * If there is an entry with the given hash code and key already exists, then update its value
     * @param h int hash code of the new entry
     * @param k K key element of the new entry
     * @param v V value element of the new entry, or the new value of the existing entry
     * @return null or the old value element of the existing entry
     */
    protected abstract V bucketPut(int h, K k, V v);

    /**
     * Remove an entry from the hash map.
     * @param h int hash code of the entry to be removed
     * @param k K key element of the entry to be removed
     * @return V value element of the removed entry
     */
    protected abstract V bucketRemove(int h, K k);
}
