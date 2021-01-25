import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of positional list based on DoublyLinkedList Structure.
 * It has Position structure implemented by my previous inner Node class this time, Hence,
 * this data type re-implemented my DoublyLinked list type although it is based on it
 *
 * @param <E> type of data to be stored in the this LinkedPositionalList
 */
public class LinkedPositionalList<E> implements PositionalList<E> {

    // Nested Node class
    /**
     * Definition of a Node class
     * @param <E> type of the data to be stored in the Node
     */
    private static class Node<E> implements Position<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;
        public Node(E e, Node<E> p, Node<E> n){
            element = e;
            prev = p;
            next = n;
        }

        /**
         * @return The element stored in the Node
         * @throws IllegalStateException if the node is no longer in the list
         */
        public E getElement() throws IllegalStateException {
            if (next == null)
                throw new IllegalStateException("Position no longer valid");
            return element;
        }
        public Node<E> getPrev() {return prev;}

        public Node<E> getNext() {return next;}

        public void setElement(E e){element = e;}

        public void setPrev(Node<E> p) {prev = p;}

        public void setNext(Node<E> n) {next = n;}
    }//End of nested Node class

    private Node<E> header;
    private Node<E> trailer;
    private int size = 0; //Number of elements in the list

    /**
     * Creates an initially empty list
     */
    public LinkedPositionalList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, null, null);
        header.setNext(trailer);
    }

    /**
     * Tests if given position p is a node and if it is, test p if it is an already removed one.
     * @param p Position to be tested
     * @return Position casted to Node
     * @throws IllegalArgumentException if p is not a Node, or if it is a removed Node
     */
    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Invalid p");
        Node<E> node = (Node<E>) p;
        if (node.getNext() == null)
            throw new IllegalArgumentException("p is no longer in the list");
        return node;
    }

    /**
     * Return the given Node p as Position or null if it is a sentinel.
     * @param node Node to be returned as Position
     * @return Position
     */

    private Position<E> position(Node<E> node){
        if (node == header || node == trailer) return null;
        return node;
    }

    /**
     * Gives the number of elements
     * @return int the number of elements in the list
     */
    public int size() {return size;}

    /**
     * Tests it the number of elements is 0
     * @return true if list is empty
     */
    public boolean isEmpty() {return (size == 0);}

    /**
     * Gives the first Position of the List or null if it is empty
     * @return Position
     */
    public Position<E> first() {return position(header.getNext());}

    /**
     * Gives the last Position of the List or null if it is empty
     * @return Position
     */
    public Position<E> last() {return position(trailer.getPrev());}

    /**
     * Gives the position right before the position p in the list
     * @param p Position
     * @return Position
     * @throws IllegalArgumentException if p is not in the list
     */
    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getPrev());
    }

    /**
     * Gives the position right after the position p in the list
     * @param p Position
     * @return Position
     * @throws IllegalArgumentException if p is not in the list
     */
    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getNext());
    }

    /**
     * Insert the element e between given two nodes.
     * @param e the element to be inserted
     * @param bef predecessor of e
     * @param af successor of e
     * @return Node which stores e
     */
    public Position<E> addBetween(E e, Node<E> bef, Node<E> af) {
        Node<E> node = new Node<>(e, bef, af);
        bef.setNext(node);
        af.setPrev(node);
        size++;
        return node;
    }

    /**
     * Insert element e to the list as the first element
     * @param e element to be inserted
     * @return Position which is added to the list
     */
    public Position<E> addFirst(E e) {
        return addBetween(e, header, header.getNext());
    }

    /**
     * Insert element e to the list as the last element
     * @param e element to be inserted
     * @return Position which is added to the list
     */
    public Position<E> addLast(E e) {
        return addBetween(e, trailer.getPrev(), trailer);
    }

    /**
     * Insert element e to the list right before p
     * @param p which will be the successor of e
     * @param e element which is added to the list before p
     * @return Position which is added to the list, stores e
     * @throws IllegalArgumentException if p is not in the list
     */
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node.getPrev(), node);
    }

    /**
     * Insert element e to the list right after p
     * @param p which will be the predecessor of e
     * @param e element which is added to the list after p
     * @return Position which is added to the list, stores e
     * @throws IllegalArgumentException if p is not in the list
     */
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node, node.getPrev());
    }

    /**
     * Update the element of Position p with e and give the old value
     * @param p Position whose element to be updated
     * @param e new element
     * @return E old element
     * @throws IllegalArgumentException if p is not in the list
     */
    public E set(Position<E> p , E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }

    /**
     * Remove the given Position p from the list and return the element stored in it.
     * @param p Position to be removed
     * @return Position which is removed
     * @throws IllegalArgumentException if the position is already not in the list
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E temp = node.getElement();
        Node<E> pre = node.getPrev();
        Node<E> suc = node.getNext();
        pre.setNext(suc);
        size--;
        node.setElement(null);
        node.setNext(null);
        node.setPrev(null);
        return temp;
    }

    //Nested PositionIterator class
    private class PositionIterator implements Iterator<Position<E>> {
        private Position<E> cursor = first(); // Position of the next element to be returned
        private Position<E> recent = null; // Position of last returned element

        /**
         * Tests if the iterator has a next element
         *
         * @return true if the list is not yet exhausted
         */
        public boolean hasNext() {
            return cursor != null;
        }

        /**
         * Gives the next Position in the iterator.
         *
         * @return Position
         * @throws NoSuchElementException if the list was exhausted and there is no next element
         */
        public Position<E> next() throws NoSuchElementException {
            if (cursor == null) throw new NoSuchElementException("nothing left");
            recent = cursor;
            cursor = after(cursor);
            return recent;
        }

        /**
         * Removes the last returned element by the next method.
         *
         * @throws IllegalStateException if there is nothing to remove
         */
        public void remove() throws IllegalStateException {
            if (recent == null) throw new IllegalStateException("nothing to remove");
            LinkedPositionalList.this.remove(recent);
            recent = null;
        }
    }  //End of nested PositionIterator class


    //Nested PositionIterable class
    /**
     *
     */
    private class PositionIterable implements Iterable<Position<E>> {
        public Iterator<Position<E>> iterator() {return new PositionIterator();}
    } //End of nested PositionIterable class

    /**
     * Returns an iterable representation of the positions of the list
     * @return Iterable
     */
    public Iterable<Position<E>> positions() {
        return new PositionIterable();
    }

    //Nested ElementIterator class
    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> positionIterator = new PositionIterator();
        public boolean hasNext() {return positionIterator.hasNext();}
        public E next() {return positionIterator.next().getElement();}
        public void remove() {positionIterator.remove();}
    } //End of nested ElementIterator class

    /**
     * @return An iterator of the elements stored in the list
     */
    public Iterator<E> iterator() {return new ElementIterator();}

    /**
     * A static method for performing insertion-sort on a positional-list
     * @param pl the PositionalList to be sorted in non decreasing order.
     */
    public static void insertionSort(PositionalList<Integer> pl) {
        Position<Integer> marker = pl.first();
        while (marker != pl.last()){
            Position<Integer> pivot = pl.after(marker);
            Integer value = pivot.getElement();
            if (value > marker.getElement())
                marker = pivot;
            else {
                Position<Integer> walk = marker;
                while (walk != pl.first() && pl.before(walk).getElement() > value){
                    walk = pl.before(walk);
                }
                pl.remove(pivot);
                pl.addBefore(walk, value);
            }
        }
    }
}
