package othello;


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
public class OthelloSquare {

    private Rectangle _square;
    private Pane _pane;
    private Piece[][] _pieces;

    //takes in a Pane to add the pieces to
    public OthelloSquare(Pane othello) {

        //creates an array of Pieces
        _pieces = new Piece[8][8];
        _pane = othello;
        _square = new Rectangle(50, 50);
        _square.setFill(Color.WHITE);
        _square.setStroke(Color.BLACK);
    }

    //changes square fill to white
    public void changeSquareColor(){
        _square.setFill(Color.GREY);
    }

    //changes square fill to white
    public void changeColorBack(){
        _square.setFill(Color.WHITE);
    }

    //returns square
    public Rectangle getSquare() {
        return _square;
    }

    //sets the X and Y location of the square
    public void setXY(double x, double y) {
        _square.setX(x);
        _square.setY(y);
    }


    //adds circle pieces to the board by setting their location to the row and col
    //and setting their color to the color taken in as a parameter
    public void addPiece(int row, int col, Color color){
        _pieces[row][col]= new Piece();
        _pieces[row][col].setLocation(row, col);
        _pieces[row][col].setColor(color);
        _pane.getChildren().add(_pieces[row][col].getCircle());
    }

    //removes a piece from the board both logically and graphically
    public void removePiece(int row, int col){
        if(_pieces[row][col]!=null){
        _pane.getChildren().remove(_pieces[row][col].getCircle());
        _pieces[row][col]=null;}
    }

    //returns a piece at the specified row and col
    public Piece returnPiece(int row, int col){
        return _pieces[row][col];
    }

    //returns the color of a specific piece
    public Color returnColor(int row, int col){
        return _pieces[row][col].getColor();
    }

    //sets the color of a specific piece
    public void flipColor(int row, int col, Color color){

        _pieces[row][col].getCircle().setFill(color);
    }


}
