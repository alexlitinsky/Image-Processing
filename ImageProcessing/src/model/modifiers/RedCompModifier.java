package model.modifiers;

import model.ImageModel;

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
  public ImageModel apply(ImageModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("invalid model");
    }
    ImageModel build = new ImageModel(model.getDimensions()[0], model.getDimensions()[1]);
    for (int i = 0; i < model.getDimensions()[0]; i++) {
      for (int j = 0; j < model.getDimensions()[1]; j++) {
        int red = model.getPixel(i, j).getRGB()[0];
        build.assignPixels(i, j, red, red, red);
      }
    }
    return build;
  }
}
