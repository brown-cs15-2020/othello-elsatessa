package othello;

public class ComputerPlayer implements Player{

    private Player _currentPlayer;
    private Referee _referee;
    private int _level;
    private Board _board;
    public ComputerPlayer(Referee referee, int level, Board board){

        _level = level;
        _referee = referee;
        _board = board;
        _currentPlayer = _referee.returnPlayer();
    }


    @Override
    public boolean moveOver() {
        return false;
    }

    public boolean gameOver(){
        return false;
    }

    public Move getBestMove(Board board, int intelligence, Player currentPlayer ){
        _level = intelligence;
        _board = new Board(board);
        _currentPlayer = currentPlayer;
        if(this.gameOver() && _currentPlayer.wins())
            return highMove;
        else if(this.gameOver() && _currentPlayer.loses())
            return lowMove;
        else if(this.gameOver() && _currentPlayer.ties())


            if(!_referee.moveValidity()){
                if(_level==1)
                    return lowMove;
                else
                   return  (getBestMove(_board, _level-1, _currentPlayer));}

        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                if(_referee.moveValidity(i, j))
                    makeMove(i,j);
                if(_level==1)
                    value = makeMove;
                else
                    value = (getBestMove(_board, _level-1, _currentPlayer));



            }
        }




    }
}
