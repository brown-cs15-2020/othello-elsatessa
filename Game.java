package othello;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Game {

    private Pane _pane;
    private Board _board;
    public Game(Pane Othello){

        _pane = Othello;
        _board = new Board(_pane);


    }

    public void startGame(int white, int black){


    }


   /* private class Clicker implements ActionEvent<MouseEvent>{

        private Clicker(){

        }

        public void handle(MouseEvent e){
            MouseButton mouseButton =
        }

    }*/

}