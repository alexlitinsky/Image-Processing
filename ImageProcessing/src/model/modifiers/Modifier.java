package model.modifiers;

import model.Image;

/**
 * An interface representing different ways to modify an image. All modifiers should have the below
 * methods.
 */
public interface Modifier {
  /**
   * Applies a modifier to an image.
   *
   * @param model the original image
   */
  Image apply(Image model) throws IllegalArgumentException;
}
