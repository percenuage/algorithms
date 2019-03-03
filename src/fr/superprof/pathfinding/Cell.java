package fr.superprof.pathfinding;

import java.util.ArrayList;
import java.util.List;

public class Cell extends Node<Cell> {
    private Integer row;
    private Integer col;
    private Type type;
    private Robot robot;
    private AbstractBoard board;

    public Cell(Integer row, Integer col, AbstractBoard board) {
        this(row, col, Type.ROAD, board);
    }

    public Cell(Integer row, Integer col, Type type, AbstractBoard board) {
        this.row = row;
        this.col = col;
        this.type = type;
        this.board = board;
    }

    public Cell getRelativeCell(Direction direction) {
        switch (direction) {
            case UP:
                return this.board.getRelativeCell(this, -1, 0);
            case RIGHT:
                return this.board.getRelativeCell(this, 0, 1);
            case DOWN:
                return this.board.getRelativeCell(this, 1, 0);
            case LEFT:
                return this.board.getRelativeCell(this, 0, -1);
            default:
                return this;
        }
    }

    public Direction getDirection(Cell adjacent) {
        if (!getAdjacents().contains(adjacent)) {
            throw new IllegalArgumentException("Adjacent arg must be an adjacent cell.");
        }
        if (getRelativeCell(Direction.UP).equals(adjacent)) {
            return Direction.UP;
        } else if (getRelativeCell(Direction.RIGHT).equals(adjacent)) {
            return Direction.RIGHT;
        } else if (getRelativeCell(Direction.DOWN).equals(adjacent)) {
            return Direction.DOWN;
        } else if (getRelativeCell(Direction.LEFT).equals(adjacent)) {
            return Direction.LEFT;
        } else {
            return Direction.IDLE;
        }
    }

    public List<Cell> getAdjacents() {
        List<Cell> adj = new ArrayList<>();
        if (getRelativeCell(Direction.UP).canBeCrossed()) {
            adj.add(getRelativeCell(Direction.UP));
        }
        if (getRelativeCell(Direction.RIGHT).canBeCrossed()) {
            adj.add(getRelativeCell(Direction.RIGHT));
        }
        if (getRelativeCell(Direction.DOWN).canBeCrossed()) {
            adj.add(getRelativeCell(Direction.DOWN));
        }
        if (getRelativeCell(Direction.LEFT).canBeCrossed()) {
            adj.add(getRelativeCell(Direction.LEFT));
        }
        return adj;
    }

    public Boolean canBeCrossed() {
        return this.type != Type.BLOCK;
    }

    public String getAscii() {
        return this.type.toString();
    }

    @Override
    protected Integer computeDistance(Cell cell) {
        return Math.abs(cell.getRow() - this.row) + Math.abs(cell.getCol() - this.col);
    }

    @Override
    public String toString() {
        return super.toString() + "@(" + this.row + ":" + this.col + ")";
    }

    public Integer getRow() {
        return row;
    }

    public Integer getCol() {
        return col;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public Type getType() {
        return type;
    }

}
