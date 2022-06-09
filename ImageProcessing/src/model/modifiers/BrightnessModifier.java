package model.modifiers;

import model.ImageModel;

/**
 * Represents a Brightness Modifier. Changes the brightness of an image with an associated value.
 * Applies the value to all pixels of an image.
 */
public class BrightnessModifier implements Modifier {
  private int value;

  /**
   * Initializes a Brightness Modifier with an associated value.
   */
  public BrightnessModifier(int value)  {
    this.value = value;
  }

  /**
   * Applies a modifier to an image and returns the new modified image. If red, green, blue value
   * is greater than 255, we set that value to the maximum rgb value.
   */
  @Override
  public ImageModel apply(ImageModel model) throws IllegalArgumentException {
    if (model == null) { throw new IllegalArgumentException("invalid model"); }
    ImageModel build = new ImageModel(model.getDimensions()[0], model.getDimensions()[1]);
    for (int i = 0; i < model.getDimensions()[0]; i++) {
      for (int j = 0; j < model.getDimensions()[1]; j++) {
        int red = model.getPixel(i, j).getRGB()[0] + value;
        int green = model.getPixel(i, j).getRGB()[1] + value;
        int blue = model.getPixel(i, j).getRGB()[2] + value;
        build.assignPixels(i, j, Math.min(255, red), Math.min(255, green), Math.min(255, blue));
      }
    }
    return build;
  }
}
