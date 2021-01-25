import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Abstract method defines common methods for general tree structure and implements Tree interface
 * @param <E> Generic type
 */
abstract class AbstractTree<E> implements Tree<E>{
    /**
     * Method returns true if a given position is internal or not.
     * @param p Position
     * @return boolean
     * @throws IllegalArgumentException if given position is null
     */
    @Override
    public boolean isInternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) > 0;
    }

    /**
     * Method returns true if a given position is root (has no parent) or not.
     * @param p Position
     * @return boolean
     * @throws IllegalArgumentException if given Position is null
     */
    @Override
    public boolean isRoot(Position<E> p) throws IllegalArgumentException {
        return p == root();
    }

    /**
     * Method returns true if a given position is external (or leaf = has no child) or not.
     * @param p Position
     * @return boolean
     * @throws IllegalArgumentException if given position is null
     */
    @Override
    public boolean isExternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) == 0;
    }

    /**
     * Returns true if the tree is empty, means there is no Node
     * @return boolean
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Method returns count of the ancestors of a given Position p.
     * @param p Position
     * @return int
     */
    public int depth(Position<E> p){
        if (isRoot(p))
            return 0;
        return 1 + depth(parent(p));
    }

    /**
     * Method returns count of the levels from a given Position p to the deepest Position which is a member of a subtree
     * of p
     * @param p Position
     * @return int
     */
    public int height(Position<E> p){
        int h = 0;
        for (Position<E> pos: children(p)){
            h = Math.max(h, 1 + height(pos));
        }
        return h;
    }

    /* Inner Class implements Iterator interface */
    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = positions().iterator();
        public boolean hasNext(){return posIterator.hasNext();}
        public E next() {return posIterator.next().getElement();}
        public void remove() {posIterator.remove();}
    }
    /*End of inner class*/

    /**
     * Returns an iterator to traverse a tree by default(which is preorder in this type) traverse order.
     * @return Iterator
     */
    public Iterator<E> iterator() {return new ElementIterator();}

    /**
     * A wrapper method for the preorder() method.
     * Returns an iterable collection of positions to which can be used for traversing the tree by
     * default traverse order which is preorder in here
     * @return Iterable
     */
    public Iterable<Position<E>> positions() {return preorder();}

    /**
     * Returns an Iterable collection of positions of the tree to traverse by preorder fashion.
     * @return Iterable
     */
    public Iterable<Position<E>> preorder(){
        List<Position<E>> snapshot = new ArrayList<>();
        if (!(isEmpty()))
            preorderSubTree(root(), snapshot);
        return snapshot;
    }

    /**
     * Method recursively adds all Positions rooted to a given Position to a given
     * List by using preorder traverse method.
     * @param p Position
     * @param snapshot List
     */
    private void preorderSubTree(Position<E> p, List<Position<E>> snapshot){
        snapshot.add(p);
        for(Position<E> c: children(p)){
            preorderSubTree(c, snapshot);
        }
    }

    /**
     * This method returns an Iterable collection to traverse the tree by postorder method
     * @return Iterable
     */
    public Iterable<Position<E>> postorder(){
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty())
            postorderSubTree(root(), snapshot);
        return snapshot;
    }

    /**
     * Method recursively adds all Positions rooted to a given Position to a given
     * List by using postorder traverse method.
     * @param p Position
     * @param snapshot List
     */
    private void postorderSubTree(Position<E> p, List<Position<E>> snapshot){
        for (Position<E> c: children(p))
            postorderSubTree(c, snapshot);
        snapshot.add(p);
    }

    /**
     * This method returns an Iterable collection to traverse the tree by breadth first method
     * @return Iterable
     */
    public Iterable<Position<E>> breadthfirst(){
        List<Position<E>> snapshot = new ArrayList<>(); //create an ArrayList to add the Positions
        if (!isEmpty()){
            SinglyLinkedQueue<Position<E>> fringe = new SinglyLinkedQueue<>(); //create a queue to add Positions by level order
            fringe.enqueue(root()); //add root to the queue, first element will be the root
            while (!fringe.isEmpty()){ //process the loop until the queue is empty
                Position<E> p = fringe.dequeue(); // get the first element in the queue
                snapshot.add(p); //add the first element in the queue to the next index in the List
                for (Position<E> c: children(p)) //add all children of this element to the queue
                    fringe.enqueue(c);
            }
        }
        return snapshot;
    }
}