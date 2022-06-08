package controller.commands;

import controller.ImageControllerImpl;
import model.modifiers.LumaCompModifier;

public class LumaComp extends ACommand {

  public LumaComp(ImageControllerImpl c, String name, String newName) {
    super(c, name, newName);
  }
  @Override
  public void go() {
    c.getVersions().put(newName, model.applyFilter(new LumaCompModifier()));
  }
}