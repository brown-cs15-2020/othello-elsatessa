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

    private Board _board;
    private Color _color;
    public HumanPlayer(Referee referee, Pane othello, Board board, Color color){

        _board = board;
        _othello = othello;
        _referee = referee;



        Circle circle = new Circle(50);
        circle.setFill(Color.RED);
        othello.getChildren().add(circle);
       _color = color;
    }

    @Override
    public boolean moveOver() {

        this.addGrey(_color);
        _othello.setOnMouseClicked(new HumanPlayer.ClickHandler());
        _othello.setFocusTraversable(true);

        return false;
    }


    public void addGrey(Color color){

        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){


                if(_referee.moveValidity(i, j, color, _board))
                    _board.getArray()[i][j].changeSquareColor();

            }
        }
    }


    public void removeGrey(Color color){

        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){

                _board.getArray()[i][j].changeColorBack();
            }
        }
    }

    private class ClickHandler implements EventHandler<MouseEvent> {

        public ClickHandler(){}


        @Override
        public void handle(MouseEvent event) {


          //  System.out.println("hello");
            _row = (int) (event.getY()/50);
            _col = (int) (event.getX()/50);
           _referee.moveValidity(_row, _col, _color, _board);

            if (_referee.moveValidity(_row, _col, _color, _board)){

              //  System.out.println("test");
            _board.getArray()[_row][_col].addPiece(_row, _col, _color);

         _referee.flip(_row, _col, _color, _board);

                _referee.endTurn();
          //  System.out.println("elsa");
                HumanPlayer.this.removeGrey(_color);
            }


        }
    }
}
