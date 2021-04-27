package othello;


import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;



public class HumanPlayer implements Player {

    private int _row;
    private int _col;
    private Pane _othello;
    private Referee _referee;
    private Board _board;
    private Color _color;

    //stars the Human Player; takes in a referee, a pane, a board, and a color
    public HumanPlayer(Referee referee, Pane othello, Board board, Color color) {

        _board = board;
        _othello = othello;
        _referee = referee;
        _color = color;
    }


    //starts the return by adding grey squares where a move can be made and then calling the click handler
    @Override
    public void moveOver() {

        this.addGrey(_color);
        _othello.setOnMouseClicked(new HumanPlayer.ClickHandler());
        _othello.setFocusTraversable(true);

    }


    //adds Grey squares where the current Player has valid moves
    public void addGrey(Color color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {


                if (_referee.moveValidity(i, j, color, _board))
                    _board.getArray()[i][j].changeSquareColor();

            }
        }
    }


    //removes all grey squares from the board
    public void removeGrey() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                _board.getArray()[i][j].changeColorBack();
            }
        }
    }

    //takes in a mouse click
    private class ClickHandler implements EventHandler<MouseEvent> {

        public ClickHandler() {
        }


        @Override
        public void handle(MouseEvent event) {


            //does nothing if the user clicks outside of the othello board
            if (event.getY() >400 || event.getX() > 400){

            }

            //if the user clicks within the board, if they click on a valid square, the piece is
            //added and the pieces flip and then the turn ends
            //after the turn is ended, the grey squares are all removed
            else
            {
                _row = (int) (event.getY() / 50);
                _col = (int) (event.getX() / 50);
                _referee.moveValidity(_row, _col, _color, _board);

                if (_referee.moveValidity(_row, _col, _color, _board)) {

                    _board.getArray()[_row][_col].addPiece(_row, _col, _color);

                    _referee.flip(_row, _col, _color, _board);

                    _referee.endTurn();
                    HumanPlayer.this.removeGrey();
                }

                else if (_referee.cantMove(_color, _board))
                    _referee.endTurn();


            }


        }
    }
}