package othello;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class OthelloSquare {

    //delayed association- piece instance variable-set piece to null in constructor
    //method that then sets up piece and adds to board
    private Rectangle _square;
    private Pane _pane;
    public OthelloSquare(Pane othello){
        _pane = othello;
        _square = new Rectangle(50, 50);
        _square.setFill(Color.WHITE);
        _square.setStroke(Color.BLACK);
        this.addCircle();

    }

    public Rectangle getSquare(){
        return _square;
    }

    public void setXY(double x, double y){
        _square.setX(x);
        _square.setY(y);
    }

    //returns true if there is a move available
    public boolean canMove(){

    }

    //returns true if a move is valid
    public boolean moveValidity(){

    }

    //returns true if the game is over
    public boolean gameOver(){

    }

    public void addCircle(){

        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){

                Piece piece = new Piece();
                piece.setLocation(j, i);
                _pane.getChildren().add(piece.getCircle());
            }
        }

        Piece piece = new Piece();
        piece.setLocation(8,8);
        _pane.getChildren().add(piece.getCircle());


    }

    public class ClickHanlder implements EventHandler<ActionEvent>{

        public ClickHanlder(){}


        @Override
        public void handle(ActionEvent event) {

            OthelloSquare.this.addCircle();
        }
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