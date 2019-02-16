import fr.superprof.matrix.Matrix;
import fr.superprof.pathfinding.Board;

public class Main {

    public static void main(String[] args) {
        startPathFindind();
//        startMatrix();
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
