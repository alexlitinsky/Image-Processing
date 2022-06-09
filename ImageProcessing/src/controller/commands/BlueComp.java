package controller.commands;

import controller.ImageControllerImpl;
import model.modifiers.BlueCompModifier;
import view.ImageTextView;

/**
 * Class to represent the command for apply a blue compononent modifier. This sets all the RGB
 * components to the current blue value by calling the go method.
 */
public class BlueComp extends ACommand {

  /**
   * Constructor for the blue component command. Consumes the current controller to access its
   * versions, the name of the model to be modified, and the name of the new modified model.
   *
   * @param c       the controller passed to this command
   * @param name    the name of the model being modified
   * @param newName the name of the new model created from the modifications
   */
  public BlueComp(ImageControllerImpl c, String name, String newName)
          throws IllegalArgumentException {
    super(c, name, newName);
  }

  /**
   * Method to apply this command. Gets the map of versions from the controller, modifies the image
   * and puts the modified version in the map of versions under the new name.
   */
  @Override
  public void go() {
    c.getVersions().put(newName, model.newModdedImage(new BlueCompModifier()));
    ImageTextView view = new ImageTextView(c.getVersions().get(newName), new StringBuilder());
  }
}
