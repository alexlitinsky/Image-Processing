package model.modifiers;

/**
 * Represents an image filter for blurring an image.
 */
public class Blur extends AFilter {

  /**
   * A constructor for blurring an image.
   * @throws IllegalArgumentException if any of the entries of the kernel are odd
   */
  public Blur() throws IllegalArgumentException {
    super(new double[][]{
            {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0},
            {1.0 / 8.0, 1.0 / 4.0, 1.0 / 8.0},
            {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0}});
  }

  /**
   * A custom constructor for blurring an image.
   * @param kernel the kernel being applied for blurring an image
   * @throws IllegalArgumentException if any of the entries of the kernel are odd
   */
  public Blur(double[][] kernel) throws IllegalArgumentException {
    super(kernel);
  }




}
