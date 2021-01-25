import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * An implementation of a multimap as an adaptation of classes from the java.util package
 * Uses an ArrayList collection for multiple keys, combines all values with the same key into
 * a list and the list becomes the "value" of the entry.
 * @param <K> key of an entry
 * @param <V> value of an entry
 */
public class HashMultimap<K, V> {
    //keep all values of a key in a list
    Map<K, List<V>> map = new HashMap<>();
    int total = 0;

    public HashMultimap() {}

    public int size() {return total; }

    public boolean isEmpty() {return total == 0; }

    /**
     * Returns a list of values with the given key if exists, an empty list otherwise
     * @param key key of the entry to search
     * @return ArrayList of the values
     */
    public Iterable<V> get(K key) {
        List<V> secondary = map.get(key);
        if (secondary != null)
            return secondary;
        return new ArrayList<>();
    }

    /**
     * inserts the key-value pair, if there is an entry with the given key, then it inserts this value
     * for that key.
     * @param key key of the entry to be inserted
     * @param value value of the entry to be inserted
     */
    public void put(K key, V value) {
        List<V> secondary = map.get(key);
        if (secondary == null) {
            secondary = new ArrayList<>();
            map.put(key, secondary);
        }
        secondary.add(value);
        total++;
    }

    /**
     * remove the given key-value pair from the map if there is a key-value pair matching the given.
     * If there are any other values for this key, then it just removes this value. The key and the
     * other values for this key remain.
     * @param key key of the entry to be removed
     * @param value value to be removed.
     * @return true if the operation successful (there was value to remove, the value is removed)
     */
    public boolean remove(K key, V value) {
        boolean wasRemoved = false;
        List<V> secondary = map.get(key);
        if (secondary != null) {
            wasRemoved = secondary.remove(value);
            if (wasRemoved) {
                total--;
                if (secondary.isEmpty())
                    map.remove(key);
            }
        }
        return wasRemoved;
    }

    /**
     * Removes the all entries with the given key.
     * @param key key of the entries to be removed.
     * @return an iterable of values that are removed.
     */
    public Iterable<V> removeAll(K key) {
        List<V> secondary = map.get(key);
        if (secondary != null) {
            total -= secondary.size();
            map.remove(key);
        } else {
            secondary = new ArrayList<>();
        }
        return secondary;
    }

    /**
     * Returns an ArrayList of the distinct key-value pairs.
     * @return ArrayList of entries.
     */
    public Iterable<Map.Entry<K, V>> entries() {
        List<Map.Entry<K, V>> result = new ArrayList<>();
        for (Map.Entry<K, List<V>> secondary : map.entrySet()) {
            K key = secondary.getKey();
            for (V value : secondary.getValue())
                result.add(new AbstractMap.SimpleEntry<K, V>(key, value));
        }
        return result;
    }
}
