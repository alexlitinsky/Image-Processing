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

  double[] applyToAll(double val);

  double applyToR(double val);

  double applyToG(double val);

  double applyToB(double val);
}
