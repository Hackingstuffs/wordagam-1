package io.github.gravetii.controller;

import io.github.gravetii.game.Game;
import io.github.gravetii.pojo.GamePlayResult;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Map;

import static io.github.gravetii.controller.TableResultDisplayer.TableResult;

public class GameResultController implements FxController {

  private final Game game;
  private final GameController ref;
  private TableResultDisplayer displayer;

  @FXML private TableView<TableResult> tblDisplay;

  @FXML private TableColumn<TableResult, Integer> idTblCol;

  @FXML private TableColumn<TableResult, String> wordTblCol;

  @FXML private TableColumn<TableResult, Integer> pointsTblCol;

  public GameResultController(Game game, GameController ref) {
    this.game = game;
    this.ref = ref;
  }

  @FXML
  public void initialize() {
    this.idTblCol.prefWidthProperty().bind(tblDisplay.widthProperty().divide(4));
    this.wordTblCol.prefWidthProperty().bind(tblDisplay.widthProperty().divide(2));
    this.pointsTblCol.prefWidthProperty().bind(tblDisplay.widthProperty().divide(4));
    this.idTblCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    this.wordTblCol.setCellValueFactory(new PropertyValueFactory<>("word"));
    this.pointsTblCol.setCellValueFactory(new PropertyValueFactory<>("score"));
    this.tblDisplay.setRowFactory(
        callback -> {
          TableRow<TableResult> row = new TableRow<>();
          row.setOnMouseClicked(
              event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                  String word = row.getItem().getWord();
                  this.ref.revisitResult(game.getResult().get(word));
                }
              });

          return row;
        });

    this.displayer = new TableResultDisplayer(this.tblDisplay);
    this.display();
  }

  private void display() {
    Map<String, GamePlayResult> result = this.game.getResult();
    result.forEach(
        (word, gamePlayResult) -> {
          this.displayer.show(word, gamePlayResult.getScore());
        });
  }
}
