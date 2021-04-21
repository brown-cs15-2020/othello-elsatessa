package othello;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tetris.Constants;

/**
  * This is the  main class where your Othello game will start.
  * The main method of this application calls the App constructor. You
  * will need to fill in the constructor to instantiate your game.
  *
  * Class comments here...
  *
  */

public class App extends Application {

    @Override
    public void start(Stage stage) {
        // Create top-level object, set up the scene, and show the stage here.
        stage.setTitle("Othello");

        //creates top level object
        PaneOrganizer organizer = new PaneOrganizer();

        //creates scene that is 300 x 600
        Scene scene = new Scene(organizer.getRoot(),  670, 800);


        // sets up the scene
        stage.setScene(scene);

        //shows the stage
        stage.show();
    }

    /*
    * Here is the mainline! No need to change this.
    */
    public static void main(String[] argv) {
        // launch is a method inherited from Application
        launch(argv);
    }
}
