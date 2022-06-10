package model.modifiers;

import model.ImageModel;

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
  ImageModel apply(ImageModel model) throws IllegalArgumentException;
}
