package othello;

public class Move {

    private int _row;
    private int _col;
    private int _value;
    public Move(int row, int col, int value){
        _row = row;
        _col = col;
        _value = value;


    }

    public int getValue(){
        return _value;
    }

    public int getRow(){
        return _row;
    }
    public int getCol(){
        return _col;
    }
}
