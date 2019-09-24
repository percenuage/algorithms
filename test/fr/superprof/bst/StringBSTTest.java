package fr.superprof.bst;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringBSTTest {

    StringBST bst = new StringBST();

    @BeforeEach
    void setUp() {
        bst.clear();
        bst.add("Jang");
        bst.add("Anna");
        bst.add("Pol");
        bst.add("Boris");
        bst.add("Claudio");
        bst.add("Pler");
        bst.add("Marc");
        bst.add("Mario");
        bst.add("Sam");
    }

    @Test
    void toStringTest() {
        bst.setMode(StringBST.PREORDER);
        System.out.println(bst);
        bst.remove("Pol");
        System.out.println(bst);
    }
}