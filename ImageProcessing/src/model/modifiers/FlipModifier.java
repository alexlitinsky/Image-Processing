package model.modifiers;

import model.ImageModel;

/**
 * Class tor represent modifiers for a horizontal or vertical flip.
 */
public class FlipModifier implements Modifier{

  private boolean isVertical;

  /**
   * Constructor for this flip modifier.
   * //@param destination the name of the file the new, modified model should be sent to
   * //@param model the model being modified
   * @param isVertical true if the modifier is for a vertical flip
   */
  public FlipModifier(boolean isVertical) {
    this.isVertical = isVertical;
  }

  /**
   * Applies a modifier to an image
   *
   * @return
   */
  @Override
  public ImageModel apply(ImageModel model) {
    ImageModel build = new ImageModel(model.getDimensions()[0], model.getDimensions()[1]);
    for (int i = 0; i < model.getDimensions()[0]; i++) {
      for (int j = 0; j < model.getDimensions()[1]; j++) {
        int red = model.getPixel(i, j).getRGB()[0];
        int green = model.getPixel(i, j).getRGB()[1];
        int blue = model.getPixel(i, j).getRGB()[2];
        if (this.isVertical) {
          build.assignPixels(i, (model.getDimensions()[1] - 1) - j, red, green, blue);
        } else {
          build.assignPixels((model.getDimensions()[0] - 1) - i, j, red, green, blue);
        }
      }
    }
    return build;
  }
}
