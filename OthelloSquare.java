package othello;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class OthelloSquare {

    //delayed association- piece instance variable-set piece to null in constructor
    //method that then sets up piece and adds to board
    private Rectangle _square;
    private Pane _pane;
    private Piece[][] _pieces;

    public OthelloSquare(Pane othello) {
        _pieces = new Piece[8][8];
        _pane = othello;
        _square = new Rectangle(50, 50);
        _square.setFill(Color.WHITE);
        _square.setStroke(Color.BLACK);
        this.setupBoardwithPieces();

    }

    public Rectangle getSquare() {
        return _square;
    }

    public void setXY(double x, double y) {
        _square.setX(x);
        _square.setY(y);
    }


    public void setupBoardwithPieces() {


        _pieces[3][3]= new Piece();
        _pieces[3][3].setLocation(3,3);
        _pane.getChildren().add(_pieces[3][3].getCircle());

        _pieces[4][4]= new Piece();
        _pieces[4][4].setLocation(4,4);
        _pane.getChildren().add(_pieces[4][4].getCircle());

        _pieces[3][4]= new Piece();
        _pieces[3][4].setColor(Color.GREEN);
        _pieces[3][4].setLocation(3,4);
        _pane.getChildren().add(_pieces[3][4].getCircle());

        _pieces[4][3]= new Piece();
        _pieces[4][3].setColor(Color.GREEN);
        _pieces[4][3].setLocation(4,3);
        _pane.getChildren().add(_pieces[4][3].getCircle());

       /* for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                _pieces[i][j] = new Piece();
                _pieces[i][j].setLocation(j, i);
                _pane.getChildren().add(_pieces[i][j].getCircle());
            }
        }

        Piece piece = new Piece();
        piece.setLocation(8, 8);
        _pane.getChildren().add(piece.getCircle());

*/
    }

    public void addPiece(int x, int y){

        _pieces[y][x]= new Piece();
        _pieces[y][x].setLocation(y, x);
        _pane.getChildren().add(_pieces[y][x].getCircle());
    }


}

//game starts after apply settings
//controls knows what type of player
//game class will make players
//will pass that info to referee, determines when to move, controls timeline, player tells referee when game is over
//referee class, instance variable (whiteplayer, blackplayer), method that sets them up,game should reference referee
//in referee method take two players from the game class
//players and referree- double association
//1236 ClickHandler