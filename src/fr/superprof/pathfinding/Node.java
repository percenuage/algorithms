package fr.superprof.pathfinding;

public abstract class Node<E> implements Comparable<Node> {
    private Integer cost;
    private Integer heuristic;
    private E parent;

    Node() {
        this.cost = 0;
        this.heuristic = 0;
    }

    void updateCost() {
        if (this.parent == null) throw new IllegalArgumentException("Must be a parent.");
        this.cost = ((Node)parent).getCost() + computeDistance(parent);
    }

    void updateHeuristique(E e) {
        this.heuristic = computeDistance(e);
    }

    protected abstract Integer computeDistance(E e);

    private Integer getScore() {
        return this.cost + this.heuristic;
    }

    @Override
    public int compareTo(Node o) {
        if (this.getScore() - o.getScore() != 0) {
            return this.getScore() - o.getScore();
        } else {
            return this.heuristic - o.getHeuristic();
        }
    }

    @Override
    public String toString() {
        return "{G:" + this.getCost() + ",H:" + this.getHeuristic() + ",F:" + this.getScore() + "}";
    }

    Integer getCost() {
        return cost;
    }

    Integer getHeuristic() {
        return heuristic;
    }

    E getParent() {
        return parent;
    }

    void setParent(E parent) {
        this.parent = parent;
    }
}
