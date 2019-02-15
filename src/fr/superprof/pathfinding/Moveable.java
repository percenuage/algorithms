package fr.superprof.pathfinding;

public interface Moveable {
    void moveTo(Direction direction);
    Boolean canMove(Direction direction);
}
