package othello;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Board {

    private OthelloSquare[][] _board;
    private Pane _pane;

    public Board(Pane pane){

        //creates board that is a 2D array of Othello Squares
        _board = new OthelloSquare[8][8];
        _pane = pane;
        this.setUpBoard();
    }

    //creates a copy board constructor that takes in a board as a parameter and creates an exact
    //replica of the board logically, but not graphically
    public Board (Board board){
        _board = new OthelloSquare[8][8];
         _pane = new Pane();
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++){


                OthelloSquare square = new OthelloSquare(_pane);
                square.setXY(j*50, i*50);
                _board[i][j] = square;

                if(board.getArray()[i][j].returnPiece(i, j)!=null){
                  Color color =  board.getArray()[i][j].returnColor(i,j);

                  _board[i][j].addPiece(i, j, color);
                }

            }
        }

    }

    //sets up the board graphically and logically with 64 othello squares
    private void setUpBoard(){
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){

                OthelloSquare square = new OthelloSquare(_pane);
                square.setXY(j*50, i*50);
                _board[i][j] = square;
                _pane.getChildren().add(square.getSquare());
            }
        }
    }

    //returns the 2D othello square array
    public OthelloSquare[][] getArray(){

        return _board;
    }
}
