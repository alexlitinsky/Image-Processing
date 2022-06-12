package model.modifiers;

/**
 * Represents an image transformation with a sepia filter.
 */
public class Sepia extends ATransform {

  /**
   * A constructor for sepia.
   * @throws IllegalArgumentException if the kernel array entries are odd
   */
  public Sepia() throws IllegalArgumentException {
    super(new double[][]{
            {0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}});
  }

  /**
   * A custom constructor for sepia.
   * @param kernel the kernel (filter) of seipa being appled
   * @throws IllegalArgumentException if the kernel array entries are odd
   */
  public Sepia(double[][] kernel) throws IllegalArgumentException {
    super(kernel);
  }
}
