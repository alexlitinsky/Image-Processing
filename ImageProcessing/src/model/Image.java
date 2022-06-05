package model;

import java.awt.*;
import java.awt.image.BufferedImage;
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
   *
   * @param width
   * @param height
   */
  public Image(int width, int height) throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Invalid width or height.");
    }
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

  /**
   * Renders a image
   * @return a buffered image
   */
  public BufferedImage renderImage() {
    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        // might be a simpler way of doing these following operations?
        Color col = new new Color(Integer.parseInt(imagePixels[i][j].toString().substring(0, 1)),
                Integer.parseInt(imagePixels[i][j].toString().substring(2, 3)),
                Integer.parseInt(imagePixels[i][j].toString().substring(4, 5)));
        img.setRGB(j, i, col.getRGB());
      }
    }
    return img;
  }

  // might need to change canvas size in the future

  public void applyFilter(Modifer mod) throws IllegalArgumentException {
    if (mod == null) { throw new IllegalArgumentException("Null modifier"); }
    this.imagePixels = mod.apply(this);
  }

  public Pixel getPixel(int x, int y) {
    return imagePixels[y][x];
  }

  public Pixel[][] getAllPixels() {
    Pixel [][] copy = new Pixel[imagePixels.length][];
    for(int i = 0; i < imagePixels.length; i++)
      copy[i] = imagePixels[i].clone();
    return copy;
  }

  public int[] getProps() {
    return new int[] {this.width, this.height};
  }

}
