package fr.superprof.pathfinding;

public class Cell {
    private Integer row;
    private Integer col;
    private Type type;
    private Robot robot;
    private Board board;

    public Cell(Integer row, Integer col, Board board) {
        this(row, col, Type.ROAD, board);
    }

    public Cell(Integer row, Integer col, Type type, Board board) {
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

    public Boolean canBeCrossed() {
        return this.type != Type.BLOCK;
    }

    @Override
    public String toString() {
        return this.type.toString();
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
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
