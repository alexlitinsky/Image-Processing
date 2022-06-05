package model;

public class Pixel {
  private int red;
  private int green;
  private int blue;

  public Pixel(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  public String toString() {
    StringBuilder build = new StringBuilder();
    build.append(red + " ");
    build.append(green + " ");
    build.append(blue + " ");
    String out = build.toString();
    return out;
  }
}
