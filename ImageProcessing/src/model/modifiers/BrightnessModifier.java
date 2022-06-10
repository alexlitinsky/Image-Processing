package model.modifiers;

import model.ImageModelImpl;

/**
 * Represents a Brightness Modifier. Changes the brightness of an image with an associated value.
 * Applies the value to all pixels of an image.
 */
public class BrightnessModifier implements Modifier {
  private int value;

  /**
   * Initializes a Brightness Modifier with an associated value.
   */
  public BrightnessModifier(int value) {
    this.value = value;
  }

  /**
   * Applies a modifier to an image and returns the new modified image. If red, green, blue value
   * is greater than 255, we set that value to the maximum rgb value. If any of the resulting
   * values are less than 0, then set the red, green, blue to 0.
   */
  @Override
  public ImageModelImpl apply(ImageModelImpl model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("invalid model");
    }
    ImageModelImpl build = new ImageModelImpl(model.getDimensions()[0], model.getDimensions()[1]);
    for (int i = 0; i < model.getDimensions()[0]; i++) {
      for (int j = 0; j < model.getDimensions()[1]; j++) {
        int red = Math.min(model.getPixel(i, j).getRGB()[0] + value, 255);
        int green = Math.min(model.getPixel(i, j).getRGB()[1] + value, 255);
        int blue = Math.min(model.getPixel(i, j).getRGB()[2] + value, 255);
        build.assignPixels(i, j, Math.max(0, red), Math.max(0, green), Math.max(0, blue));

      }
    }
    return build;
  }
}
