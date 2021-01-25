//import java.util.ArrayList;
//
//public class TreeMapMurat<K, V> extends AbstractSortedMap<K,V> {
//    protected static class BalanceableBinaryTree<K, V> extends LinkedBinaryTree<Entry<K, V>> {
//        protected static class BSTNode<E> extends Node<E> {
//            int aux = 0;
//            BSTNode(E e, Node<E> parent, Node<E> leftChild, Node<E> rightChild) {
//                super(e, parent, leftChild, rightChild);
//            }
//        }
//        //TODO
//    }
//
//    protected BalanceableBinaryTree<K, V> tree = new BalanceableBinaryTree<> ();
//
//    public TreeMapMurat() {
//        super();
//        tree.addRoot(null);
//    }
//
//    public int size() {return (tree.size() - 1) / 2; }
//
//    private void expandExternal(Position<Entry<K, V>> p, Entry<K, V> entry) {
//        tree.set(p, entry);
//        tree.addLeft(p, null);
//        tree.addRight(p, null);
//    }
//
//    protected Position<Entry<K, V>> root() {return tree.root(); }
//
//    private Position<Entry<K, V>> treeSearch(Position<Entry<K, V>> p, K key) {
//        if (tree.isExternal(p))
//            return p;
//        int comp = compare(key, p.getElement());
//        if (comp == 0)
//            return p;
//        else if (comp < 0)
//            return treeSearch(tree.left(p), key);
//        else
//            return treeSearch(tree.right(p), key);
//    }
//
//    public V get(K key) throws IllegalArgumentException {
//        checkKey(key);
//        Position<Entry<K, V>> p = treeSearch(root(), key);
//        rebalanceAccess(p);
//        if (tree.isExternal(p)) return null;
//        return p.getElement().getValue();
//    }
//
//    public V put(K key, V value) throws IllegalArgumentException {
//        checkKey(key);
//        Entry<K, V> newEntry = new MapEntry<>(key, value);
//        Position<Entry<K, V>> p = treeSearch(root(), key);
//        if (tree.isExternal(p)) {
//            expandExternal(p, newEntry);
//            rebalanceInsert(p);
//            return null;
//        }else {
//            V old = p.getElement().getValue();
//            tree.set(p, newEntry);
//            tree.rebalanceAccess(p);
//            return old;
//        }
//    }
//
//    public V remove(K key) throws IllegalArgumentException {
//        checkKey(key);
//        Position<Entry<K, V>> p = treeSearch(root(), key);
//        if (tree.isExternal(p)) {
//            rebalanceAccess(p);
//            return null;
//        } else {
//            V old = p.getElement().getValue();
//            if (tree.isInternal(tree.left(p)) && tree.isInternal(tree.right(p))) {
//                Position<Entry<K, V>> replacement = treeMax(tree.left(p));
//                tree.set(p, replacement.getElement());
//                p = replacement;
//            }
//            Position<Entry<K, V>> leaf = (tree.isExternal(tree.left(p)) ? tree.left(p) : tree.right(p));
//            Position<Entry<K, V>> sib = tree.sibling(leaf);
//            remove(leaf);
//            remove(p);
//            rebalanceDelete(sib);
//            return old;
//        }
//    }
//
//    protected Position<Entry<K, V>> treeMax(Position<Entry<K, V>> p) {
//        Position<Entry<K, V>> walk = p;
//        while (tree.isInternal(walk))
//            walk = tree.right(walk);
//        return tree.parent(walk);
//    }
//
//    public Entry<K, V> lastEntry() {
//        if (isEmpty()) return null;
//        return treeMax(root()).getElement();
//    }
//
//    public Entry<K, V> floorEntry(K key) throws IllegalArgumentException {
//        checkKey(key);
//        Position<Entry<K, V>> p = treeSearch(root(), key);
//        if (tree.isInternal(p)) return p.getElement();
//        while (!tree.isRoot(p)) {
//            if (p == tree.right(tree.parent(p)))
//                return tree.parent(p).getElement();
//            else p = tree.parent(p);
//        }
//        return null;
//    }
//
//    public Entry<K, V> lowerEntry(K key) throws IllegalArgumentException {
//        checkKey(key);
//        Position<Entry<K, V>> p = treeSearch(root(), key);
//        if (tree.isInternal(p) && tree.isInternal(tree.left(p)))
//            return treeMax(tree.left(p)).getElement();
//        while (!tree.isRoot(p)) {
//            if (p == tree.right(tree.parent(p)))
//                return tree.parent(p).getElement();
//            else
//                p = tree.parent(p);
//        }
//        return null;
//    }
//
//    public Iterable<Entry<K, V>> entrySet() {
//        ArrayList<Entry<K, V>> buffer = new ArrayList<>();
//        for (Position<Entry<K, V>> p : tree.inorder())
//            if (tree.isInternal(p)) buffer.add(p.getElement());
//        return buffer;
//    }
//
//    public Iterable<Entry<K, V>> subMap(K fromKey, K toKey) {
//        ArrayList<Entry<K, V>> buffer = new ArrayList<>(size());
//        if (compare(fromKey, toKey) < 0)
//            subMapRecurse(fromKey, toKey, root(), buffer);
//        return buffer;
//    }
//
//    private void subMapRecurse( K fromKey, K toKey, Position<Entry<K, V>> p, ArrayList<Entry<K, V>>buffer) {
//        if (tree.isInternal(p))
//            if (compare(p.getElement(), fromKey) < 0)
//                subMapRecurse(fromKey, toKey, tree.right(p), buffer);
//            else {
//                subMapRecurse(fromKey, toKey, tree.left(p), buffer);
//                if (compare(p.getElement(), toKey) < 0) {
//                    buffer.add(p.getElement());
//                    subMapRecurse(fromKey, toKey, tree.right(p), buffer);
//                }
//            }
//
//    }
//
//    @Override
//    public Entry<K, V> firstEntry() {
//        return null;
//    }
//
//    @Override
//    public Entry<K, V> ceilingEntry(K key) throws IllegalArgumentException {
//        return null;
//    }
//
//    @Override
//    public Entry<K, V> higherEntry(K key) throws IllegalArgumentException {
//        return null;
//    }
//}

