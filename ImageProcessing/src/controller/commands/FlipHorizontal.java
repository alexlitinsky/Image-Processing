package controller.commands;

import controller.ImageControllerImpl;
import model.modifiers.FlipModifier;

public class FlipHorizontal extends ACommand {

  public FlipHorizontal(ImageControllerImpl c, String name, String newName) {
    super(c, name, newName);
  }

  @Override
  public void go() {
    c.getVersions().put(newName, model.newModdedImage(new FlipModifier(false)));
  }
}