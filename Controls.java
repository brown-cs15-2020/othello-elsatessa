package othello;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/*
 * Controls sets up the GUI for the game menu, allowing the user to pick the
 * game modes and to start and track games. Controls holds a one-way reference
 * to the Game, so it can control the Game's player settings.
 */
public class Controls {

  private Game _game;

  private VBox _controlsPane;

  // Arrays for player buttons. Each button is checked to see if it is
  // selected when the user starts each game.
  private RadioButton[][] _playerButtons;
  private CheckBox[] _deterministicButtons;


  public Controls(Game othello) {

    _game = othello;

    _controlsPane = new VBox();

    _controlsPane.setPadding(new Insets(10));
    _controlsPane.setSpacing(30);
    _controlsPane.setAlignment(Pos.TOP_RIGHT);

    this.setupInstructions();
    /* TODO: Add score & turn labels. These should be contained in whatever class is controlling turn-taking, but
        be sure to add them to the _controlsPane here!  */

    //_referee.setupLabels(_controlsPane);
    this.setupMenu();
    this.setupGameButtons();
  }

  // TODO: Be sure to use this method to add the control pane to the _root pane in Pane Organizer
  public Pane getPane() {
    return _controlsPane;
  }

  private void setupInstructions() {
    Label instructionsLabel = new Label(
        "Select options, then press Apply Settings");
    _controlsPane.getChildren().add(instructionsLabel);
  }

  /*
   * Sets up the two halves of the player mode menu.
   */
  private void setupMenu() {
    _playerButtons = new RadioButton[2][4];
    _deterministicButtons = new CheckBox[2];

    HBox playersMenu = new HBox();
    playersMenu.setSpacing(10);
    playersMenu.setAlignment(Pos.CENTER_RIGHT);
    playersMenu.getChildren().addAll(this.playerMenu(Constants.WHITE),
        this.playerMenu(Constants.BLACK));

    _controlsPane.getChildren().add(playersMenu);
  }

  /*
   * Provides the menu for each player mode.
   */
  private VBox playerMenu(int player) {
    VBox playerMenu = new VBox();
    playerMenu.setPrefWidth(Constants.CONTROLS_PANE_WIDTH / 2);
    playerMenu.setSpacing(10);
    playerMenu.setAlignment(Pos.CENTER_RIGHT);

    // Player color.
    String playerColor = "White";
    if (player == Constants.BLACK) {
      playerColor = "Black";
    }
    Label playerName = new Label(playerColor);

    // Radio button group for player mode.
    ToggleGroup toggleGroup = new ToggleGroup();

    // Human player.
    RadioButton humanButton = new RadioButton("Human         ");
    humanButton.setToggleGroup(toggleGroup);
    humanButton.setSelected(true);
    _playerButtons[player][0] = humanButton;

    // Disables deterministic button when Human player selected.
    humanButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        _deterministicButtons[player].setDisable(true);
        _deterministicButtons[player].setSelected(false);
      }
    });

    // Computer Players.
    for (int i = 1; i < 4; i++) {
      RadioButton computerButton = new RadioButton("Computer " + i + "  ");
      computerButton.setToggleGroup(toggleGroup);
      _playerButtons[player][i] = computerButton;

      // Enables deterministic button when Computer player selected.
      computerButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
          _deterministicButtons[player].setDisable(false);
          _deterministicButtons[player].setSelected(true);
        }
      });
    }

    // Checkbox for deterministic play. Only enabled when computer player
    // selected. This is ONLY for Bells&Whistles
    CheckBox deterministic = new CheckBox("Deterministic");
    deterministic.setDisable(true);
    _deterministicButtons[player] = deterministic;

    // Visually add the player mode menu.
    playerMenu.getChildren().add(playerName);
    for (RadioButton rb : _playerButtons[player]) {
      playerMenu.getChildren().add(rb);
    }
    playerMenu.getChildren().add(deterministic);

    return playerMenu;
  }

  private void setupGameButtons() {
    Button applySettingsButton = new Button("Apply Settings");
    applySettingsButton.setOnAction(new ApplySettings());
    applySettingsButton.setFocusTraversable(false);

    Button resetButton = new Button("Reset");
    resetButton.setOnAction(new ResetHandler());
    resetButton.setFocusTraversable(false);

    Button quitButton = new Button("Quit");
    quitButton.setOnAction(new QuitHandler());
    quitButton.setFocusTraversable(false);

    _controlsPane.getChildren().addAll(applySettingsButton, resetButton,
        quitButton);
  }

  /*
   * Handler for Apply Settings button.
   */
  private class ApplySettings implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent e) {

      // Determine game play mode for each player.
      int whitePlayerMode = 0;
      int blackPlayerMode = 0;
      for (int player = 0; player < 2; player++) {
        for (int mode = 0; mode < 4; mode++) {
          if (_playerButtons[player][mode].isSelected()) {
            if (player == Constants.WHITE) {
              whitePlayerMode = mode;
            } else {
              blackPlayerMode = mode;
            }
          }
        }
      }

      // Set determinism for each player.
      boolean whitePlayerDeterministic = _deterministicButtons[Constants.WHITE]
          .isSelected();
      boolean blackPlayerDeterministic = _deterministicButtons[Constants.BLACK]
          .isSelected();

      /* TODO: Set the Game's players, which starts the game. whitePlayerMode and blackPlayerMode store the modes of
          the players, where 0 is HumanPlayer, and 1-3 are levels 1-3 of ComputerPlayer respectively. We also provide
          whitePlayerDeterministic and blackPlayerDeterministic which stores whether the players should be
          deterministic or not, but feel free to ignore these unless you are doing the deterministic Bells&Whistles.
          You should have a method in the game class that sets the players of the game, and then call this method here
          with the information we provided! */

      _game.startGame(whitePlayerMode, blackPlayerMode);
    }
  }

  /* TODO: Fill out this handle method once you have figured out how to reset the game.
      This will most likely not be done until after you have implemented turn-taking and score-keeping */
  private class ResetHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent e){ }

  }

  private class QuitHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent e) {
      Platform.exit();
    }
  }

}
