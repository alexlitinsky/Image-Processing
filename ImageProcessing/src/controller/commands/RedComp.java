package controller.commands;

import controller.ImageControllerImpl;
import model.modifiers.RedCompModifier;

public class RedComp extends ACommand {

  public RedComp(ImageControllerImpl c, String name, String newName) {
    super(c, name, newName);
  }

  @Override
  public void go() {
    c.getVersions().put(newName, model.newModdedImage(new RedCompModifier()));
  }
}