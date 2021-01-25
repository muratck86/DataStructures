import java.util.ArrayList;
import java.util.List;

/**
 * Abstract method defines the common methods of Binary tree structure, extends AbstractTree abstract class and
 * implements BinaryTree interface
 * @param <E> Generic type
 */
abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E>{
    /**
     * Accessor method returns the sibling of given Position p
     * @param p Position
     * @return Position
     */
    public Position<E> sibling(Position<E> p){
        Position<E> parent = parent(p);
        if (parent == null) return null;
        if (p == left(parent))
            return right(parent);
        else return left(parent);
    }

    /**
     * Method returns the number of children of a position
     * @param p Position
     * @return int count of children (may be 0, 1 or 2 since this is a BinaryTree)
     */
    public int numChildren(Position<E> p){
        int count = 0;
        if (left(p) != null)
            count++;
        if (right(p) != null)
            count++;
        return count;
    }

    /**
     * Method returns an Iterable collection of children of a Position which may contain at most 2 Positions since
     * this is a BinaryTree
     * @param p Position
     * @return Iterable collection of chidren of p
     */
    public Iterable<Position<E>> children(Position<E> p){
        List<Position<E>> snapshot = new ArrayList<>(2);
        if (left(p) != null)
            snapshot.add(left(p));
        if (right(p) != null)
            snapshot.add(right(p));
        return snapshot;
    }

    /**
     * Method returns an Iterable collection of Positions to traverse the tree in default order which is inorder
     * method for BinaryTree
     * @return Iterable collection of Positions of this
     */
    public Iterable<Position<E>> positions(){
        return inorder();
    }

    /**
     * The wrapper method for inorderSubtree() method
     * Method returns an Iterable collection of Positions to traverse the tree by inorder method.
     * @return Iterable
     */
    public Iterable<Position<E>> inorder(){
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty())
            inorderSubtree(root(), snapshot);
        return snapshot;
    }

    /**
     * Method recursively adds all Positions rooted to a given Position to a given
     * List by using inorder traverse method.
     * @param p Position
     * @param snapshot List of Positions
     */
    private void inorderSubtree(Position<E> p, List<Position<E>> snapshot){
        if (left(p) != null)
            inorderSubtree(left(p), snapshot);
        snapshot.add(p);
        if (right(p) != null)
            inorderSubtree(right(p), snapshot);
    }
}