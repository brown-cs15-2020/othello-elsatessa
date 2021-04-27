package othello;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class Referee {
    private Timeline _timeline;
    private Player _white;
    private Player _black;
    private Player _currentPlayer;
    private Board _board;



    //takes in a Board and adds the staring 4 pieces to the Board
    public Referee(Board board) {
        _board = board;
        _board.getArray()[3][3].addPiece(3,3, Color.MAGENTA);
        _board.getArray()[4][4].addPiece(4,4, Color.MAGENTA);
        _board.getArray()[4][3].addPiece(4,3, Color.GREEN);
        _board.getArray()[3][4].addPiece(3,4, Color.GREEN);

        }




        //takes in a white and black player and sets current player to white and sets up the timeline
    public void takePlayer(Player white, Player black) {
        _white = white;
        _black = black;
        _currentPlayer = _white;
        this.setupTimeLine();

    }



    //set up timeline when apply settings is clicked
    private void setupTimeLine() {
      KeyFrame kf = new KeyFrame(Duration.seconds(1), new Referee.OthelloMover());
      _timeline = new Timeline(kf);
        _timeline.setCycleCount(Animation.INDEFINITE);
        _timeline.play();

    }

    //returns true if a player of parametered color can't move
    public boolean cantMove(Color color, Board board){

      for (int row=0; row<8; row++){
          for (int col=0; col<8; col++){
              if(this.moveValidity(row,col, color, board))
                  return false;
          }
      }
        return true;
    }

    //returns true if the game is Over (if both players can't move or the board is full)
    public boolean gameOver(Board board) {
        if(this.cantMove(Color.MAGENTA, board) && this.cantMove(Color.GREEN, board))
            return true;

        else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getArray()[i][j].returnPiece(i, j) == null)
                        return false;
                }
            }
        }

        return true;
    }


    //returns the number of squares on the board are occupied by pink
    public int PinkScore (Board board){
        int pink=0;

        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                if (board.getArray()[i][j].returnPiece(i,j) != null) {
                if(board.getArray()[i][j].returnColor(i, j)==Color.MAGENTA)
                    pink++;
            }}
        }
        return pink;
    }

    //returns the number os squares on the board occupied by green
    public int GreenScore (Board board){
        int green=0;

        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){

                if (board.getArray()[i][j].returnPiece(i,j) != null) {
                    if (_board.getArray()[i][j].returnPiece(i, j).getColor() == Color.MAGENTA)
                        green++;

                }
        }}
        return green;
    }


    //determines which color wins and returns that color- if there is a tie it returns yellow
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

    //returns the Color of the current Player
    public Color returnaColor(){
       if (_currentPlayer == _white)
          return Color.GREEN;
     else
         return Color.MAGENTA;

    }

    //returns a String of the current Player
    public String currentPlayer(){

        if (_currentPlayer == _white)
            return "Green";
        else
            return "Pink";
    }

    //switches current Player and plays timeline in order to show the switching of turns
    public void endTurn() {
        if (_currentPlayer == _white)
            _currentPlayer = _black;
         else
            _currentPlayer = _white;

        _timeline.play();
    }


    //returns the current Player
    public Player returnPlayer(){
        return _currentPlayer;
    }

    //returns true if there is a valid move in a specific row, col
    public boolean moveValidity(int row, int col, Color color, Board board) {

        boolean move = false;

        if(board.getArray()[row][col].returnPiece(row, col)==null && this.Sandwich(row, col, color, board)) {
            move = true;
        }
        return move;

    }

    //checks if there is a sandwich is a specific row, col
    public boolean Sandwich(int row, int col, Color color, Board board) {



        boolean flag;

        for (int i=-1; i<= 1; i++){
            for (int j=-1; j<= 1; j++) {


                //iterates through the surrounding rows and cols
                int currentRow = row + i;
                int currentCol = col + j;
                flag = false;

                while (true) {

                    //checks to see if it's actually on the board
                    if (currentRow >= 0 && currentRow < 8 && currentCol >= 0 && currentCol < 8) {


                        //breaks if the spot is null
                        {if (board.getArray()[currentRow][currentCol].returnPiece(currentRow, currentCol) == null){

                            break;}}
                        {

                            //sets flag to true if the piece is the opposite color
                            if (board.getArray()[currentRow][currentCol].returnPiece(currentRow, currentCol) != null && board.getArray()[currentRow][currentCol].returnColor(currentRow, currentCol) != color) {
                                flag = true;
                                currentRow += i;
                                currentCol += j;
                               
                            } else
                            {
                                //this else statement checks to see if the square is of the same color
                                //if flag is true returns true
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

    //clears all pieces and then resets up the board with the original four pieces
    public void clearBoard(){
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){

                {
                _board.getArray()[i][j].removePiece(i,j);
                     _board.getArray()[i][j].changeColorBack();
                            }
            }
        }

        _board.getArray()[3][3].addPiece(3,3, Color.MAGENTA);
        _board.getArray()[4][4].addPiece(4,4, Color.MAGENTA);
        _board.getArray()[4][3].addPiece(4,3, Color.GREEN);
        _board.getArray()[3][4].addPiece(3,4, Color.GREEN);
    }

    //flips the pieces if there is a sandwich; follows the same logic as the sandwich method but instead of returning true, it
    //iterates back and changes the colors of pieces
    public void flip(int row, int col, Color color, Board board){
        {
            boolean flag;
            for (int i=-1; i<= 1; i++){
                for (int j=-1; j<= 1; j++) {

                    int currentRow = row + i;
                    int currentCol = col + j;
                    flag = false;

                    while (true) {

                        if (currentRow >= 0 && currentRow < 8 && currentCol >= 0 && currentCol < 8) {

                            {if (board.getArray()[currentRow][currentCol].returnPiece(currentRow, currentCol) == null){

                                break;}}
                            {

                                if (board.getArray()[currentRow][currentCol].returnPiece(currentRow, currentCol) != null && board.getArray()[currentRow][currentCol].returnColor(currentRow, currentCol) != color) {
                                    flag = true;
                                    currentRow += i;
                                    currentCol += j;

                                } else
                                {
                                    if (flag) {

                                        while(currentRow!=row || currentCol!=col) {
                                            currentRow -= i;
                                            currentCol -= j;


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


    //creates a TimeHandler that pauses the timeline plays the turn and ends the turn if the player can move
    //and ends the turn if it can't
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

