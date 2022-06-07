package model.modifiers;

import model.ImageModel;

/**
 * Class tor represent modifiers for a horizontal or vertical flip.
 */
public class FlipModifier implements Modifier{
  private String destination;
  private ImageModel model;
  private boolean isVertical;

  /**
   * Constructor for this flip modifier.
   * @param destination the name of the file the new, modified model should be sent to
   * @param model the model being modified
   * @param isVertical true if the modifier is for a vertical flip
   */
  public FlipModifier(String destination, ImageModel model, boolean isVertical) {
    this.destination = destination;
    this.model = model;
    this.isVertical = isVertical;
  }

  /**
   * Applies a modifier to an image
   */
  @Override
  public void apply() {
    int width = model.getDimensions()[0];
    int height = model.getDimensions()[1];
    ImageModel build = new ImageModel(destination, width, height);
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int red = model.getPixel(i, j).getRBG()[0];
        int green = model.getPixel(i, j).getRBG()[1];
        int blue = model.getPixel(i, j).getRBG()[2];
        if (this.isVertical) {
          build.assignPixels(i, (height - 1) - j, red, green, blue);
        } else {
          build.assignPixels((width - 1) - i, j, red, green, blue);
        }
      }
    }
    model.addVersion(build);
  }
}
