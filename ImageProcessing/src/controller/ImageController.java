package controller;

import java.util.Map;

import model.ImageModel;

/**
 * Represents an interface for an image controller. Has the ability to play a controller as well
 * as get the versions of the images.
 */
public interface ImageController {

  /**
   * Plays the controller.
   * @throws IllegalStateException if the controller breaks down
   */
  void playGame() throws IllegalStateException;

  /**
   * Gets the versions of images of a specific controller.
   * @return  a hashmap of images
   */
  Map<String, ImageModel> getVersions();
}
