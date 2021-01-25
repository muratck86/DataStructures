public interface PositionalList<E> {
    /**Returns the number of elements in the list */
    int size();

    /** Returns true if this list has no elements in it */
    boolean isEmpty();

    /** returns the first element in the list (or null if the list is empty) */
    Position<E> first();

    /** Returns the last element in the list (or null if the list is empty) */
    Position<E> last();

    /**Returns the position before the given position (or null if p is the first)*/
    Position<E> before(Position<E> p) throws IllegalArgumentException;

    /**Returns the position after the given position p (or null if p is the last)*/
    Position<E> after(Position<E> p) throws IllegalArgumentException;

    /**Inserts the given element as the first element and returns its position */
    Position<E> addFirst(E e);

    /**Inserts the given element as the last element and returns its position*/
    Position<E> addLast(E e);

    /** Inserts Element e right before the given position p and returns its new position */
    Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException;

    /** Inserts Element e right after the given position p and returns its new position. */
    Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException;

    /** Updates the element of the given position and returnst the old element kept in the position */
    E set(Position<E> p, E e) throws IllegalArgumentException;

    /** Removes the element stored in the position p in the list and returns its element (invalidates p)*/
    E remove(Position<E> p) throws IllegalArgumentException;

}
