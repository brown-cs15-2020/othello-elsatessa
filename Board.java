package othello;

import javafx.scene.layout.Pane;

public class Board {

    private OthelloSquare[][] _board;
    private Pane _pane;

    public Board(Pane pane){

        _board = new OthelloSquare[8][8];
        _pane = pane;
        this.setUpBoard();
    }

    public void changeSquareColor(int row, int col){
        _board[row][col].changeSquareColor();
    }
    public void changeColorBack(int row, int col){
        _board[row][col].changeColorBack();
    }


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

    public OthelloSquare[][] getArray(){

        return _board;
    }
}
