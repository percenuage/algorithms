import fr.superprof.matrix.Matrix;
import fr.superprof.pathfinding.Board;
import fr.superprof.pathfinding.Master;

public class Main {

    public static void main(String[] args) {
        startPathFindind();
//        startMatrix();
    }

    public static void startPathFindind() {
        System.out.println("PATH FINDING");
        Master master = Master.getInstance();
        Board board = new Board(5, 8);
        master.setBoard(board);
        System.out.println(board);
        master.start();
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
