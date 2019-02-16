package fr.superprof.pathfinding;

public class Robot implements Moveable {
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

    @Override
    public Boolean canMove(Direction direction) {
        return this.cell.getRelativeCell(direction).canBeCrossed();
    }

    @Override
    public String toString() {
        return ".";
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}
