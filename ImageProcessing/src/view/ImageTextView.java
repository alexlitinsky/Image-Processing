package view;

import java.io.IOException;

import model.ImageModel;


/**
 * Represents an ImageTextView. prints out the pixels of a specific model and destination.
 */
public class ImageTextView implements TextView {
  protected ImageModel model;
  protected Appendable destination;

  /**
   * Constructor for image text views. Requires a model to view and a destination to send the view.
   * Returns the RGB values for each pixel in the image.
   *
   * @param model       the model being viewed
   * @param destination location where the view is sent
   */
  public ImageTextView(ImageModel model, Appendable destination) {
    this.model = model;
    this.destination = destination;
  }

  /**
   * Method to create a string representing this image based on the specified information type
   * to be visualized.
   *
   * @return
   */
  public String toString() {
    Appendable build = new StringBuilder();
    for (int i = 0; i < model.getDimensions()[1]; i++) {
      for (int j = 0; j < model.getDimensions()[0]; j++) {
        try {
          String red = String.valueOf(model.getPixel(j, i).getRGB()[0]);
          String green = String.valueOf(model.getPixel(j, i).getRGB()[1]);
          String blue = String.valueOf(model.getPixel(j, i).getRGB()[2]);
          String values = red + ", " + green + ", " + blue;
          build.append("(" + values + ") ");
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
      try {
        build.append("\n");
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    String output = build.toString();
    output = output.substring(0, output.length() - 1);
    return output;
  }
}
