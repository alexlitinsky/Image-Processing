package view;

import java.io.IOException;

import model.ImageModel;

public class ImageVILTextView extends ImageTextView implements TextView {
  /**
   * This enum represents the three different pieces of information about an image
   * this text view can represent.
   */
  enum VILType {
    Value, Intensity, Luma
  }

  /**
   * Constructor for a luma text view. When converted to a string, displays the value, intensity or
   * luma based on which value is requested.
   * @param model the model being viewed
   * @param destination the destination the view is sent to
   */
  ImageVILTextView(ImageModel model, Appendable destination, VILType type) {
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
          output.append(model.getPixel(i, j).toString());
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    }
    return output.toString();
  }
}
