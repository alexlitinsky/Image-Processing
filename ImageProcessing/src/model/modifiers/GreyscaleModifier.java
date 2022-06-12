package model.modifiers;

/**
 * Representation for a greyscale transformation filter.
 */
public class GreyscaleModifier extends ATransform {

  /**
   * A constructor for a grayscale modifier object. Default kernel is the luma of an image.
   * @throws IllegalArgumentException if any of the entries of the kernel are odd
   */
  public GreyscaleModifier() throws IllegalArgumentException {
    super(new double[][] {
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}});
  }

  /**
   * A custom constructor for greyscale.
   * @param kernel the kernel being applied for greyscale
   * @throws IllegalArgumentException if any of the entries of the kernel are odd
   */
  public GreyscaleModifier(double[][] kernel) throws IllegalArgumentException {
    super(kernel);
  }
}
