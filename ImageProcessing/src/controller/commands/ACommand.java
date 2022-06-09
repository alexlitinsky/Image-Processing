package controller.commands;

import controller.ImageControllerImpl;
import model.ImageModel;

/**
 * Class to represent abstracted command data. Contains the controller accessing and controlling the
 * command, the model being edited by the command's requisite modifier, and the name for the new
 * image model created by running the command.
 */
public abstract class ACommand implements Command {
  protected final ImageControllerImpl c;
  protected final ImageModel model;
  protected final String newName;

  /**
   * Constructor for the abstract command class. Consumes a controller to control the command,
   * a name for the model to get from the controller's list of versions, and the new name to give to
   * the model created by running the command.
   * @param c the controller controlling the command
   * @param name the name of the model being modified
   * @param newName the name of the new model created from running the command
   */
  public ACommand(ImageControllerImpl c, String name, String newName)
          throws IllegalArgumentException {
    if (c == null || name == null || newName == null) {
      throw new IllegalArgumentException("Invalid parameters for command.");
    }
    this.c = c;
    this.newName = newName;
    this.model = this.c.getVersions().get(name);
  }

  /**
   * Method to run this command. All commands must have this method.
   */
  public abstract void go();
}
