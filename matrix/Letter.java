package fr.superprof.matrix;

public class Letter {
    private Character letter;
    private Integer row;
    private Integer col;

    public Letter() {
        this('\0');
    }

    public Letter(Character letter) {
        this(letter, -1, -1);
    }

    public Letter(Character letter, Integer row, Integer col) {
        this.letter = letter;
        this.row = row;
        this.col = col;
    }

    public Boolean isAdjacent(Character letter, Integer row, Integer col) {
        return isAdjacent(new Letter(letter, row, col));
    }

    public Boolean isAdjacent(Letter letter) {
        return (letter.getRow().equals(this.row - 1) && letter.getCol().equals(this.col))
                || (letter.getRow().equals(this.row - 1) && letter.getCol().equals(this.col + 1))
                || (letter.getRow().equals(this.row - 1) && letter.getCol().equals(this.col - 1))
                || (letter.getRow().equals(this.row + 1) && letter.getCol().equals(this.col))
                || (letter.getRow().equals(this.row + 1) && letter.getCol().equals(this.col + 1))
                || (letter.getRow().equals(this.row + 1) && letter.getCol().equals(this.col - 1))
                || (letter.getRow().equals(this.row) && letter.getCol().equals(this.col + 1))
                || (letter.getRow().equals(this.row) && letter.getCol().equals(this.col - 1));
    }

    @Override
    public boolean equals(Object obj) {
        Letter letter = (Letter) obj;
        return this.getLetter().equals(letter.getLetter());
    }

    @Override
    public String toString() {
        return this.letter.toString();
    }

    public Character getLetter() {
        return letter;
    }

    public void setLetter(Character letter) {
        this.letter = letter;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }
}
