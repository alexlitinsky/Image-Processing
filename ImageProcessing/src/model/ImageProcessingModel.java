package model;

import java.util.Map;

/**
 * Interface to represent features that all ImageProcessingModels should have. This includes
 * required methods.
 */
public interface ImageProcessingModel {
  /**
   * Method to retrieve all the images in this image processing model.
   *
   * @return the map containing all the images in this model and their keys as strings
   */
  Map<String, Image> getImages();
}
