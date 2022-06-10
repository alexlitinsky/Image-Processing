package model;

import model.modifiers.Modifier;

/**
 * Represents an interface for an image. Has the ability to assign each individual pixel an image.
 * Can get the dimensions of an image. Can produce a new modded image. Can get a particular pixel.
 */
public interface ImageModel {
  /**
   * Assigns pixels to an image.
   * @param x the x coordinate
   * @param y the y coordinate
   * @param r the red color
   * @param g the green color
   * @param b the blue color
   */
  void assignPixels(int x, int y, int r, int g, int b);

  /**
   * Creates a new modded image.
   * @param mod the modification being implemented
   * @return the new iamge
   * @throws IllegalArgumentException if modifier is invalid
   */
  ImageModel newModdedImage(Modifier mod) throws IllegalArgumentException;


  /**
   * Gets a particular pixel.
   * @param x the x coordinate
   * @param y the y coordinate
   * @return the pixel given the coordinates
   * @throws IllegalArgumentException if any of the values are invalid
   */
  Pixel getPixel(int x, int y) throws IllegalArgumentException;

  /**
   * Gets the dimensions of an image.
   * @return an array of dimensions (ints)
   */
  int[] getDimensions();
}
