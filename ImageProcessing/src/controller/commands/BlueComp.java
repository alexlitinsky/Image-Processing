package controller.commands;

import controller.ImageControllerImpl;
import model.modifiers.BlueCompModifier;

public class BlueComp extends ACommand {

  public BlueComp(ImageControllerImpl c, String name, String newName) {
    super(c, name, newName);
  }

  @Override
  public void go() {
    c.getVersions().put(newName, model.applyFilter(new BlueCompModifier()));

  }
}
