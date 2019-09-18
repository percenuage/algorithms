package fr.superprof.bst;

public class Node {

    protected String data;
    protected Node left, right;

    public Node(String data){
        this.data = data;
    }

    public int compareTo(Object o){
        return data.compareTo(o.toString());
    }
}
