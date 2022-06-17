package model.modifiers;

import model.Image;
import model.ImageImpl;

/**
 * Represents a value-component modifier. Changes the r g b values of each pixel to their respective
 * max value. Employed Math.max() to compute the max value.
 */
public class ValueCompModifier implements Modifier {

  /**
   * A Constructor for a value-component object.
   */
  public ValueCompModifier() {
    // no fields to initialize
  }

  @Override
  public Image apply(Image model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("invalid model");
    }
    Image build = new ImageImpl(model.getDimensions()[0], model.getDimensions()[1]);
    for (int i = 0; i < model.getDimensions()[0]; i++) {
      for (int j = 0; j < model.getDimensions()[1]; j++) {
        int val = Math.max(model.getPixel(i, j).getRGB()[0],
                Math.max(model.getPixel(i, j).getRGB()[1], model.getPixel(i, j).getRGB()[2]));
        build.assignPixels(i, j, val, val, val);
      }
    }
    return build;
  }
}
