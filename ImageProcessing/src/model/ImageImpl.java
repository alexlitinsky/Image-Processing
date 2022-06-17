package model;


import java.util.ArrayList;
import java.util.List;

import model.modifiers.Modifier;

/**
 * Class to represent an image. An image has a width, height, and a 2D array of pixels.
 * Can construct an image given a valid width and a height. Has a method to assign all the pixels
 * to their valid values. Can retrieve each individual pixel. Can retrieve dimensions of an image.
 */
public class ImageImpl implements Image {
  private final int width;
  private final int height;
  private final Pixel[][] imagePixels;

  /**
   * Constructor which makes a model for an image using a 2D array of pixels.
   *
   * @param width  the width of the image
   * @param height the height of the image
   * @throws IllegalArgumentException if the width or height is invalid
   */
  public ImageImpl(int width, int height) throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Invalid width or height.");
    }
    this.width = width;
    this.height = height;
    this.imagePixels = new PixelImpl[height][width];
  }

  /**
   * Assigns the given RBG values to this pixel in this position.
   *
   * @param x the x coordinate
   * @param y the y coordinate
   * @param r the red color value
   * @param g the green color value
   * @param b the blue color value
   */
  @Override
  public void assignPixels(int x, int y, int r, int g, int b) {
    if (x > this.getDimensions()[0] - 1 || y > this.getDimensions()[1] - 1 || x < 0 || y < 0) {
      throw new IllegalArgumentException("Pixel to be assigned must be in the image");
    }
    imagePixels[y][x] = new PixelImpl(r, g, b);
  }

  // might need to change canvas size in the future

  /**
   * Creates a new image given a modifier.
   *
   * @param mod the modifier being used
   * @return the new image
   * @throws IllegalArgumentException if the modifier is null
   */
  @Override
  public Image newModdedImage(Modifier mod) throws IllegalArgumentException {
    if (mod == null) {
      throw new IllegalArgumentException("Null modifier");
    }
    // we have to return version to put it in the hashmap
    return mod.apply(this);
  }

  /**
   * Gets an individual pixel.
   *
   * @param x the x coordinate
   * @param y the y coordinate
   * @return the specific pixel
   */
  @Override
  public Pixel getPixel(int x, int y) throws IllegalArgumentException {
    if (x < 0 || y < 0 || x > this.getDimensions()[0] || y > this.getDimensions()[1]) {
      throw new IllegalArgumentException("Invalid parameters");
    }
    return imagePixels[y][x];
  }

  /**
   * Gets the dimensions of an image.
   *
   * @return an array of the width and height of the image
   */
  @Override
  public int[] getDimensions() {
    return new int[]{this.width, this.height};
  }

  @Override
  public int[] findPixel(Pixel p) {
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        if (this.getPixel(i, j) == p) {
          return new int[] {i, j};
        }
      }
    }
    throw new IllegalArgumentException("Couldn't find pixel.");
  }

  @Override
  public List<Pixel> convertToList() {
    List<Pixel> list = new ArrayList<>();
    for (int i = 0; i < imagePixels[0].length; i++) {
      for (int j = 0; j < imagePixels.length; j++) {
        list.add(this.getPixel(i, j));
      }
    }
    return list;
  }


}
