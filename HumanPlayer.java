package othello;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import tetris.Tetris;

import java.sql.Ref;


public class HumanPlayer implements Player{

    private int _row;
    private int _col;
    private Pane _othello;
    private Referee _referee;
    private OthelloSquare _othelloSquare;
    private Board _board;
    private Color _color;
    public HumanPlayer(Referee referee, Pane othello, Board board, Color color){

        _board = board;
        _othello = othello;
        _referee = referee;
        _othelloSquare = new OthelloSquare(_othello);

        Circle circle = new Circle(50);
        circle.setFill(Color.RED);
        othello.getChildren().add(circle);

       _color = color;


    }

    @Override
    public boolean moveOver() {

        _othello.setOnMouseClicked(new HumanPlayer.ClickHandler());
        _othello.setFocusTraversable(true);
        return false;
    }



    private class ClickHandler implements EventHandler<MouseEvent> {

        public ClickHandler(){}


        @Override
        public void handle(MouseEvent event) {

            System.out.println("hello");
            _row = (int) (event.getY()/50);
            _col = (int) (event.getX()/50);
            if (_referee.moveValidity(_row, _col)){
                _referee.endTurn();
            _board.getArray()[_row][_col].addPiece(_row, _col, _color);


            System.out.println("elsa");}


        }
    }
}
