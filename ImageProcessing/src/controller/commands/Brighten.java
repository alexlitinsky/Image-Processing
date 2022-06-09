package controller.commands;

import controller.ImageControllerImpl;
import model.modifiers.BrightnessModifier;

/**
 * Class to represent the brighten command. The command can either increase or decrease the
 * brightness of an image, depending on the value supplied to it.
 */
public class Brighten extends ACommand {
  private int value;

  /**
   * Constructor for the brighten command. Consumes a controller, the name of the image being
   * modified, the name of the new image created from modification, the value to change the
   * brightness by (restricted to between -255 and 255, and must not make the model have RGB
   * values less than 0 or greater than 255.
   *
   * @param c       the controller controlling the command
   * @param value   the value to change the brigthness by
   * @param name    the name of the image to modify
   * @param newName the name of the new image created by this command
   */
  public Brighten(ImageControllerImpl c, String value, String name, String newName)
          throws IllegalArgumentException {
    super(c, name, newName);
    this.value = Integer.parseInt(value);
  }

  /**
   * Method to apply this command. Gets the map of versions from the controller, modifies the image
   * and puts the modified version in the map of versions under the new name.
   */
  @Override
  public void go() {
    this.c.getVersions().put(this.newName, this.model.newModdedImage(new BrightnessModifier(value)));
  }
}
