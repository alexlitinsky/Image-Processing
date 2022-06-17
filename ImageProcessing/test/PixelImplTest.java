import org.junit.Test;

import model.Pixel;
import model.PixelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

  /**
   * Tests the applyToAll method. Should change all RGB values according to the specified value.
   * Should only return values between 0 and 255.
   */
  @Test
  public void testApplyToAll() {
    Pixel p = new PixelImpl(1, 3, 2);
    assertEquals(2.1, p.applyToAll(2.1)[0], 0);
    assertEquals(255, p.applyToAll(256)[0], 0);
    assertEquals(0, p.applyToAll(-1)[0], 0);
    assertEquals(6, p.applyToAll(2.0)[1], 0);
    assertEquals(4, p.applyToAll(2.0)[2], 0);
    assertEquals(0, p.applyToAll(0.0)[2], 0);
  }

  /**
   * Test applyToR(). Should return the red value multiplied by the given value.
   * Should only return values between 0 and 255.
   */
  @Test
  public void testApplyToR() {
    Pixel p = new PixelImpl(1, 3, 2);
    assertEquals(2.1, p.applyToR(2.1), 0);
    assertEquals(255, p.applyToR(256), 0);
    assertEquals(0.0, p.applyToR(0.0), 0);
    assertEquals(0.0, p.applyToR(-1), 0);
  }

  /**
   * Test applyToG(). Should return the green value multiplied by the given value.
   * Should only return values between 0 and 255.
   */
  @Test
  public void testApplyToG() {
    Pixel p = new PixelImpl(1, 4, 2);
    assertEquals(8.4, p.applyToG(2.1), 0);
    assertEquals(255, p.applyToG(256), 0);
    assertEquals(0.0, p.applyToG(0.0), 0);
    assertEquals(0, p.applyToG(-1), 0);
  }

  /**
   * Test applyToB(). Should return the blue value multiplied by the given value.
   * Should only return values between 0 and 255.
   */
  @Test
  public void testApplyToB() {
    Pixel p = new PixelImpl(1, 3, 2);
    assertEquals(4.2, p.applyToB(2.1), 0);
    assertEquals(255, p.applyToB(256), 0);
    assertEquals(0.0, p.applyToB(0.0), 0);
    assertEquals(0.0, p.applyToB(-1), 0);
  }

  /**
   * Test the pixel equals method. Two pixels should be equal if they have the same RGB values.
   */
  @Test
  public void testEquals() {
    Pixel p1 = new PixelImpl(1, 2, 3);
    Pixel p2 = new PixelImpl(1, 2, 3);
    Pixel p3 = new PixelImpl(1, 2, 4);
    assertTrue(p1.pixelEquals(p2));
    assertFalse(p1.pixelEquals(p3));
  }
}