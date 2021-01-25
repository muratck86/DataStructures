
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * An implementation of stack data type based on Singly Linked List structure
 *
 * @author Murat Can Kurt
 * @since 10.11.2020
 * @param <E> type of the data to work with the stacks
 *
 */


class SinglyLinkedStack<E>{
    private SinglyLinkedList<E> stack;

    /**
     * Constructor with empty stack
     */
    public SinglyLinkedStack(){
        stack = new SinglyLinkedList<E>();
    }

    /**
     * Number of the objects in the stack
     * @return int
     */
    public int size(){ return stack.size();}

    /**
     * Returns true if the stack is empty
     * @return boolean
     */
    public boolean isEmpty(){ return stack.isEmpty();}

    /**
     * Put an element to the top of the stack
     * @param e Generic object type
     */
    public void push(E e) {
        stack.addFirst(e);
    }

    /**
     * Gives the element that is at the top of the stack
     * @return E
     */
    public E top(){
        if (isEmpty()) return null;
        return stack.first();
    }

    /**
     * Does the same thing as top() method and additionally, removes this element from the stack
     * @return gives the element which is at the top of the stack and removes it from the stack
     */
    public E pop(){
        if (isEmpty()) return null;
        return stack.removeFirst();
    }

    @Override
    public String toString() {
        return "MyStack{" +
                "Size=" + size() +
                ", Top Element=" + top() +
                 '}';
    }
}
