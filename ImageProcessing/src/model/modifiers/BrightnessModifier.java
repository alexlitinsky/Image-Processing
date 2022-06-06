package model.modifiers;

import model.ImageModel;
import model.Pixel;

/**
 *
 */
public class BrightnessModifier implements Modifier{
  private String destination;
  private ImageModel model;
  private int value;

  public BrightnessModifier(String destination, ImageModel model, int value) {
    this.destination = destination;
    this.model = model;
    this.value = value;
  }

  /**
   * Applies a modifier to an image and returns the new modified image.
   */
  @Override
  public void apply() {
    ImageModel build = new ImageModel(destination, model.getDimensions()[0], model.getDimensions()[1]);
    for (int i = 0; i < model.getDimensions()[0]; i++) {
      for (int j = 0; j < model.getDimensions()[1]; j++) {
        int red = model.getPixel(i, j).getRBG()[0] + value;
        int green = model.getPixel(i, j).getRBG()[1] + value;
        int blue = model.getPixel(i, j).getRBG()[2] + value;
        if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
          throw new IllegalArgumentException("Brightness value is invalid");
        }
        build.assignPixels(i, j, red, green, blue);
      }
    }
    model.addVersion(build);
  }
}
