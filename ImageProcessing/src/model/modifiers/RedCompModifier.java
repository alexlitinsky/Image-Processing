package model.modifiers;

import model.Image;
import model.ImageImpl;

/**
 * Represents a red-component modification. Changes the rgb values to their respective
 * red component.
 */
public class RedCompModifier implements Modifier {

  /**
   * A Constructor for a red-component object.
   */
  public RedCompModifier() {
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
        int red = model.getPixel(i, j).getRGB()[0];
        build.assignPixels(i, j, red, red, red);
      }
    }
    return build;
  }
}
