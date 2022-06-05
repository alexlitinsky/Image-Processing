package view;

import java.io.IOException;

import model.ImageModel;

public class ImageRBGTextView extends ImageTextView implements TextView{

  public ImageRBGTextView(ImageModel model, Appendable destination) {
    super(model, destination);
  }

  /**
   * Method to create a string representing this image.
   *
   * @return
   */
  public String toString() {
    Appendable output = new StringBuilder();
    for (int i = 0; i < model.getDimensions()[0]; i++) {
      for (int j = 0; j < model.getDimensions()[1]; j++) {
        try {
          String red = String.valueOf(model.getPixel(i, j).getRBG()[0]);
          String green = String.valueOf(model.getPixel(i, j).getRBG()[1]);
          String blue = String.valueOf(model.getPixel(i, j).getRBG()[2]);
          String rgb = red + ", " + green + ", " + blue;
          output.append("(" + rgb + ") ");
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    }
    return output.toString();
  }
}
