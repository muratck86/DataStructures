public interface AdaptablePriorityQueue<K, V> extends PriorityQueue<K, V>{
    public void remove(Entry<K, V> e) throws IllegalArgumentException;
    public void replaceKey(Entry<K, V> e, K k) throws IllegalArgumentException;
    public void replaceValue(Entry<K, V> e, V v) throws IllegalArgumentException;
}
