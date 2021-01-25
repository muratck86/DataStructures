public class DoublyLinkedList<E> {

    private static class Node<E>{
        private E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(E e, Node<E> prev, Node<E> next){
            this.data = e;
            this.next = next;
            this.prev = prev;
        }

        public E getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setData(E data) {
            this.data = data;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }
    }

    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;

    public DoublyLinkedList(){
        header = new Node<E>(null,null,null);
        trailer = new Node<E>(null,header,null);
        header.setNext(trailer);
    }

    public int size(){return size;}

    public boolean isEmpty(){ return size == 0;}

    public E first(){
        if (isEmpty())
            return null;
        return header.getNext().getData();
    }

    public E last(){
        if (isEmpty())
            return null;
        return trailer.getPrev().getData();
    }
    public void addFirst(E e){addBetween(e,header,header.getNext());}

    public void addLast(E e){addBetween(e,trailer.getPrev(), trailer);}

    public E removeFirst(){return remove(header.getNext());}

    public E removeLast(){return remove(trailer.getPrev());}

    private void addBetween(E e, Node<E> pred, Node<E> succ){
        Node<E> newest = new Node<E>(e, pred, succ);
        pred.setNext(newest);
        succ.setPrev(newest);
        size++;
    }
    private E remove(Node<E> e){
        if (header.getNext() == trailer && trailer.getPrev() == header)
            return null;
        e.getPrev().setNext(e.getNext());
        e.getNext().setPrev(e.getPrev());
        size--;
        return e.getData();
    }
}
