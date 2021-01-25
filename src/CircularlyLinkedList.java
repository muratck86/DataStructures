
public class CircularlyLinkedList<E> {
    private static class Node<E>{

        private E data;
        private Node<E> next;

        public Node(E e, Node<E> next){
            data = e;
            this.next = next;
        }

        public E getData() {return data;}
        public void setData(E data) {this.data = data;}
        public Node<E> getNext() {return next;}
        public void setNext(Node<E> next) {this.next = next;}
    }

    private int size = 0;
    private Node<E> tail = null;

    public int size(){return size;}

    public boolean isEmpty(){return tail == null;}

    public E first(){
        if (isEmpty())
            return null;
        return tail.getNext().getData();
    }

    public E last(){
        if (isEmpty()){
            return null;
        }else{
            return tail.getData();
        }

    }

    public void addFirst(E e){
        if (isEmpty()){
            tail = new Node<>(e,null);
            tail.setNext(tail);
        }else{
            tail.setNext(new Node<>(e, tail.getNext()));
        }
        size++;
    }

    public void addLast(E e){
        addFirst(e);
        rotate();
    }

    public E removeFirst(){
        if (isEmpty()){return null;}
        Node<E> temp = tail.getNext();
        if (tail == temp) {
            tail = null;
        }
            else{
            tail.setNext(temp.getNext());
        }
        size--;
        return temp.getData();
    }

    public void rotate(){
        if (!isEmpty())
            tail = tail.getNext();
    }

    @Override
    public String toString() {
        String list = "{";
        for (int i = 0; i < size(); i++){
            list = list.concat(tail.getData().toString());
            if (i < this.size -1)
                list = list.concat(", ");
            rotate();
        }
        return list.concat("}");
    }
}
