package model.modifiers;

import model.ImageModelImpl;

/**
 * Represents a modifier to change all the pixel values to the green component.
 */
public class GreenCompModifier implements Modifier {

  /**
   * Constructs a green modifier object.
   */
  public GreenCompModifier() {
    // no fields to be initialized
  }

  @Override
  public ImageModelImpl apply(ImageModelImpl model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("invalid model");
    }
    ImageModelImpl build = new ImageModelImpl(model.getDimensions()[0], model.getDimensions()[1]);
    for (int i = 0; i < model.getDimensions()[0]; i++) {
      for (int j = 0; j < model.getDimensions()[1]; j++) {
        int green = model.getPixel(i, j).getRGB()[1];
        build.assignPixels(i, j, green, green, green);
      }
    }
    return build;
  }
}