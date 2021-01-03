package io.github.gravetii.scene.start;

import io.github.gravetii.scene.FxDimensions;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.scene.menu.MenuBarComponent;
import javafx.geometry.Dimension2D;
import javafx.stage.Stage;

import java.util.Optional;

public class StartScene extends FxScene {
  private final MenuBarComponent menuBarComponent;
  private final StartImageComponent startImageComponent;
  private final StartControlsComponent startControlsComponent;

  public StartScene(Stage stage) throws Exception {
    super(stage);
    this.menuBarComponent = new MenuBarComponent(this.stage, this.root);
    this.startImageComponent = new StartImageComponent(this.root);
    this.startControlsComponent = new StartControlsComponent(menuBarComponent);
  }

  @Override
  protected void build() {
    this.showTop(this.menuBarComponent)
        .showCenter(this.startImageComponent)
        .showRight(this.startControlsComponent);
  }

  @Override
  public String title() {
    return "-WORDAGAM-";
  }

  @Override
  protected Optional<FxDimensions> preferredDimensions() {
    Dimension2D def = new Dimension2D(960, 630);
    Dimension2D min = new Dimension2D(960, 630);
    Dimension2D max = new Dimension2D(1100, 732);
    return Optional.of(new FxDimensions(def, min, max));
  }
}
