import org.junit.Test;

import model.Pixel;

import static org.junit.Assert.assertEquals;

public class PixelTest {

  @Test
  public void testPixel() {
    Pixel p = new Pixel(0, 1, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRed() {
    Pixel p = new Pixel(-1, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGreen() {
    Pixel p = new Pixel(1, -1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBlue() {
    Pixel p = new Pixel(1, 1, -1);
  }

  @Test
  public void testGetRGB() {
    Pixel p = new Pixel(0, 1, 2);
    assertEquals(0, p.getRGB()[0]);
    assertEquals(1, p.getRGB()[1]);
    assertEquals(2, p.getRGB()[2]);
  }

}