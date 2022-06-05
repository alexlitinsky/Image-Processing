package model;

/**
 * A Representation of a single Pixel of a particular o image.
 */
public class Pixel {
  private int red;
  private int green;
  private int blue;

  public Pixel(int red, int green, int blue) throws IllegalArgumentException {
    if (red < 0 || red > 255 || green < 0)
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  public String toString() {
    StringBuilder build = new StringBuilder();
    build.append(red + " "); "1 1 1"
    build.append(green + " ");
    build.append(blue + " ");
    return build.toString();
  }


}
