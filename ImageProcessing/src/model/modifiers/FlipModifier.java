package model.modifiers;

import model.ImageModelImpl;

/**
 * Class tor represent modifiers for a horizontal or vertical flip. Takes in a isVertical boolean
 * variable. If true, we change the position of the height of an image. Else, we change the position
 * of the width of an image.
 */
public class FlipModifier implements Modifier {

  private final boolean isVertical;

  /**
   * Constructor for this flip modifier.
   *
   * @param isVertical true if the modifier is for a vertical flip
   */
  public FlipModifier(boolean isVertical) {
    this.isVertical = isVertical;
  }

  @Override
  public ImageModelImpl apply(ImageModelImpl model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("invalid model");
    }
    ImageModelImpl build = new ImageModelImpl(model.getDimensions()[0], model.getDimensions()[1]);
    for (int i = 0; i < model.getDimensions()[0]; i++) {
      for (int j = 0; j < model.getDimensions()[1]; j++) {
        int red = model.getPixel(i, j).getRGB()[0];
        int green = model.getPixel(i, j).getRGB()[1];
        int blue = model.getPixel(i, j).getRGB()[2];
        if (this.isVertical) {
          build.assignPixels(i, (model.getDimensions()[0] - 1) - j, red, green, blue);
        } else {
          build.assignPixels((model.getDimensions()[1] - 1) - i, j, red, green, blue);
        }
      }
    }
    return build;
  }
}
