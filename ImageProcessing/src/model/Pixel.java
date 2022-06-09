package model;

/**
 * A Representation of a single Pixel of a particular o image. Has three channels
 * (red, green, blue).
 */
public class Pixel {
  private int red;
  private int green;
  private int blue;

  /**
   * Creates a pixel object with three rgb values.
   * @param red the red value of a pixel
   * @param green the green value of a pixel
   * @param blue the vlue value of a pixel
   * @throws IllegalArgumentException if any of the values are greater than 255 or less than 0
   */
  public Pixel(int red, int green, int blue) throws IllegalArgumentException {
    if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
      throw new IllegalArgumentException("Invalid rgbs");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;

  }

  /**
   * Gets all the rgb values of a single pixel.
   * @return an array of the rgb values
   */
  public int[] getRGB() {
    return new int[]{this.red, this.green, this.blue};
  }
}
