package othello;

public class ComputerPlayer implements Player{

    private Referee _referee;
    private int _level;
    public ComputerPlayer(Referee referee, int level){

        _level = level;
        _referee = referee;
    }


    @Override
    public boolean moveOver() {
        return false;
    }
}
