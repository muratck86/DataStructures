/**
 * The interface defines the BinaryTree specific method signatures and extends Tree interface
 * @param <E> Generic type
 */
interface BinaryTree<E> extends Tree<E>{
    /**
     * Accessor method returns left child of given Position p
     * @param p Position
     * @return Position
     * @throws IllegalArgumentException if it is null
     */
    Position<E> left(Position<E> p) throws IllegalArgumentException;

    /**
     * Accessor method returns right child of given Position p
     * @param p Position
     * @return Position
     * @throws IllegalArgumentException if it is null
     */
    Position<E> right(Position<E> p) throws IllegalArgumentException;

    /**
     * Accessor method returns the sibling of given Position p
     * @param p Position
     * @return Position
     * @throws IllegalArgumentException if p is the only child
     */
    Position<E> sibling(Position<E> p) throws IllegalArgumentException;
}