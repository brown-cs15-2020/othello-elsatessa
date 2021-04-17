package othello;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Game {


    private Pane _pane;
    private Board _board;
    private Player _whitePlayer;
    private Player _blackPlayer;
    private Referee _ref;
    public Game(Pane Othello){

        _pane = Othello;
        _board = new Board(_pane);
        _ref = new Referee();


      //  _whitePlayer = new HumanPlayer(_ref, _pane);
      //  _blackPlayer = new HumanPlayer(_ref, _pane);

       //. this.startGame(0, 0);


    }

   public void startGame(int white, int black){

        //make first player move, then tell referee that it's done (inside moveOver), referee will end turn
        if (white==0)
            _whitePlayer = new HumanPlayer(_ref, _pane, _board);
            _whitePlayer.moveOver();
        if (white!=0)
            _whitePlayer = new ComputerPlayer(_ref);
        if (black==0)
            _blackPlayer = new HumanPlayer(_ref, _pane, _board);
        _blackPlayer.moveOver();
        if(black!=0)
            _blackPlayer = new ComputerPlayer(_ref);

        _ref.takePlayer(_whitePlayer, _blackPlayer);

    }


   /* private class Clicker implements ActionEvent<MouseEvent>{

        private Clicker(){

        }

        public void handle(MouseEvent e){
            MouseButton mouseButton =
        }

    }*/

}