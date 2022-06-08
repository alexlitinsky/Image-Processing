package controller.commands;

import controller.ImageControllerImpl;
import model.ImageModel;

public abstract class ACommand implements Command {
  protected final ImageControllerImpl c;
  protected final ImageModel model;
  protected final String newName;

  public ACommand(ImageControllerImpl c, String name, String newName) {
    this.c = c;
    this.newName = newName;
    this.model = this.c.getVersions().get(name);
  }

  public abstract void go();

}
