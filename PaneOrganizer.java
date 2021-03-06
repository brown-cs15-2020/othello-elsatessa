package othello;


import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class PaneOrganizer {

    private Game _othello;
    private Controls _controls;
    private BorderPane _root;
    //sets up Game Pane and Controls pane
    public PaneOrganizer(){

        _root = new BorderPane();
        Pane othelloGame = new Pane();
        _othello = new Game(othelloGame);
        _controls = new Controls(_othello);
        _root.setCenter(othelloGame);
        _root.setBottom(_controls.getPane());

    }

    //returns root Border Pane
    public BorderPane getRoot(){
        return _root;
    }
}
