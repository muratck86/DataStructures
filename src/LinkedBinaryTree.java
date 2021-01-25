/**
 * Concrete implementation of BinaryTree based on Linked structure, this class extends
 * AbstractBinaryTree abstract class
 * @param <E> Generic type
 */

class LinkedBinaryTree<E> extends AbstractBinaryTree<E>{

    /**
     * Nested Node class defines a fields for a node in the tree, each node has data, parent, left, and right fields.
     * And this Node implements Position interface.
     * @param <E> Generic type
     */
    protected static class Node<E> implements Position<E>{
        private E data;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        /**
         * Constructor for a Node
         * @param e E Generic type is the data kept in the node
         * @param p Node parent node
         * @param l Node left node
         * @param r Node right node
         */
        public Node(E e, Node<E> p, Node<E> l, Node<E> r){
            data = e;
            parent = p;
            left = l;
            right = r;
        }

        /**
         * Implementation of the method of the Position interface,
         * this is the accessor method to get the data of the node.
         * @return
         */
        public E getElement() {return data;}

        /*Accessor methods*/
        public Node<E> getParent() {return parent;}
        public Node<E> getLeft() {return left;}
        public Node<E> getRight() {return right;}

        /*Update methods*/
        public void setElement(E e) {data = e;}
        public void setParent(Node<E> e) {parent = e;}
        public void setLeft(Node<E> e) {left = e;}
        public void setRight(Node<E> e) {right = e;}

    } /*End of Nested Class Node*/

    /**
     * Creates an object of Node, -factory method-
     * @param e E element (the data)
     * @param p Node parent Node
     * @param l Node left Node
     * @param r Node right Node
     * @return Node object with given properties
     */
    protected Node<E> createNode(E e, Node<E> p, Node<E> l, Node<E> r){
        return new Node<>(e, p, l, r);
    }

    protected Node<E> root = null; // The root of the tree, which is initially has null data
    private int size = 0; //number of the nodes in the tree

    /**
     * Default constructor.
     */
    public LinkedBinaryTree(){}

    /**
     * This method validates a given Node if it is a Node of this tree or not and recognises the removed nodes from this
     * tree (which are the nodes whose parents are set to be themselves).
     * @param p Position
     * @return Node
     * @throws IllegalArgumentException if the Node is not from this tree or it is a removed node.
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException{
        if (!(p instanceof Node))
            throw new IllegalArgumentException("Not a valid position!");
        Node<E> node = (Node<E>) p;
        if (node.getParent() == node)
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    /**
     * Gives the count of the Nodes in the tree
     * @return int
     */
    public int size() {return size;}

    /**
     * Accessor method for root position
     * @return Position
     */
    public Position<E> root() {return root;}

    /**
     * Accessor method for parent position of a given position p
     * @param p Position
     * @return Position
     * @throws IllegalArgumentException if p has no parent (p is root)
     */
    public Position<E> parent(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        return node.getParent();
    }

    /**
     * Accessor method for left child Position of p
     * @param p Position
     * @return Position
     * @throws IllegalArgumentException if p has no left child
     */
    public Position<E> left(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        return node.getLeft();
    }

    /**
     * Accessor method for right child Position of p
     * @param p Position
     * @return Position
     * @throws IllegalArgumentException if p has no right child
     */
    public Position<E> right(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        return node.getRight();
    }

    /**
     * Adds the root node of the empty tree
     * @param e E the data which is kept in the Position
     * @return Position of the root of the tree
     * @throws IllegalStateException if the tree already has a root
     */
    public Position<E> addRoot(E e) throws IllegalStateException{
        if (!isEmpty()) throw new IllegalStateException("Tree is not empty.");
        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }

    /**
     * Adds the left child of Position p
     * @param p Position
     * @param e E data of left child to be added
     * @return Position of left child which is added
     * @throws IllegalArgumentException if p already has a left child.
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException{
        Node<E> parent = validate(p);
        if (parent.getLeft() != null)
            throw new IllegalArgumentException("p already has a left child.");
        Node<E> child = createNode(e, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }

    /**
     * Adds the right child of Position p
     * @param p Position
     * @param e E data of right child to be added
     * @return Position of right child which is added
     * @throws IllegalArgumentException if p already has a right child.
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException{
        Node<E> parent = validate(p);
        if(parent.getRight() != null) throw new IllegalArgumentException("p already has a right child.");
        Node<E> child = createNode(e, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }

    /**
     * Updates the data kept in a Position p
     * @param p Position
     * @param e E the new data
     * @return E the old data
     * @throws IllegalArgumentException if Position is not valid
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }

    /**
     * Merges two given trees by rooting them to a given Position p
     * @param p Position new root of the trees to be attached together
     * @param t1 LinkedBinaryTree to be the left subtree of the merged tree
     * @param t2 LinkedBinaryTree to be the right subtree of the merged tree
     * @throws IllegalArgumentException if p is not valid or p is an internal Position
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2)
            throws IllegalArgumentException{
        Node<E> node = validate(p);
        if (isInternal(p)) throw new IllegalArgumentException("p must be a leaf.");
        size += t1.size() + t2.size();
        if (!t1.isEmpty()){
            t1.root.setParent(node);
            node.setLeft(t1.root);
            t1.root = null;
            t1.size = 0;
        }
        if (!t2.isEmpty()){
            t2.root.setParent(node);
            node.setRight(t2.root);
            t2.root = null;
            t2.size = 0;
        }
    }

    /**
     * Remove a Position from this, this method works only if p is a leaf or has just one child.
     * @param p Position to be removed
     * @return E the data of the removed Position
     * @throws IllegalArgumentException if p has two children
     */
    public E remove(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        if (numChildren(p) == 2) throw new IllegalArgumentException("p has two children.");
        Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
        if (child != null) child.setParent(node.getParent());
        if (node == root) root = child;
        else {
            Node<E> parent = node.getParent();
            if (node == parent.getLeft()) parent.setLeft(child);
            else parent.setRight(child);
        }
        size--;
        E temp = node.getElement();
        node.setElement(null);
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node);
        return temp;
    }
}