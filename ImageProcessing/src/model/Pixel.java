package model;

/**
 * A Representation of a single Pixel of a particular o image.
 */
public class Pixel {
  protected int red;
  protected int green;
  protected int blue;

  public Pixel(int red, int green, int blue) throws IllegalArgumentException {
    if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
      throw new IllegalArgumentException("Invalid rgbs");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  public String toString() {
    StringBuilder build = new StringBuilder();
    build.append("(" + red + ", ");
    build.append(green + ", ");
    build.append(blue + ") ");
    return build.toString();
  }


}
