package fr.superprof.pathfinding;

public class Board {
    private static final String SHAPE =
            "        " +
            "   x    " +
            "   xxx  " +
            " S xFxx " +
            "   x    ";

    private Integer rows;
    private Integer cols;
    private Cell[][] cells;
    private Robot robot;
    private Cell start;
    private Cell end;

    public Board(Integer rows, Integer cols) {
        this.rows = rows;
        this.cols = cols;
        this.cells = new Cell[rows][cols];
        this.init();
        this.initRobot();
    }

    protected void moveRobot(Direction direction) {
        if (this.robot.canMove(direction)) {
            this.robot.moveTo(direction);
        } else {
            System.out.println("Robot can't be moved this way!");
        }
    }

    protected Cell getRelativeCell(Cell origin, Integer row, Integer col) {
        try {
            return this.cells[origin.getRow() + row][origin.getCol() + col];
        } catch (ArrayIndexOutOfBoundsException e) {
            return origin;
        }
    }

    protected Boolean isFinished() {
        return this.robot.getCell().equals(this.end);
    }

    private void init() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                Type type = Type.fromChar(SHAPE.charAt(this.cols * i + j));
                this.cells[i][j] = new Cell(i, j, type, this);
                if (type == Type.START) {
                    this.start = this.cells[i][j];
                }
                if (type == Type.END) {
                    this.end = this.cells[i][j];
                }
            }
        }
    }

    private void initRobot() {
        if (this.start != null) {
            if (this.robot != null) {
                this.robot.getCell().setRobot(null);
            }
            this.robot = new Robot(this.start);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                sb.append("[");
                if (this.cells[i][j].getRobot() != null && this.cells[i][j].getType() == Type.ROAD) {
                    sb.append(this.cells[i][j].getRobot());
                } else {
                    sb.append(this.cells[i][j]);
                }
                sb.append("]");
            }
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
