package fr.superprof.pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Board extends AbstractBoard {

    private static List<Direction> shortestPath;

    private void computeAstar() {
        ArrayList<Cell> closeList = new ArrayList<>();
        PriorityQueue<Cell> openList = new PriorityQueue<>();
        openList.add(getStart());

        while (!openList.isEmpty()) {
            Cell current = openList.poll();
            closeList.add(current);

            if (current.equals(getEnd())) {
                buildShortestPath();
                break; // Rebuild Path
            }

            for (Cell next : current.getAdjacents()) {
                if (closeList.contains(next) || (openList.contains(next) && next.getCost() <= current.getCost() + 1)) {
                    continue;
                }

                next.setParent(current);
                next.updateCost();
                next.updateHeuristique(getEnd());

                if (!openList.contains(next)) {
                    openList.add(next);
                }
            }
        }
    }

    private void buildShortestPath() {
        List<Cell> cells = new ArrayList<>();
        List<Direction> directions = new ArrayList<>();
        Cell current = getEnd();
        while (current.getParent() != null) {
            cells.add(current);
            directions.add(current.getParent().getDirection(current));
            current = current.getParent();
        }
        Collections.reverse(directions);
        Collections.reverse(cells);
        shortestPath = directions;
//        System.out.println(directions);
//        System.out.println(toStringFromCells(cells));
    }

    @Override
    protected Direction handleRobot() {
        // TODO
        if (turn == 1) {
            computeAstar();
        }
        return shortestPath.get(turn - 1);
    }

}
