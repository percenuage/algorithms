package fr.superprof.matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    private static final int SIZE = 4;
    private static final String LETTERS = "ADPUIKJZNADXTLMP";

    @Test
    void contains() {
        Matrix matrix = new Matrix(SIZE, LETTERS);
        System.out.println(matrix);
        assertEquals(true, matrix.contains("ADPU"));
    }
}