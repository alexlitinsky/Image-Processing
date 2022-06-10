import org.junit.Test;

import model.PixelImpl;

import static org.junit.Assert.assertEquals;

/**
 * Represents testing for the pixel class. Tests the pixel constructor and all the class methods.
 * Tests exceptions for all three constructor arguments, as well as the getRGB() method.
 */
public class PixelImplTest {

  /**
   * Tests the pixel constructor.
   */
  @Test
  public void testPixel() {
    PixelImpl p = new PixelImpl(0, 1, 2);
    assertEquals(0, p.getRGB()[0]);
    assertEquals(1, p.getRGB()[1]);
    assertEquals(2, p.getRGB()[2]);
  }

  /**
   * Test for an invalid red component.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRed() {
    PixelImpl p = new PixelImpl(-1, 1, 1);
  }

  /**
   * Test for an invalid green component.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGreen() {
    PixelImpl p = new PixelImpl(1, -1, 1);
  }

  /**
   * Test for an invalid blue component.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBlue() {
    PixelImpl p = new PixelImpl(1, 1, -1);
  }

  /**
   * Tests the getRGB method. Should return the current RGB values of the pixel as an int[].
   */
  @Test
  public void testGetRGB() {
    PixelImpl p = new PixelImpl(0, 1, 2);
    assertEquals(0, p.getRGB()[0]);
    assertEquals(1, p.getRGB()[1]);
    assertEquals(2, p.getRGB()[2]);
  }
}