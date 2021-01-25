/**
 * @author Murat Can Kurt
 * @since 2.11.2020
 * @param <E> Integer for this project
 *
 *           An implementation of SinglyLinkedList contains an inner class named Node
 *
 */

 public class SinglyLinkedList<E> {

    /**
     * Inner class node has three properties which are, size, head and tail.
     * @param <E> Generic type
     */
    private static class Node<E> {
        private E data;
        private Node<E> next;
        public Node(E e, Node<E> n){
            data = e;
            next = n;
        }
        public E getData(){return data;}
        public Node<E> getNext(){return next;}
        public void setNext(Node<E> n) {next = n;}
    }

    private int size = 0;
    private Node<E> head = null;
    private Node<E> tail = null;

    /**
     * returns the number of elements in the List
     * @return int
     */
    public int size(){return size;}

    /**
     * returns true if there is no element in the list
     * @return boolean
     */
    public boolean isEmpty(){
        return head == null;
    }

    /**
     * Returns the first element of the linkedlist. And returns null if it it empty.
     * @return
     */
    public E first(){
        if (isEmpty())
            return null;
        return head.getData();
    }

    /**
     * Return the last element of the list
     * @return E
     */
    public E last(){
        if (isEmpty()) return null;
        return tail.getData();
    }

    /**
     * Add an element to the head of the list.
     * @param n Generic type
     */
    public void addFirst(E n){  //addFirst("Murat")
        head = new Node<>(n,head);
        if (size == 0){
            tail = head;
        }
        size++;
    }

    /**
     * Add an element to end of the list
     * @param n Generic type
     */
    public void addLast(E n){
        Node<E> newest = new Node<>(n, null);
        if(isEmpty()){
            head = newest;
        }else
            tail.setNext(newest);
        tail = newest;
        size++;
    }

    /** remove and return the first element in the list, the second element will become the first after that.
     * if the list is empty it returns null and does nothing.
     * @return Generic type of the linked list
     */
    public E removeFirst(){
        if (isEmpty()) return null;
        E temp = head.getData();
        head = head.getNext();
        size--;
        if (size == 0) tail = null;
        return temp;
    }

    /**
     * A robust implementation of the hashCode method
     * @return int hashCode
     */
    public int hashCode() {
        int h = 0;
        for (Node walk = head; walk != null; walk = walk.getNext()) {
            h ^= walk.getData().hashCode();
            h = (h << 5 | h >> 27);
        }
        return h;
    }

    @Override
    public String toString() {
        Node<E> node = head;
        String list = "{";
        for (int i = 0; i < size(); i++){
            list = list.concat(node.getData().toString());
            if (i < this.size -1)
                list = list.concat(", ");
            node = node.getNext();
        }
        return list.concat("}");
    }
}