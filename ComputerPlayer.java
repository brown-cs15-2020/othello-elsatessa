package othello;

import javafx.scene.paint.Color;

public class ComputerPlayer implements Player {

    private Player _currentPlayer;
    private Referee _referee;
    private int _level;
    private Board _board;
    private Color _color;

    //sets up the Computer Player which takes in a referee, a level, a board, and a color as parameters
    public ComputerPlayer(Referee referee, int level, Board board, Color color) {

        _level = level;
        _referee = referee;
        _board = board;
        _currentPlayer = _referee.returnPlayer();
        _color = color;
    }

    //takes in a board and a color and evaluates the board in terms of that color based on the array of board weights
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



    //plays the best move that is determined by the minimax by adding the piece, flipping it, and then ending the turn
    @Override
    public void moveOver() {

       Move move= this.getBestMove(_board, _level, _color);
        _board.getArray()[move.getRow()][move.getCol()].addPiece(move.getRow(), move.getCol(), _color);
        _referee.flip(move.getRow(), move.getCol(), _color, _board);
        _referee.endTurn();

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

    //returns the Best Move
    //This method works recursively: it takes in a board, an intelligence level, and a color
    public Move getBestMove(Board board, int intelligence, Color color) {

        int highestRow=-1;
        int highestCol =-1;
        int value=0;
        int highestVal=-10000;

       Color otherColor;

        if(color== Color.MAGENTA)
            otherColor= Color.GREEN;
        else
            otherColor = Color.MAGENTA;


        //sets Value to a very high number if the current player will win, a very low number if the
        //player will lose, and to zero if the players tie
        if (!this.gameOver(board) && _referee.whoWins(board)==color)
            return new Move(0, 0, 1000);
        else if (!this.gameOver(board) && _referee.whoWins(board)!=Color.YELLOW && _referee.whoWins(board)!= color)
            return new Move(0, 0, -1000);
        else if (!this.gameOver(board) && _referee.whoWins(board)== Color.YELLOW)
            return new Move(0, 0, 0);


       // tests if there are no moves at all- if intelligence is 1 it returns a very low value
        //if intelligence is not one, value is equal to the negative of the method done recursively
        // with a lower value
        if (_referee.cantMove(color, board)) {
            if (intelligence == 1)
                return new Move(0, 0, -1000);
            else{
                value = -1* (getBestMove(board, intelligence - 1, otherColor)).getValue();
                return new Move(0,0, value);

        }}


        //iterates through the whole board
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                //determines if the specific row and column have a valid move
                if (_referee.moveValidity(row, col, color, board)) {


                    //iterates through all valid moves, makes a copy board, tests move on copy board
                            Board copyBoard = new Board(board);
                            copyBoard.getArray()[row][col].addPiece(row, col, color);
                            _referee.flip(row, col, color, copyBoard);


                            //if the intelligence level is 1, it evaluates based on the copy board
                            if(intelligence==1) {
                                value = this.evaluateBoard(copyBoard, color);

                            }

                            //if the intelligence is not 1, it continues recursively
                             else
                            { value = -1* (getBestMove(copyBoard, intelligence -1, otherColor).getValue());}

                             //sets highestValue to the highest value and sets highestRow and highestCol to the row and col
                            //associated with the highestValue
                    if(value>=highestVal){
                        highestVal=value;
                        highestRow =row;
                        highestCol=col;



                    }
                        }


                    }
                }



        //returns the Move with the highest Value
        return new Move(highestRow, highestCol, highestVal);

            }
        }

