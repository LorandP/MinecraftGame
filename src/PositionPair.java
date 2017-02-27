/**
 * Created by Hermes on 27/02/2017.
 */
class PositionPair<Line, Col> {
    private Line line;
    private Col col;

    public PositionPair(Line line, Col col){
        this.col = col;
        this.line = line;
    }
    public Line getLine()
    {
        return line;
    }
    public Col getCol()
    {
        return col;
    }
    public void setLine(Line line){
        this.line = line;
    }
    public void setCol(Col col){
        this.col = col;
    }
}
