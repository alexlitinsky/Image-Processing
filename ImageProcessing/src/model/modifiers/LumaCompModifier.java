package model.modifiers;

import model.Image;
import model.ImageImpl;

/**
 * Represents a luma component modifier. Changes the new models' rgb values to
 * their respective luma. When representing a luma, the integer is cast down.
 */
public class LumaCompModifier implements Modifier {

  /**
   * Constructs a luma modifier object.
   */
  public LumaCompModifier() {
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
        int luma = (int) (model.getPixel(i, j).getRGB()[0] * .2126
                + model.getPixel(i, j).getRGB()[1] * .7152 +
                model.getPixel(i, j).getRGB()[2] * .0722);
        build.assignPixels(i, j, luma, luma, luma);
      }
    }
    return build;
  }
}