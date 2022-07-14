package model;

/**
 * A Representation of a single Pixel of a particular o image. Has three channels
 * (red, green, blue).
 */
public class PixelImpl implements Pixel {
  private int red;
  private int green;
  private int blue;

  /**
   * Creates a pixel object with three rgb values.
   *
   * @param red   the red value of a pixel
   * @param green the green value of a pixel
   * @param blue  the vlue value of a pixel
   * @throws IllegalArgumentException if any of the values are greater than 255 or less than 0
   */
  public PixelImpl(int red, int green, int blue) throws IllegalArgumentException {
    if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
      throw new IllegalArgumentException("Invalid rgbs");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;

  }

  /**
   * Method to determine if this pixel is equal to another pixel. Equality is generally when two
   * pixels share the same RGB values.
   *
   * @param pixel the pixel being compared to
   * @return true if this pixel is equal to that pixel
   */
  @Override
  public boolean pixelEquals(Pixel pixel) {
    return this.red == pixel.getRGB()[0]
            && this.green == pixel.getRGB()[1]
            && this.blue == pixel.getRGB()[2];
  }

  /**
   * Gets all the rgb values of a single pixel.
   *
   * @return an array of the rgb values
   */
  public int[] getRGB() {
    return new int[]{this.red, this.green, this.blue};
  }

  /**
   * Multiplies the RGB values of this pixel by the given value. Returns 0 or 255 if the result
   * is < 0 or > 255.
   *
   * @param val the value being applied
   * @return double to represent the product of the RGB values and the specified value
   */
  @Override
  public double[] applyToAll(double val) {
    //return new double[]{applyToR(val), applyToG(val), applyToB(val)};
    return new double[] { this.red * val, this.green * val, this.blue * val};
  }

  /**
   * Multiplies the red value by the given value. Returns 0 or 255 if the result is < 0 or > 255.
   *
   * @param val the value being applied
   * @return double to represent the product of the red value and the specified value
   */
  @Override
  public double applyToR(double val) {
    return Math.max(Math.min(this.red * val, 255), 0);
  }

  /**
   * Multiplies the green value by the given value. Returns 0 or 255 if the result is < 0 or > 255.
   *
   * @param val the value being applied
   * @return double to represent the product of the green value and the specified value
   */
  @Override
  public double applyToG(double val) {
    return Math.max(Math.min(this.green * val, 255), 0);
  }

  /**
   * Multiplies the blue value by the given value. Returns 0 or 255 if the result is < 0 or > 255.
   *
   * @param val the value being applied
   * @return double to represent the product of the blue value and the specified value
   */
  @Override
  public double applyToB(double val) {
    return Math.max(Math.min(this.blue * val, 255), 0);
  }

}
