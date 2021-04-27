package othello;

public class Move {

    private int _row;
    private int _col;
    private int _value;

    //takes in a row, col, and value
    public Move(int row, int col, int value){
        _row = row;
        _col = col;
        _value = value;


    }

    //returns value
    public int getValue(){
        return _value;
    }

    //returns row
    public int getRow(){
        return _row;
    }

    //returns col
    public int getCol(){
        return _col;
    }
}
