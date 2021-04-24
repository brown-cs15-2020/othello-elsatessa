package othello;

import com.sun.deploy.security.SelectableSecurityManager;
import javafx.scene.paint.Color;

public class ComputerPlayer implements Player {

    private Player _currentPlayer;
    private Referee _referee;
    private int _level;
    private Board _board;
    private Color _mainColor;
    private Color _otherColor;
    private Color _color;

    public ComputerPlayer(Referee referee, int level, Board board, Color color) {

        _level = level;
        _referee = referee;
        _board = board;
        _currentPlayer = _referee.returnPlayer();
        _color = color;
    }

    public int evaluateBoard(Board board, Color color) {


        int value = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (board.getArray()[i][j].returnPiece(i, j) == null) {
                    continue;
                } else if (board.getArray()[i][j].returnPiece(i, j).getColor() == color) {
                    value = value + Constants.SCORE[i][j];
                } else
                    value = value - Constants.SCORE[i][j];
            }


        }
        return value;
    }


    @Override
    public boolean moveOver() {

       Move move= this.getBestMove(_board, _level, _color);
        _board.getArray()[move.getRow()][move.getCol()].addPiece(move.getRow(), move.getCol(), _color);
       // _referee.flip(move.getRow(), move.getCol(), _color);
        _referee.endTurn();

        return false;
    }

    //checks if every square on the board is full or if neither player can move
    public boolean gameOver(Board board) {
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                if(board.getArray()[i][j].returnPiece(i,j)!=null)
                    return true;
            }
        }
        if(_referee.cantMove(Color.MAGENTA, board) && _referee.cantMove(Color.GREEN, board))
            return true;

        return false;
    }

    //ba

    public Move getBestMove(Board board, int intelligence, Color color) {

        int highestRow=-1;
        int highestCol =-1;
        int value=0;
        int highestVal=-10000;

       Color otherColor;
        //Color mainColor =color;
        if(color== Color.MAGENTA)
            otherColor= Color.GREEN;
        else
            otherColor = Color.MAGENTA;

        System.out.println("ben");
        if (!this.gameOver(board) && _referee.whoWins(board)==color)
            return new Move(0, 0, 1000);
        else if (!this.gameOver(board) && _referee.whoWins(board)!=Color.YELLOW && _referee.whoWins(board)!= color)
            return new Move(0, 0, -1000);
        else if (!this.gameOver(board) && _referee.whoWins(board)== Color.YELLOW)
            return new Move(0, 0, 0);


        //method that tests if no moves at all
//        if (!_referee.moveValidity(row, col, color, board)) {
//            if (_level == 1)
//                return new Move(0, 0, -1000);
//            else{
//                value = -1* (getBestMove(board, intelligence - 1, otherColor)).getValue();
//                return new Move(0,0, value);
//                //negative
//        }}

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

              //  if(row==2 && col ==3){
             //   System.out.println("nate");}

                //method that
                if (_referee.moveValidity(row, col, color, board)) {
                    //iterate through all valid moves, make a copy board, test move on copy board

                            Board copyBoard = new Board(board);
                            copyBoard.getArray()[row][col].addPiece(row, col, color);
                            _referee.flip(row, col, color, copyBoard);

                            if(intelligence==1) {
                                //look into this value
                                value = this.evaluateBoard(copyBoard, color);
                                System.out.println(value);
                            }
                             else
                                 value = -1* (getBestMove(copyBoard, intelligence -1, otherColor).getValue());



                        }




                if(value>=highestVal){
                    highestVal=value;
                    highestRow =row;
                    highestCol=col;


                }
                    }
                }
        System.out.println(highestRow);
        System.out.println(highestCol);
        System.out.println(highestVal);

        return new Move(highestRow, highestCol, highestVal);

            }
        }

