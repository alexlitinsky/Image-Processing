package model;

import java.io.IOException;

/**
 * Class to represent an image.
 */
public class Image {
  private int width;
  private int height;
  protected Pixel[][] imagePixels;

  /**
   * Constructor to create an image representing a 2D array of pixels.
   * @param width
   * @param height
   */
  public Image(int width, int height) {
    this.width = width;
    this.height = height;
    this.imagePixels = new Pixel[height][width];
  }

  public void assignPixels(int x, int y, int r, int g, int b) {
    imagePixels[y][x] = new Pixel(r, g, b);
  }

  public String toString() {
    Appendable output = new StringBuilder();
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        try {
          output.append(imagePixels[j][i].toString());
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    }
    return output.toString();
  }
}
