package controller.commands;

import model.ImageModel;

/**
 * filler
 */
public class Load implements Command{

  @Override
  public void apply() {
    ImageUtil util = new ImageUtil();
  }
}
