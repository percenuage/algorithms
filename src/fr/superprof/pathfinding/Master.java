package fr.superprof.pathfinding;

public class Master {
    private static final int MAX_ITERATION = 100;

    private Board board;

    private Master() {}

    private static class SingletonHolder {
        private final static Master instance = new Master();
    }

    public static Master getInstance() {
        return SingletonHolder.instance;
    }

    public void start() {
        int turn = 0;
        while (!this.board.isFinished() && turn++ < MAX_ITERATION) {
            this.board.moveRobot(handleRobot());
        }
        if (this.board.isFinished()) {
            System.out.println("You have arrived in " + turn + " turn");
        } else {
            System.out.println("Your have not arrived in time...");
        }
    }

    private Direction handleRobot() {
        //TODO
        return Direction.IDLE;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
