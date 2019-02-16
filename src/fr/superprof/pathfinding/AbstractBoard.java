package fr.superprof.pathfinding;

public abstract class AbstractBoard {
    private static final int ROWS = 5;
    private static final int COLS = 8;
    private static final int MAX_ITERATIONS = 100;
    private static final String SHAPE =
            "        " +
            "   x    " +
            "   xxx  " +
            " S xFxx " +
            "   x    ";
    protected static final Direction[] SHORTEST_PATH = {
            Direction.UP, Direction.UP, Direction.UP, Direction.RIGHT,
            Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT,
            Direction.RIGHT, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN,
            Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.UP
    };
    protected static int turn = 0;

    private Integer rows;
    private Integer cols;
    private Cell[][] cells;
    private Robot robot;
    private Cell start;
    private Cell end;

    public AbstractBoard() {
        this(ROWS, COLS);
    }

    public AbstractBoard(Integer rows, Integer cols) {
        this.rows = rows;
        this.cols = cols;
        this.cells = new Cell[rows][cols];
        this.init();
        this.initRobot();
    }

    public final void play() {
        while (!this.isFinished() && turn++ < MAX_ITERATIONS) {
            this.moveRobot(handleRobot());
        }
        if (this.isFinished()) {
            System.out.println("You have arrived in " + turn + " turn");
        } else {
            System.out.println("Your have not arrived in time...");
        }
    }

    protected final void moveRobot(Direction direction) {
        if (this.robot.canMove(direction)) {
            this.robot.moveTo(direction);
        } else {
            System.out.println("Robot can't be moved this way!");
        }
    }

    abstract protected Direction handleRobot();

    protected final Cell getRelativeCell(Cell origin, Integer row, Integer col) {
        try {
            return this.cells[origin.getRow() + row][origin.getCol() + col];
        } catch (ArrayIndexOutOfBoundsException e) {
            return origin;
        }
    }

    protected final Boolean isFinished() {
        return this.robot.getCell().equals(this.end);
    }

    private final void init() {
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

    private final void initRobot() {
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

    public Integer getRows() {
        return rows;
    }

    public Integer getCols() {
        return cols;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public Robot getRobot() {
        return robot;
    }

    public Cell getStart() {
        return start;
    }

    public Cell getEnd() {
        return end;
    }
}
