package othello;

public class ComputerPlayer implements Player{

    private Referee _referee;
    public ComputerPlayer(Referee referee){
        _referee = referee;
    }


    @Override
    public boolean moveOver() {
        return false;
    }
}
