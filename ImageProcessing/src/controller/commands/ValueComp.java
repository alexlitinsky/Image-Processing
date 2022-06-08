package controller.commands;

import controller.ImageControllerImpl;
import model.modifiers.ValueCompModifier;

public class ValueComp extends ACommand {

  public ValueComp(ImageControllerImpl c, String name, String newName) {
    super(c, name, newName);
  }

  @Override
  public void go() {
    c.getVersions().put(newName, model.newModdedImage(new ValueCompModifier()));
  }
}