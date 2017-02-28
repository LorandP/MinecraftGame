/**
 * Created by Hermes on 27/02/2017.
 */
class PositionPair {
    private int line;
    private int column;

    public PositionPair(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
