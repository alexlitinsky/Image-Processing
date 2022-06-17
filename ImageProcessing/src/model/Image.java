package model;

import java.util.List;

import model.modifiers.Modifier;

/**
 * Represents an interface for an image. Has the ability to assign each individual pixel an image.
 * Can get the dimensions of an image. Can produce a new modded image. Can get a particular pixel.
 */
public interface Image {

  /**
   * Determines if this image is equal to another image. Two images are equal when all their pixels
   * are equal (meaning they have the same RGB values).
   *
   * @param image the image this image is being compared to
   * @return true if the two images are equal
   * @throws IllegalArgumentException if the given image is null
   */
  boolean imgEquals(Image image) throws IllegalArgumentException;

  /**
   * Assigns pixels to an image.
   *
   * @param x the x coordinate
   * @param y the y coordinate
   * @param r the red color
   * @param g the green color
   * @param b the blue color
   */
  void assignPixels(int x, int y, int r, int g, int b);

  /**
   * Creates a new modded image.
   *
   * @param mod the modification being implemented
   * @return the new iamge
   * @throws IllegalArgumentException if modifier is invalid
   */
  Image newModdedImage(Modifier mod) throws IllegalArgumentException;


  /**
   * Gets a particular pixel.
   *
   * @param x the x coordinate
   * @param y the y coordinate
   * @return the pixel given the coordinates
   * @throws IllegalArgumentException if any of the values are invalid
   */
  Pixel getPixel(int x, int y) throws IllegalArgumentException;

  /**
   * Gets the dimensions of an image.
   *
   * @return an array of dimensions (ints)
   */
  int[] getDimensions();

  /**
   * Finds a particular pixel in an image.
   *
   * @return the coordinates of the pixel
   */
  int[] findPixel(Pixel p);

  /**
   * Converts the image of pixels into a singular list.
   *
   * @return a list of pixels
   */
  List<Pixel> convertToList();
}
