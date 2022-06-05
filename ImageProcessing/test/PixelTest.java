import org.junit.Test;

import model.Pixel;

import static org.junit.Assert.*;

public class PixelTest {

  @Test
  public void testToString() {
    Pixel pix = new Pixel(1, 1, 1);
    assertEquals("(1 1 1) ", pix.toString());
  }
}