/**
 * The interface for a structure of an object which stores a pair of key-value elements
 * @param <K> key
 * @param <V> value
 */
public interface Entry<K, V> {
    /**
     * Returns the key stored in this entry
     * @return K key
     */
    K getKey();

    /**
     * Returns the value stored in this entry
     * @return V value
     */
    V getValue();
}
