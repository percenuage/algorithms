package fr.superprof.pathfinding;

public class Robot implements Moveable {
    public static final Character ASCII = '.';
    private static final Boolean CLEAR_PATH = false;
    private Cell cell;

    public Robot(Cell cell) {
        this.cell = cell;
        cell.setRobot(this);
    }

    public Cell getRelativeCell(Direction direction) {
        return this.cell.getRelativeCell(direction);
    }

    @Override
    public void moveTo(Direction direction) {
        if (CLEAR_PATH) {
            this.cell.setRobot(null);
        }
        this.cell = this.getRelativeCell(direction);
        this.cell.setRobot(this);
    }

    public String getAscii() {
        return ASCII.toString();
    }

    @Override
    public Boolean canMove(Direction direction) {
        return this.cell.getRelativeCell(direction).canBeCrossed();
    }

    @Override
    public String toString() {
        return this.cell.toString();
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}
