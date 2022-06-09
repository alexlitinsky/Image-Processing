package controller.commands;

import controller.ImageControllerImpl;
import model.modifiers.GreenCompModifier;

/**
 * Class to represent the command for apply a green compononent modifier. This sets all the RGB
 * components to the current green value by calling the go method.
 */
public class GreenComp extends ACommand {

  /**
   * Constructor for the GreenComp command. Consumes the current controller to access its
   * versions, the name of the model to be modified, and the name of the new modified model.
   *
   * @param c       the controller passed to this command
   * @param name    the name of the model being modified
   * @param newName the name of the new model created from the modifications
   */
  public GreenComp(ImageControllerImpl c, String name, String newName)
          throws IllegalArgumentException {
    super(c, name, newName);
  }

  /**
   * Method to apply this command. Gets the map of versions from the controller, modifies the image
   * and puts the modified version in the map of versions under the new name.
   */
  @Override
  public void go() {
    c.getVersions().put(newName, model.newModdedImage(new GreenCompModifier()));
  }
}
