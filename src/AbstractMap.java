import java.util.Iterator;

/**
 * An implementation of a Base for Map structures.
 * @param <K> key element
 * @param <V> value element
 */
public abstract class AbstractMap<K, V> implements Map<K, V> {

    /**
     * Tests if the size of the map is 0
     * @return true if the map stores no entries.
     */
    public boolean isEmpty() { return size() == 0; }

    /*--------------Nested MapEntry class ------------------*/

    /**
     * The implementation of Entry Interface, no additional methods.
     * @param <K> key element
     * @param <V> value element
     */
    protected static class MapEntry<K, V> implements Entry<K, V> {
        private K k;
        private V v;

        /**
         * Constructor, creates an entry from the given key-value pair.
         * @param key is the key of the new entry
         * @param value is the value element of the entry
         */
        public MapEntry(K key, V value) {
            k = key;
            v = value;
        }
        //Access methods
        public K getKey() {return k; }
        public V getValue() {return v; }

        //Update methods.
        protected void setKey(K key) {k = key; }
        protected V setValue( V value) {
            V old = v;
            v = value;
            return old;
        }
    } /*-------------------End of nested MapEntry class ---------------*/

    /**
     * The support classes to create an Iterator for keys of the entries stored in the map
     */
    private class KeyIterator implements Iterator<K> {
        //Create an entries Iterator from the set of entries.
        private Iterator<Entry<K, V>> entries = entrySet().iterator();
        public boolean hasNext() {return entries.hasNext(); }
        public K next() {return entries.next().getKey(); }
        public void remove() {throw new UnsupportedOperationException(); }
    }
    private class KeyIterable implements Iterable<K> {
        public Iterator<K> iterator() {return new KeyIterator(); }
    }

    /**
     * Returns an iterable of keys based on the set of the entries
     * @return iterable of keys
     */
    public Iterable<K> keySet() {return new KeyIterable(); }

    /**
     * The support classes to create an iterator for the values of the entries stored in the map
     */
    private class ValueIterator implements Iterator<V> {
        private Iterator<Entry<K, V>> entries = entrySet().iterator();
        public boolean hasNext() {return entries.hasNext(); }
        public V next() {return entries.next().getValue(); }
        public void remove() {throw new UnsupportedOperationException(); }
    }

    private class ValueIterable implements Iterable<V> {
        public Iterator<V> iterator() {return new ValueIterator(); }
    }

    /**
     * Returns an iterable of keys based on the set of entries.
     * @return
     */
    public Iterable<V> values() {return new ValueIterable(); }
}
