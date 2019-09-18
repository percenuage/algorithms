import fr.superprof.bst.StringBST;
import fr.superprof.matrix.Matrix;
import fr.superprof.pathfinding.Board;

public class Main {

    public static void main(String[] args) {
        startBst();
//        startPathFindind();
//        startMatrix();
    }

    public static void startBst() {
        System.out.println("BINARY SEARCH TREE");
        StringBST bst = new StringBST();
        bst.add("Jang");
        bst.add("Anna");
        bst.add("Pol");
        bst.add("Boris");
        bst.add("Claudio");
        bst.add("Pler");
        bst.add("Marc");
        bst.add("Mario");
        bst.add("Sam");
        bst.setMode(StringBST.PREORDER);
        System.out.println(bst);
        bst.remove("Pol");
        System.out.println(bst);
    }

    public static void startPathFindind() {
        System.out.println("PATH FINDING");
        Board board = new Board();
        System.out.println(board);
        board.play();
        System.out.println(board);
    }

    public static void startMatrix() {
        System.out.println("MATRIX");
        Matrix matrix = new Matrix(4, "ADPUIKJZNADXTLMP");
        System.out.println(matrix);
        String word = "ADPU";
        System.out.println(word + " : " + matrix.contains(word));
    }
}
