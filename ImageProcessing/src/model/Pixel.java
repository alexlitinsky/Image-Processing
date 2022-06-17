package model;

/**
 * An interface of a pixel. Has the ability to retrieve each component of a pixel.
 */
public interface Pixel {
  /**
   * Method to determine if this pixel is equal to another pixel. Equality is generally when two
   * pixels share the same RGB values.
   * @param pixel the pixel being compared to
   * @return true if this pixel is equal to that pixel
   */
  boolean equals(Pixel pixel);

  /**
   * Gets each individual component of a rgb.
   * @return an array of ints including the rgb values
   */
  int[] getRGB();

  /**
   * Applies a transformation to all channels of a pixel.
   * @param val the value being applied
   * @return an array of the new pixels
   */
  double[] applyToAll(double val);

  /**
   * Applies a transformation to the red channel of a pixel.
   * @param val the value being applied
   * @return the new red pixel value
   */
  double applyToR(double val);

  /**
   * Applies a transformation to the red channel of a pixel.
   * @param val the value being applied
   * @return the new red pixel value
   */
  double applyToG(double val);

  /**
   * Applies a transformation to the red channel of a pixel.
   * @param val the value being applied
   * @return the new red pixel value
   */
  double applyToB(double val);
}
