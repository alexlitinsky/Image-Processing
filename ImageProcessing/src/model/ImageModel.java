package model;


import model.modifiers.Modifier;

/**
 * Class to represent an image.
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
//    if (name.equals("")) {
//      throw new IllegalArgumentException("Name cannot be empty");
//    }
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Invalid width or height.");
    }
    //this.name = name;
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
    imagePixels[y][x] = new Pixel(r, g, b);
  }

  // for future assignment brainstorming
//  /**
//   * Renders a image
//   * @return a buffered image
//   */
//  public BufferedImage renderImageRGB() {
//    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//    for (int i = 0; i < height; i++) {
//      for (int j = 0; j < width; j++) {
//        // might be a simpler way of doing these following operations?
//        Color col = new Color(Integer.parseInt(imagePixels[i][j].toString().substring(0, 1)),
//                Integer.parseInt(imagePixels[i][j].toString().substring(2, 3)),
//                Integer.parseInt(imagePixels[i][j].toString().substring(4, 5)));
//        Color newCol = new Color(imagePixels[i][j].red, imagePixels[i][j].green,
//                imagePixels[i][j].blue);
//        img.setRGB(j, i, newCol.getRGB());
//      }
//    }
//    return img;
//  }


  // might need to change canvas size in the future

  public ImageModel newModdedImage(Modifier mod) throws IllegalArgumentException {
    if (mod == null) {
      throw new IllegalArgumentException("Null modifier");
    }
    // we have to return version to put it in the hashmap
    return mod.apply(this);
  }

  public Pixel getPixel(int x, int y) {
    return imagePixels[y][x];
  }

  public Pixel[][] getAllPixels() {
    Pixel[][] copy = new Pixel[imagePixels.length][];
    for (int i = 0; i < imagePixels.length; i++)
      copy[i] = imagePixels[i].clone();
    return copy;
  }

  public int[] getDimensions() {
    return new int[]{this.width, this.height};
  }

}
