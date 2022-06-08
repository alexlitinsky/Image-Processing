package controller.commands;

import controller.ImageControllerImpl;
import model.modifiers.FlipModifier;

public class FlipHorizontal extends ACommand {

  public FlipHorizontal(ImageControllerImpl c, String name, String newName) {
    super(c, name, newName);
  }

  /**
   * Method to apply this command. Gets the map of versions from the controller, modifies the image
   * and puts the modified version in the map of versions under the new name.
   */
  @Override
  public void go() {
    c.getVersions().put(newName, model.newModdedImage(new FlipModifier(false)));
  }
}