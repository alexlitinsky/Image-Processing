package model.modifiers;

import model.ImageModelImpl;

/**
 * Represents an intensity modifier. Changes the rgb values to the average of all 3 rgb values.
 */
public class IntensityCompModifier implements Modifier {

  /**
   * Constructs an intensity modifier object.
   */
  public IntensityCompModifier() {
    // no fields to initialize
  }

  @Override
  public ImageModelImpl apply(ImageModelImpl model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("invalid model");
    }
    ImageModelImpl build = new ImageModelImpl(model.getDimensions()[0], model.getDimensions()[1]);
    for (int i = 0; i < model.getDimensions()[0]; i++) {
      for (int j = 0; j < model.getDimensions()[1]; j++) {
        int its = (model.getPixel(i, j).getRGB()[0] + model.getPixel(i, j).getRGB()[1] +
                model.getPixel(i, j).getRGB()[2]) / 3;
        build.assignPixels(i, j, its, its, its);
      }
    }
    return build;
  }
}