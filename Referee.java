package othello;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.util.Duration;


import java.sql.Ref;

public class Referee {
    private Timeline _timeline;
    private Player _white;
    private Player _black;
    private Player _currentPlayer;
    private Board _board;


    public Referee(Board board) {
        _board = board;
        //this.endTurn();
       // this.setupTimeLine();

        }



    public void takePlayer(Player white, Player black) {
        _white = white;
        _black = black;
        _currentPlayer = _black;
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
      KeyFrame kf = new KeyFrame(Duration.seconds(.4), new Referee.OthelloMover());
      _timeline = new Timeline(kf);
        _timeline.setCycleCount(Animation.INDEFINITE);
        _timeline.play();

    }

    public boolean cantMove(Color color, Board board){


      for (int row=0; row<8; row++){
          for (int col=0; col<8; col++){
              if(this.moveValidity(row,col, color))
                  return false;
          }
      }
        return true;
    }

    public Color whoWins(Board board){
        int black=0;
        int white=0;
        for (int row=0; row<8; row++){
            for (int col=0; col<8; col++){
                if(board.getArray()[row][col].returnPiece(row,col).getColor()==Color.MAGENTA)
                    black++;
                else if(board.getArray()[row][col].returnPiece(row,col).getColor()==Color.GREEN)
                    white++;
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
     else if(_currentPlayer == _black)
         return Color.MAGENTA;

      else
            return Color.MAGENTA;
    }

    public void endTurn() {

        if (_currentPlayer == _white)
            _currentPlayer = _black;
         else
            _currentPlayer = _white;

         this.setupTimeLine();
        _timeline.play();
    }

    public Player returnPlayer(){
        return _currentPlayer;
    }

    public boolean moveValidity(int row, int col, Color color) {

        boolean move = false;

        if(_board.getArray()[row][col].returnPiece(row, col)==null && this.Sandwich(row, col, color)) {
            move = true;
            _board.getArray()[row][col].changeColor(row, col, Color.GREY);
        }
        return move;

                       }



    public boolean Sandwich(int row, int col, Color color) {


        int array =0;
        boolean flag = false;
        boolean mark = false;
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
                        {if (_board.getArray()[currentRow][currentCol].returnPiece(currentRow, currentCol) == null){
                           // System.out.println("cos");
                            break;}}


                        if (_board.getArray()[currentRow][currentCol].returnPiece(currentRow, currentCol) != null && _board.getArray()[currentRow][currentCol].returnPiece(currentRow, currentRow).getColor() != color)
                        {
                            flag = true;
                           // System.out.println("fiona");
                            currentRow += i;
                            currentCol += j;
                            }

                        else//if (_board.getArray()[currentRow][currentCol].returnPiece(currentRow, currentCol) != null && _board.getArray()[currentRow][currentCol].returnPiece(currentRow, currentCol).getColor() != color) {
                        {   //System.out.println("um");
                        if (flag){
                            return true;}
                        else{
                            break;}}

                        System.out.println(currentRow);
                        System.out.println(currentCol);
                    }
                }

            }}
        return false;
    }
        //flip every turn
        public void flip(int x, int y){

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

            }
        }
    }
