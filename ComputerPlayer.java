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

    public ComputerPlayer(Referee referee, int level, Board board) {

        _level = level;
        _referee = referee;
        _board = board;
        _currentPlayer = _referee.returnPlayer();
    }

    public int evaluateBoard(Board board, Color color) {


        int value = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (board.getArray()[i][j].returnPiece(i, j) == null) {
                    break;
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
        int highestVal=0;
        _level = intelligence;
        _board = new Board(board);
        _mainColor=color;
        if(_mainColor== Color.MAGENTA)
            _otherColor= Color.GREEN;
        else if(_mainColor== Color.GREEN)
            _otherColor = Color.MAGENTA;

        if (this.gameOver(_board) && _referee.whoWins(_board)==color)
            return new Move(0, 0, 1000);
        else if (this.gameOver(_board) && _referee.whoWins(_board)!=Color.YELLOW && _referee.whoWins(_board)!= color)
            return new Move(0, 0, -1000);
        else if (this.gameOver(_board) && _referee.whoWins(_board)== Color.YELLOW)
            return new Move(0, 0, 0);


        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                if (_referee.moveValidity(row, col, color)) {
                    //iterate through all valid moves, make a copy board, test move on copy board


                        if (!_referee.moveValidity(row, col, color)) {
                            if (_level == 1)
                                return new Move(row, col, -1000);
                            else
                                value = -1* (getBestMove(_board, _level - 1, _otherColor)).getValue(); //negative
                        }

                        if(_referee.moveValidity(row, col, color)) {
                            _board.getArray()[row][col].addPiece(row, col, color);

                            if(intelligence==1)
                                value=this.evaluateBoard(_board, color);
                             else
                                 value = -1* (getBestMove(_board, _level -1, _otherColor).getValue());

                        }

                        }

                if(value>highestVal){
                    highestVal=value;
                    highestRow =row;
                    highestCol=col;

                }
                    }
                }
        return new Move(highestRow, highestCol, highestVal);
            }
        }

