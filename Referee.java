package othello;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;


import java.sql.Ref;

public class Referee {
    private Timeline _timeline;
    private Player _white;
    private Player _black;
    private Player _currentPlayer;
    private Board _board;
    private Label _b1;
    private Label _b2;


    public Referee(Board board) {
        _board = board;
        _board.getArray()[3][3].addPiece(3,3, Color.MAGENTA);
        _board.getArray()[4][4].addPiece(4,4, Color.MAGENTA);
        _board.getArray()[4][3].addPiece(4,3, Color.GREEN);
        _board.getArray()[3][4].addPiece(3,4, Color.GREEN);

        //this.endTurn();
       // this.setupTimeLine();

        }



    public void takePlayer(Player white, Player black) {
        _white = white;
        _black = black;
        _currentPlayer = _black;
        this.setupTimeLine();
      //  this.endTurn();

    }

    public int returnPlayerr(){
        if (_currentPlayer==_white)
            return 0;
        else
            return 1;

    }


    //set up Timeline- pause and play timeline
    //set up timeline when apply settings is clicked
    private void setupTimeLine() {

        //call moveOver method
      KeyFrame kf = new KeyFrame(Duration.seconds(1), new Referee.OthelloMover());
      _timeline = new Timeline(kf);
        _timeline.setCycleCount(Animation.INDEFINITE);
        _timeline.play();

    }

    public boolean cantMove(Color color, Board board){


      for (int row=0; row<8; row++){
          for (int col=0; col<8; col++){
              if(this.moveValidity(row,col, color, board))
                  return false;
          }
      }
        return true;
    }


    public int PinkScore (Board board){
        int pink=0;

        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){

                if(board.getArray()[i][j].returnColor(i, j)==Color.MAGENTA)
                    pink++;
            }
        }
        return pink;
    }
    public int GreenScore (Board board){
        int green=0;

        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){

                if(board.getArray()[i][j].returnColor(i, j)==Color.GREEN)
                    green++;
            }
        }
        return green;
    }


    public Color whoWins(Board board){
        int black=0;
        int white=0;
        for (int row=0; row<8; row++){
            for (int col=0; col<8; col++) {

                if (board.getArray()[row][col].returnPiece(row, col) != null) {
                    if (board.getArray()[row][col].returnPiece(row, col).getColor() == Color.MAGENTA)
                        black++;
                    else if (board.getArray()[row][col].returnPiece(row, col).getColor() == Color.GREEN)
                        white++;
                }
            }

        }

        if(white>black){
            return Color.GREEN;}
        else if(black>white){
            return Color.MAGENTA;}
        else
            return Color.YELLOW;
    }

    public Color returnaColor(){
       if (_currentPlayer == _white)
          return Color.GREEN;
     else
         return Color.MAGENTA;

    }

    public String currentPlayer(){

        if (_currentPlayer == _white)
            return "Green";
        else
            return "Pink";
    }

    public void endTurn() {
      //  System.out.println("turnover");
        if (_currentPlayer == _white)
            _currentPlayer = _black;
         else
            _currentPlayer = _white;


        _timeline.play();
    }

    public Player returnPlayer(){
        return _currentPlayer;
    }

    public boolean moveValidity(int row, int col, Color color, Board board) {

        boolean move = false;

        if(board.getArray()[row][col].returnPiece(row, col)==null && this.Sandwich(row, col, color, board)) {
            move = true;
          //  _board.getArray()[row][col].changeColor(row, col, Color.GREY);
        }
        return move;

                       }



    public boolean Sandwich(int row, int col, Color color, Board board) {



        boolean flag = false;

        for (int i=-1; i<= 1; i++){
            for (int j=-1; j<= 1; j++) {


                int currentRow = row + i;
                int currentCol = col + j;
                flag = false;

                while (true) {

                  //  System.out.println("elsa");
                    //checks to see if on board
                    if (currentRow >= 0 && currentRow < 8 && currentCol >= 0 && currentCol < 8) {

                        //System.out.println("goodbye");
                        {if (board.getArray()[currentRow][currentCol].returnPiece(currentRow, currentCol) == null){

                            break;}}
                        {

                            if (board.getArray()[currentRow][currentCol].returnPiece(currentRow, currentCol) != null && board.getArray()[currentRow][currentCol].returnColor(currentRow, currentCol) != color) {
                                flag = true;
                                currentRow += i;
                                currentCol += j;
                               
                            } else//if (_board.getArray()[currentRow][currentCol].returnPiece(currentRow, currentCol) != null && _board.getArray()[currentRow][currentCol].returnPiece(currentRow, currentCol).getColor() != color) {
                            {
                                if (flag) {

                                    return true;
                                } else {
                                    break;
                                }
                            }
                        }

                    }
                    else
                        break;
                }

            }}
        return false;
    }

    public void flip(int row, int col, Color color, Board board){
        {

          //  System.out.println("flip");
            boolean flag;
            for (int i=-1; i<= 1; i++){
                for (int j=-1; j<= 1; j++) {

                    int currentRow = row + i;
                    int currentCol = col + j;
                    flag = false;

                    while (true) {

                        //  System.out.println("elsa");
                        //checks to see if on board
                        if (currentRow >= 0 && currentRow < 8 && currentCol >= 0 && currentCol < 8) {

                            //System.out.println("goodbye");
                            {if (board.getArray()[currentRow][currentCol].returnPiece(currentRow, currentCol) == null){

                                break;}}
                            {

                                if (board.getArray()[currentRow][currentCol].returnPiece(currentRow, currentCol) != null && board.getArray()[currentRow][currentCol].returnColor(currentRow, currentCol) != color) {
                                    flag = true;
                                    currentRow += i;
                                    currentCol += j;

                                } else//if (_board.getArray()[currentRow][currentCol].returnPiece(currentRow, currentCol) != null && _board.getArray()[currentRow][currentCol].returnPiece(currentRow, currentCol).getColor() != color) {
                                {
                                    if (flag) {

                                       // System.out.println("testflip");
                                        while(currentRow!=row || currentCol!=col) {
                                          //  System.out.println("testinggg");
                                            currentRow -= i;
                                            currentCol -= j;

                                          //  System.out.println(currentRow);
                                          //  System.out.println(currentCol);

                                            board.getArray()[currentRow][currentCol].flipColor(currentRow, currentCol, color);
                                        }
                                        break;
                                    }else {
                                        break;
                                    }
                                }
                            }

                        }
                        else
                            break;
                    }
                }

            }}




    }
public void setupLabels(VBox labelPane){
        _b1 = new Label("Green: 2 Pink: 2");
        _b2 = new Label ("Current Player: Pink");
        labelPane.getChildren().addAll(_b1, _b2);

}

        //create TimeHandler
    private class OthelloMover implements EventHandler<ActionEvent>{


            @Override
            public void handle(ActionEvent event) {

                _timeline.pause();
                if (!Referee.this.cantMove(Referee.this.returnaColor(), _board))
                {  _currentPlayer.moveOver();


                }
                else
                    Referee.this.endTurn();


               // _b1.setText("Green: " + Referee.this.GreenScore(_board) + " Pink: " + Referee.this.PinkScore(_board));
                //_b2.setText("Current Player: " + Referee.this.currentPlayer());
            }
        }
    }

