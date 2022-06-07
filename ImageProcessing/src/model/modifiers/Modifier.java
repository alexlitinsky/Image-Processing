package model.modifiers;

import model.ImageModel;
import model.Pixel;

/**
 * An interface representing different ways to modify an image. All modifiers should have the below
 * methods.
 */
public interface Modifier {
  /**
   * Applies a modifier to an image
   * @param img the original image
   */
  public ImageModel apply();
}
