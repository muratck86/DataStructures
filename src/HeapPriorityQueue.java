import java.util.ArrayList;
import java.util.Comparator;

/**
 * Priority queue implemented with an array-based heap.
 * @param <K> key element
 * @param <V> key element
 */
public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    /** primary collection of entries */
    protected ArrayList<Entry<K, V>> heap = new ArrayList<>();

    /** Constructor creates an empty priority queue based on the natural ordering of its keys */
    public HeapPriorityQueue() {super();}

    /** Constructor creates an empty priority queue based on the given comparator to order keys */
    public HeapPriorityQueue(Comparator<K> c) {super(c);}

    /** Constructor creates a priority queue  initialized with the given key-value pairs.
     * with bottom-up construction. using natural ordering of keys.
     */
    public HeapPriorityQueue(K[] keys, V[] values) {
        super();
        for (int j = 0; j < Math.min(keys.length, values.length); j++)
            heap.add(new PQEntry<>(keys[j], values[j]));
        heapify();
    }

    /**
     * Performs a bottom-up construction of the heap in linear time.
     * start at PARENT of last entry, loop until processing the root
     */
    protected void heapify() {
        int startIndex = parent(size() - 1);
        for (int j = startIndex; j >= 0; j--)
            downHeap(j);
    }

    //Protected utilities

    /**
     * Gives the index of the parent node of the node in the index j.
     * @param j index of a given node.
     * @return int index of the parent node.
     */
    protected int parent(int j) {return (j - 1) / 2; }

    /**
     * Gives the index of the left child node of the node in the index j.
     * @param j index of a given node.
     * @return int index of the left child node.
     */
    protected int left(int j) {return 2 * j + 1; }

    /**
     * Gives the index of the right child node of the node in the index j.
     * @param j index of a given node.
     * @return int index of the right child node.
     */
    protected int right(int j) {return 2 * j + 2; }

    /**
     * Tests if a given node at index j has a left child.
     * @param j int index of a node
     * @return true if the node in the index j has a left child.
     */
    protected boolean hasLeft(int j) { return left(j) < heap.size(); }

    /**
     * Tests if a given node at index j has a right child.
     * @param j int index of a node
     * @return true if the node in the index j has a left child.
     */
    protected boolean hasRight(int j) { return right(j) < heap.size(); }

    /**
     * Swap the nodes at indexes i and j.
     * @param i int index of a node
     * @param j int index of other node
     */
    protected  void swap (int i , int j) {
        Entry<K, V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    /**
     * Swaps the positions with its parent until it becomes the root or it has a key greater than
     * the key of its parent.
     * @param j index of the node whose key is less than its parent's key.
     */
    protected void upHeap(int j) {
        while (j > 0) {
            int p = parent(j);
            if (compare(heap.get(j), heap.get(p)) >= 0) break;
            swap(j, p);
            j = p;
        }
    }

    /**
     * Swaps the positions with its children whose key is less than its sibling and this until
     * key of this node is greater than both children or, it is a leaf.
     * @param j index of the node whose key is greater than it at least one of the children's key.
     */
    protected void downHeap(int j) {
        while (hasLeft(j)) {
            int leftIndex = left(j);
            int smallChildIndex = leftIndex;
            if (hasRight(j)) {
                int rightIndex = right(j);
                if (compare(heap.get(leftIndex), heap.get(rightIndex)) > 0)
                    smallChildIndex = rightIndex;
            }
            if (compare(heap.get(smallChildIndex), heap.get(j)) >= 0)
                break;
            swap(j, smallChildIndex);
            j = smallChildIndex;
        }
    }

    //public methods

    /**
     * Returns the count of the Entries in this priority queue
     * @return int
     */
    public int size() {return heap.size(); }

    /**
     * Gives the Entry with the minimum key value which is the 0th Entry of the array.
     * @return Entry with the least key value
     */
    public Entry<K, V> min() {
        if (heap.isEmpty()) return null;
        return heap.get(0);
    }

    /**
     * Insert an Entry to the priority queue with the given key and value
     * @param key of the entry to be inserted
     * @param value of the entry to be inserted
     * @return the inserted Entry
     * @throws IllegalArgumentException if the key is invalid
     */
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K, V> newest = new PQEntry<>(key, value);
        heap.add(newest);
        upHeap(heap.size() -1);
        return newest;
    }

    /**
     * Gives the Entry with the least key and removes it from the priority queue
     * @return Entry removed from the priority queue
     */
    public Entry<K, V> removeMin() {
        if (heap.isEmpty()) return null;
        Entry<K, V> answer = heap.get(0);
        swap(0, heap.size() - 1);
         heap.remove(heap.size() - 1);
         downHeap(0);
         return answer;
    }
}
