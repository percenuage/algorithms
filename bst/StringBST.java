package fr.superprof.bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringBST {

    private Node root;
    private int mode;
    private boolean elementRemoved;

    public static final int PREORDER = -1;
    public static final int INORDER = 0;
    public static final int POSTORDER = 1;

    private void updateObservers() {
        //todo
    }

    public Node getRoot() {
        return root;
    }
    public void setMode(int mode) {
        this.mode = mode;
    }
    public int getMode() {
        return mode;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean add(String data) {
        this.root = addRecursion(root, data);
        return true;
    }

    private Node addRecursion(Node n, String data){
        if (n == null) {
            n = new Node(data);
        } else if (n.compareTo(data) > 0) {
            n.left = addRecursion(n.left, data);
        } else {
            n.right = addRecursion(n.right, data);
        }
        return n;
    }

    public void clear() {
        this.root = null;
    }

    public boolean contains(String data){
        return containsRecursion(this.root, data);
    }

    private boolean containsRecursion(Node n, String data){
        if (n == null) {
            return false;
        }
        if (n.data.equals(data)) {
            return true;
        } else {
            return containsRecursion(n.left, data) || containsRecursion(n.right, data);
        }
    }

    public boolean isLeaf(Node n) {
        return n != null && n.right == null && n.left == null;
    }

    public boolean remove(String data) {
        return removeRecursion(this.root, data);
    }

    private boolean removeRecursion(Node n, String data) {
        Object[] array = new Object[size()];
        toArrayPreorderRecursion(this.root, array, 0);
        List<Object> list = new ArrayList<>(Arrays.asList(array));
        list.remove(data);
        clear();
        for (Object s : list) {
            add((String)s);
        }
        return true;
    }

    public int size(){
        return sizeRecursion(this.root);
    }

    private int sizeRecursion(Node n){
        if(n == null){
            return 0;
        }
        return sizeRecursion(n.left) + sizeRecursion(n.right) + 1;
    }

    public Object[] toArray(){
        Object[] array = new Object[size()];
        if (mode == PREORDER) {
            toArrayPreorderRecursion(this.root, array, 0);
        } else if (mode == POSTORDER) {
            toArrayPostorderRecursion(this.root, array, 0);
        } if (mode == INORDER) {
            toArrayInorderRecursion(this.root, array, 0);
        }
        return array;
    }

    private int toArrayPreorderRecursion(Node n, Object[] ar, int result){
        if (n != null) {
            ar[result++] = n.data;
            result = toArrayPreorderRecursion(n.left, ar, result);
            result = toArrayPreorderRecursion(n.right, ar, result);
        }
        return result;
    }

    private int toArrayInorderRecursion(Node n, Object[] ar, int result){
        if (n != null) {
            result = toArrayPreorderRecursion(n.left, ar, result);
            ar[result++] = n.data;
            result = toArrayPreorderRecursion(n.right, ar, result);
        }
        return result;
    }

    private int toArrayPostorderRecursion(Node n, Object[] ar, int result){
        if (n != null) {
            result = toArrayPreorderRecursion(n.left, ar, result);
            result = toArrayPreorderRecursion(n.right, ar, result);
            ar[result++] = n.data;
        }
        return result;
    }

    public int getDepth() {
        return depthRecursion(this.root);

    }

    private int depthRecursion(Node n) {
        if(n == null){
            return 0;
        }
        return Math.max(sizeRecursion(n.left), sizeRecursion(n.right));
    }

    @Override
    public String toString() {
        return "Size: " + size() + " " + Arrays.toString(toArray()) + "\n" + print(root, 0);
    }

    private String print(Node n, int deph) {
        StringBuilder sb = new StringBuilder();
        String indents = String.join("", Collections.nCopies(deph, "-"));
        if (n != null) {
            sb.append(n.data);
            if (n.left != null)
                sb.append("\n").append(indents).append("L: ").append(print(n.left, deph + 1));
            if (n.right != null)
                sb.append("\n").append(indents).append("R: ").append(print(n.right, deph + 1));
        }
        return sb.toString();
    }

    public void saveToFile(String fileName) {

    }

    public void loadFromFile(String fileName) {

    }

























}
