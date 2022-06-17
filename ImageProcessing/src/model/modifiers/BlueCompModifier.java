package model.modifiers;

import model.Image;
import model.ImageImpl;

/**
 * Represents a modification. Changes all the pixel values of to the blue component.
 */
public class BlueCompModifier implements Modifier {

  /**
   * Initializes a BlueComp Modifier.
   */
  public BlueCompModifier() {
    // no arguments to be given
  }

  @Override
  public Image apply(Image model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("invalid model");
    }
    Image build = new ImageImpl(model.getDimensions()[0], model.getDimensions()[1]);
    for (int i = 0; i < model.getDimensions()[0]; i++) {
      for (int j = 0; j < model.getDimensions()[1]; j++) {
        int blue = model.getPixel(i, j).getRGB()[2];
        build.assignPixels(i, j, blue, blue, blue);
      }
    }
    return build;
  }
}