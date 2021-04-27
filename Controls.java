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
    _controlsPane.setSpacing(20);
    _controlsPane.setAlignment(Pos.BOTTOM_RIGHT);

    this.setupInstructions();


    //adds Score and Current Player Labels to the Control Pane
    othello.setupLabels(_controlsPane);

    this.setupMenu();
    this.setupGameButtons();
  }


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
    playersMenu.setAlignment(Pos.BOTTOM_RIGHT);
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
    playerMenu.setAlignment(Pos.BOTTOM_RIGHT);

    // Player color.
    String playerColor = "Green";
    if (player == Constants.BLACK) {
      playerColor = "Pink";
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


//starts the game
      _game.startGame(whitePlayerMode, blackPlayerMode);

    }
  }

  //resets the game
  private class ResetHandler implements EventHandler<ActionEvent> {


    @Override
    public void handle(ActionEvent e){

      _game.reset();
    }



  }

  private class QuitHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent e) {
      Platform.exit();
    }
  }

}
