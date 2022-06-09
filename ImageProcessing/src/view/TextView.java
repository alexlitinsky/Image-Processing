package view;

/**
 * Interface for text views. Contains methods that all text views should have. Prints out an
 * image's rgb values in a (r, g, b) format.
 */
public interface TextView {
  /**
   * Prints an image's rgb values.
   *
   * @return the image's rgb values
   */
  String toString();
}
