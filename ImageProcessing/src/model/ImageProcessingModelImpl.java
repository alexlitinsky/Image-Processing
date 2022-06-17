package model;

import java.util.Map;

/**
 * Class to represent our current implementation of an image processing model. Contains all versions
 * of images created by loading and editing images.
 */
public class ImageProcessingModelImpl implements ImageProcessingModel {
  private Map<String, Image> images;

  /**
   * Method to retrieve all the images in this image processing model.
   *
   * @return the map containing all the images in this model and their keys as strings
   */
  @Override
  public Map<String, Image> getImages() {
    return this.images;
  }
}
