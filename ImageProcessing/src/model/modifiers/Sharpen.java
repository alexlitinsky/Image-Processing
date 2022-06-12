package model.modifiers;

/**
 * Represents an image filter for sharpening an image.
 */
public class Sharpen extends AFilter {

  /**
   * A constructor for sharpening an image.
   * @throws IllegalArgumentException if any of the entries of the kernel are odd
   */
  public Sharpen() throws IllegalArgumentException {
    super(new double[][]{
            {-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0},
            {-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0},
            {-1.0 / 8.0, 1.0 / 4.0, 1.0, 1.0 / 4.0, -1.0 / 8.0},
            {-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0},
            {-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0}});
  }

  /**
   * A custom constructor for sharpening an image.
   * @param kernel the kernel being represented for the image filter
   * @throws IllegalArgumentException if any of the entries of the kernel are odd
   */
  public Sharpen(double[][] kernel) throws IllegalArgumentException {
    super(kernel);
  }
}
