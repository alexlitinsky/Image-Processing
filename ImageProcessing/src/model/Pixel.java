package model;

/**
 * A Representation of a single Pixel of a particular o image.
 */
public class Pixel {
  private int red;
  private int blue;
  private int green;

  public Pixel(int red, int green, int blue) throws IllegalArgumentException {
    if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
      throw new IllegalArgumentException("Invalid rgbs");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;

  }

  public int[] getRGB() {
    return new int[] {this.red, this.green, this.blue};
  }
}
