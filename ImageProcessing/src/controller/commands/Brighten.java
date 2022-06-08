package controller.commands;

import controller.ImageControllerImpl;
import model.modifiers.BrightnessModifier;

public class Brighten extends ACommand {
  private int value;

  public Brighten(ImageControllerImpl c, int value, String name, String newName) {
    super(c, name, newName);
    this.value = value;
  }

  @Override
  public void go() {
    this.c.getVersions().put(this.newName, this.model.newModdedImage(new BrightnessModifier(value)));
  }
}
