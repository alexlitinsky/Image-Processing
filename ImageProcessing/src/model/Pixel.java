package model;

/**
 * An interface of a pixel. Has the ability to retrieve each component of a pixel.
 */
public interface Pixel {
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
