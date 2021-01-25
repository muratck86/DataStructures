import java.util.Iterator;

/**
 * The Interface defines the method signatures of general tree structure and extends Iterable interface
 * @param <E> Generic type
 */
interface Tree<E> extends Iterable<E> {
    /**
     * Accessor method for the root Position of the tree which has no parent
     * @return Position
     */
    Position<E> root();

    /**
     * Accessor method  for the parent position of a given position
     * @param p Position
     * @return Position
     * @throws IllegalArgumentException if it is root
     */
    Position<E> parent(Position<E> p) throws IllegalArgumentException;

    /**
     * Accessor method for getting the children of a given position
     * @param p Position
     * @return Iterable
     * @throws IllegalArgumentException
     */
    Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException;

    /**
     * Method returns the number of childrens of a position
     * @param p Position
     * @return int number of children
     * @throws IllegalArgumentException
     */
    int numChildren(Position<E> p) throws IllegalArgumentException;

    /**
     * Method returns true if a given position is internal or not.
     * @param p Position
     * @return boolean
     * @throws IllegalArgumentException
     */
    boolean isInternal(Position<E> p) throws IllegalArgumentException;

    /**
     * Method returns true if a given position is root (has no parent) or not.
     * @param p Position
     * @return boolean
     * @throws IllegalArgumentException
     */
    boolean isRoot(Position<E> p) throws IllegalArgumentException;

    /**
     * Method returns true if a given position is external (or leaf = has no child) or not.
     * @param p Position
     * @return boolean
     * @throws IllegalArgumentException
     */
    boolean isExternal(Position<E> p) throws IllegalArgumentException;

    /**
     * Method returns the number of positions in a given tree
     * @return int
     */
    int size();

    /**
     * Returns true if the tree is empty, means there is no Node
     * @return boolean
     */
    boolean isEmpty();

    /**
     * Returns an iterator to traverse a tree by default traverse order.
     * @return Iterator
     */
    Iterator<E> iterator();

    /**
     * Returns an iterable collection of positions to which can be used for traversing the tree by
     * default traverse order
     * @return Iterable
     */
    Iterable<Position<E>> positions();
}