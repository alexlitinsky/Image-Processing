package controller.commands;

import controller.ImageControllerImpl;
import model.modifiers.IntensityCompModifier;

public class IntensityComp extends ACommand {

  public IntensityComp(ImageControllerImpl c, String name, String newName) {
    super(c, name, newName);
  }

  @Override
  public void go() {
    c.getVersions().put(newName, model.newModdedImage(new IntensityCompModifier()));
  }
}