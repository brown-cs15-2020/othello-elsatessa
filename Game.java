package othello;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Game {


    private Label b1;
    private Label b2;
    private Pane _pane;
    private Board _board;
    private Player _whitePlayer;
    private Player _blackPlayer;
    private Referee _ref;

    //takes in the Game pane and places the board on the Game pane
    public Game(Pane Othello){

        _pane = Othello;
        _board = new Board(_pane);
        _ref = new Referee(_board);

    }

    //starts the Game
   public void startGame(int white, int black){

        if (white==0)
         _whitePlayer = new HumanPlayer(_ref, _pane, _board, Color.GREEN);

        if (white!=0)
            _whitePlayer = new ComputerPlayer(_ref, white, _board, Color.GREEN);

        if (black==0)
         _blackPlayer = new HumanPlayer(_ref, _pane, _board, Color.MAGENTA);

        if(black!=0)
            _blackPlayer = new ComputerPlayer(_ref, black, _board, Color.MAGENTA);

        _ref.takePlayer(_whitePlayer, _blackPlayer);

    }

    //resets the board
    public void reset(){
        _ref.clearBoard();
    }

        //sets up the Labels for the Score, Current Player and Game Over
        public void setupLabels (VBox labelPane){

        b1 = new Label("Pink: " + _ref.PinkScore(_board) + " Green: " + _ref.GreenScore(_board));
           b2 = new Label("Current Player: " + _ref.currentPlayer());
            labelPane.getChildren().addAll(b1, b2);
            if(!_ref.gameOver(_board))
                b2.setText("GameOver");

        }


}