import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Murat Can Kurt
 * @since 10.11.2020
 *
 *
 * An implementation of Queue ADT based on SingularlyLinkedList, it has an additional method
 * rotate() to send the first element to last position.
 * @param <E> Generic type
 *
 */
class SinglyLinkedQueue<E>{
    private SinglyLinkedList<E> queue;

    public SinglyLinkedQueue(){
        queue = new SinglyLinkedList<>();
    }

    /**
     * Number of the objects in the queue
     * @return int
     */
    public int size(){ return queue.size();}

    /**
     * Returns true if the queue is empty
     * @return boolean
     */
    public boolean isEmpty(){ return queue.isEmpty();}

    /**
     * Put an element to the last of the queue
     * @param e Generic object type
     */
    public void enqueue(E e) {
        queue.addLast(e);
    }

    /**
     * Gives the element that is at first on the queue
     * @return E
     */
    public E first(){
        return queue.first();
    }

    /**
     * Does the same thing as first() method and additionally, removes this element from the queue
     * @return E
     */
    public E dequeue(){
        return queue.removeFirst();
    }

    /**
     * Use the queue circularly, to do this, dequeue the first element and put it into last position.
     */
    public void rotate(){
        queue.addLast(queue.removeFirst());
    }

    @Override
    public String toString() {
        return "MyQueue" +
                "Size=" + size() +
                ", Top Element=" + first() +
                '}';
    }
}
