package model.modifiers;

import model.Image;
import model.Pixel;

/**
 * An interface to modify an image.
 */
public interface Modifier {
  /**
   * Applies a modifier to an image
   * @param img the original image
   */
  public Pixel[][] apply(Image img);
}
