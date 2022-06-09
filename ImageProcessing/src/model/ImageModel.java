package model;


import model.modifiers.Modifier;

/**
 * Class to represent an image. An image has a width, height, and an array of pixels. Can construct
 * an image given a valid width and a height. Has a method to assign all the pixels to their valid
 * values. Can retrieve each individual pixel. Can retrieve dimensions of an image.
 */
public class ImageModel {
  private final int width;
  private final int height;
  private final Pixel[][] imagePixels;

  /**
   * Constructor which makes a model for an image using a 2D array of pixels.
   * <p>
   * //@param name   the name for this model of an image
   *
   * @param width  the width of the image
   * @param height the height of the image
   * @throws IllegalArgumentException if
   */
  public ImageModel(int width, int height) throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Invalid width or height.");
    }
    this.width = width;
    this.height = height;
    this.imagePixels = new Pixel[height][width];
  }

  /**
   * @param x the x coordinate
   * @param y the y coordinate
   * @param r the red color value
   * @param g the green color value
   * @param b the blue color value
   */
  public void assignPixels(int x, int y, int r, int g, int b) {
    if (x > this.getDimensions()[0] - 1 || y > this.getDimensions()[1] - 1 || x < 0 || y < 0) {
      throw new IllegalArgumentException("Pixel to be assigned must be in the image");
    }
    imagePixels[y][x] = new Pixel(r, g, b);
  }

  // might need to change canvas size in the future

  /**
   * Creates a new image given a modifier
   *
   * @param mod the modifier being used
   * @return the new image
   * @throws IllegalArgumentException if the modifier is null
   */
  public ImageModel newModdedImage(Modifier mod) throws IllegalArgumentException {
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
  public int[] getDimensions() {
    return new int[]{this.width, this.height};
  }

}
