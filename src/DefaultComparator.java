import java.util.Comparator;

/**
 * A default comparator class that implements a comparator based upon the natural ordering of its element type
 * @param <E> an element that is assumed to be a Comparable type
 */
public class DefaultComparator<E> implements Comparator<E> {
    /**
     * Compares a to be according to their natural ordering
     * @param a first element
     * @param b second element
     * @return -1 if a is less (or before), 0 if a = b and 1 if b is less (or before)
     * @throws ClassCastException if a and b are not comparable
     */
    public int compare(E a, E b) throws ClassCastException {
        return ((Comparable<E>) a).compareTo(b);
    }
}
