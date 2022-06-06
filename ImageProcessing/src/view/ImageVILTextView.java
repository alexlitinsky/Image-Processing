package view;

import java.io.IOException;

import model.ImageModel;

public class ImageVILTextView extends ImageTextView implements TextView {
  private VILType type;

  /**
   * This enum represents the three different pieces of information about an image
   * this text view can represent.
   * Value is the maximum int a pixel can have for one of its RGB values. In our model,
   * this is always 255.
   * Intensity is the average of a pixel's three RGB values.
   * Luma is the weighted sum of a pixel's RGB values according to the following formula:
   * 0.2126r + 0.7152g + 0.0722b
   */
  public enum VILType {
    Value, Intensity, Luma
  }

  /**
   * Constructor for a luma text view. When converted to a string, displays the value, intensity or
   * luma based on which value is requested.
   * @param model the model being viewed
   * @param destination the destination the view is sent to
   * @param type the type of information to be visualized - is either value, intensity or luma
   */
  public ImageVILTextView(ImageModel model, Appendable destination, VILType type) {
    super(model, destination);
    this.type = type;
  }

  /**
   * Method to create a string representing this image based on the specified information type
   * to be visualized.
   *
   * @return
   */
  public String toString() {
    Appendable output = new StringBuilder();
    for (int i = 0; i < model.getDimensions()[0]; i++) {
      for (int j = 0; j < model.getDimensions()[1]; j++) {
        int[] rgb = model.getPixel(i,j).getRBG();
        switch (this.type) {
          case Value:
            try {
              output.append("(256, " + "256, " + "256) ");
            } catch (IOException e) {
              throw new RuntimeException(e);
            }
            break;
          case Intensity:
            int sum = 0;
            for (int k = 0; k < rgb.length; k++) {
              sum += rgb[k];
            }
            String average = String.valueOf(sum);
            try {
              output.append(average);
            } catch (IOException e) {
              throw new RuntimeException(e);
            }
            break;
          case Luma:
            double total = 0.0;
            total = rgb[0] * 0.2126 + rgb[1] * 0.7152 + rgb[2] * 0.0722;
            String luma = String.valueOf(total);
            try {
              output.append(luma);
            } catch (IOException e) {
              throw new RuntimeException(e);
            }
            break;
        }
      }
    }
    return output.toString();
  }
}
