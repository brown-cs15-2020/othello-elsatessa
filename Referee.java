package othello;

public class Referee {
    private Player _white;
    private Player _black;
    private Player _currentPlayer;

    public Referee(){}


public void takePlayer(Player white, Player black){
        _white = white;
        _black = black;
        _currentPlayer = _white;
        this.endTurn();

}

public void endTurn(){

        if(_currentPlayer == _white){
            _currentPlayer = _black;
        }
        else
            _currentPlayer = _white;
}

//takes in player and square index, returns true if player is the current player
/*public boolean moveValidity(Player player, int x, int y){

        boolean moveValidity = false;
        if(player == _currentPlayer){
            if(Referee.this.Sandwhich(x, y) == true){
                moveValidity = true;
            }

        }

        return moveValidity;
}

public boolean Sandwhich(int x, int y){


        for (int i=x-1; i<= x+1; i++){

            for (int j=x-1; j<= j+1; j++){

                Piece piece = new Piece();
                {

                }
            }
        }
}
*/

}
