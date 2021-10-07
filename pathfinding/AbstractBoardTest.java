package fr.superprof.pathfinding;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractBoardTest {

    Board board = new Board();

    @Test
    void play() {
        System.out.println(board);
        board.play();
        System.out.println(board);
    }
}