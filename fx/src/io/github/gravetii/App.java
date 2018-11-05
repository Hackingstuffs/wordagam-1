package io.github.gravetii;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class App extends Application {

    private Stage stage;

    private Image getStartingImage() {
        int r = 1 + new Random().nextInt(2);
        return new Image(App.class.getResourceAsStream("skins/" + r + ".jpg"));
    }

    private void start(FXMLLoader loader) throws IOException {
        AnchorPane root = loader.load();
        ImageView imgView = new ImageView();
        Image img = getStartingImage();
        imgView.setImage(img);
        imgView.setImage(img);
        BorderPane pane = (BorderPane) root.getChildren().get(1);
        pane.setCenter(imgView);
        imgView.fitWidthProperty().bind(pane.widthProperty());
        imgView.fitHeightProperty().bind(pane.heightProperty());
        Scene scene = new Scene(pane, 540, 420);
        stage.setScene(scene);
        stage.setTitle("WORDAGAM!");
        stage.sizeToScene();
        stage.show();
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(App.class.getResource("main.fxml"));
        Controller controller = new Controller(this);
        loader.setController(controller);
        start(loader);
    }

    public static void main(String[] args) {
        launch(args);
    }
}