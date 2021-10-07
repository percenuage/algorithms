package fr.superprof.matrix;

public class Matrix {
    private Letter[][] letters;
    private Integer size;

    public Matrix(Integer size, String letters) {
        if (size * size != letters.length())
            throw new IllegalArgumentException("Size must be the root of letters length");
        this.size = size;
        this.letters = new Letter[size][size];
        this.init(letters);
    }

    private void init(String letters) {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.letters[i][j] = new Letter(letters.charAt(this.size * i + j), i, j);
            }
        }
    }

    public Boolean contains(String word) {
        Letter current, next;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                current = this.letters[i][j];
                if (current.equals(word.charAt(0))) { // First letter found
                    for (int n = 0; n < word.length() - 1; n++) {
                        next = new Letter(word.charAt(n + 1));
                        if (this.isAdjacent(current, next)) {
                            current = next;
                        } else {
                            //TODO
                        }
                    }
                }
            }
        }
        return false;
    }

    public Letter getAdjacent(Letter current, Letter next) {
        if (this.getRelativeLetter(current, 0, 1).equals(next)) {
            //TODO
        }
        return null;
    }

    public Boolean isAdjacent(Letter current, Letter next) {
        return this.getRelativeLetter(current, 0, 1).equals(next)
                || this.getRelativeLetter(current, 0, -1).equals(next)
                || this.getRelativeLetter(current, 1, 0).equals(next)
                || this.getRelativeLetter(current, 1, 1).equals(next)
                || this.getRelativeLetter(current, 1, -1).equals(next)
                || this.getRelativeLetter(current, -1, 0).equals(next)
                || this.getRelativeLetter(current, -1, 1).equals(next)
                || this.getRelativeLetter(current, -1, -1).equals(next);
    }

    public Letter getRelativeLetter(Letter current, Integer deltaRow, Integer deltaCol) {
        try {
            return this.letters[current.getRow() + deltaRow][current.getCol() + deltaCol];
        } catch (ArrayIndexOutOfBoundsException e) {
            return new Letter();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                sb.append(this.letters[i][j]).append(" ");
            }
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
