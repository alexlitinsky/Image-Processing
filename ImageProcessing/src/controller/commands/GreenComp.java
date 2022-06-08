package controller.commands;

import controller.ImageControllerImpl;
import model.modifiers.GreenCompModifier;

public class GreenComp extends ACommand {

  public GreenComp(ImageControllerImpl c, String name, String newName) {
    super(c, name, newName);
  }

  @Override
  public void go() {
    c.getVersions().put(newName, model.newModdedImage(new GreenCompModifier()));
  }
}
