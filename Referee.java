package othello;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;


import java.sql.Ref;

public class Referee {
    private Timeline _timeline;
    private Player _white;
    private Player _black;
    private Player _currentPlayer;
    private Board _board;
    private int[][] _arrayOfInts;

    public Referee(Board board) {
        _board = board;
        //this.endTurn();

        _arrayOfInts = new int[8][2];
        for (int i=0; i<8; i++){
            _arrayOfInts[i][0]= 99;
            _arrayOfInts[i][1]=99;
        }
    }


    public void takePlayer(Player white, Player black) {
        _white = white;
        _black = black;
        _currentPlayer = _black;
      //  this.endTurn();

    }


    //set up Timeline- pause and play timeline
    //set up timeline when apply settings is clicked
    private void setupTimeLine() {

        //call moveOver method
       // KeyFrame kf = new KeyFrame(Duration.seconds(.4), new Referee.OthelloMover());
    //    _timeline = new Timeline(kf);
        _timeline.setCycleCount(Animation.INDEFINITE);
        _timeline.play();

    }

    public Color returnaColor(){
       // if (_currentPlayer == _white)
       //     return Color.GREEN;
     //  else if(_currentPlayer == _black)
       //     return Color.MAGENTA;

     //   else
            return Color.MAGENTA;
    }

    public void endTurn() {

        if (_currentPlayer == _white)
            _currentPlayer = _black;
         else
            _currentPlayer = _white;


         _currentPlayer.moveOver();
    }

    public Player returnPlayer(){
        return _currentPlayer;
    }

    public boolean moveValidity(int row, int col) {

        boolean move = false;

        if(_board.getArray()[row][col].returnPiece(row, col)==null && this.Sandwich(row, col))
            move=true;

        return move;

                       }


    public boolean checkSquares(int a, int b){
        boolean check=false;
        if(_board.getArray()[a][b].returnPiece(a,b)==null)
            check=true;
        if(_board.getArray()[a][b].returnPiece(a,b).getColor()== this.returnaColor())
            check=true;
        if(_board.getArray()[a][b].returnPiece(a,b).getColor()!= this.returnaColor())
            check = false;

        return check;
    }

    public boolean Sandwich(int x, int y) {
        Color color = Referee.this.returnaColor();

        int array =0;
        boolean flag = false;
        boolean mark = false;
        for (int i=-1; i<= 1; i++){
            for (int j=-1; j<= 1; j++) {

                int currentRow = y + i;
                int currentCol = x + j;
                flag = false;

                while (true) {

                    System.out.println("elsa");
                    //checks to see if on board
                    if (currentRow >= 0 && currentRow < 8 && currentCol >= 0 && currentCol < 8) {

                        System.out.println("goodbye");
                        {if (_board.getArray()[currentRow][currentCol].returnPiece(currentRow, currentCol) == null){
                            System.out.println("cos");
                            break;}}


                        if (_board.getArray()[currentRow][currentCol].returnPiece(currentRow, currentCol) != null && _board.getArray()[currentRow][currentCol].returnPiece(currentRow, currentRow).getColor() != color)
                        {
                            flag = true;
                            System.out.println("fiona");
                            currentRow += i;
                            currentCol += j;
                            }

                        else//if (_board.getArray()[currentRow][currentCol].returnPiece(currentRow, currentCol) != null && _board.getArray()[currentRow][currentCol].returnPiece(currentRow, currentCol).getColor() != color) {
                        {   System.out.println("um");
                        if (flag){
                            return true;}
                        else{
                            break;}}

                    }
                }

            }}
        return false;
    }
        //flip every turn
        public void flip(int x, int y){

        for (int i=0; i<9; i++) {

            if (_arrayOfInts[i][0] != 99) {
                int a = _arrayOfInts[i][0];
                int b = _arrayOfInts[i][0];

                while(a>x && b==y){
                    a--;
                    _board.getArray()[a][b].returnPiece(a, b).switchColor(this.returnaColor());
                }
            }
        }
        }


        //create TimeHandler
    }
