import org.junit.Test;

import model.Image;

import static org.junit.Assert.*;

public class ImageTest {

  @Test
  public void testConstructor1() {
    Image image = new Image(1, 1);
  }

  @Test
  public void testAssignPixels() {

  }

  @Test
  public void testToString() {
    Image image = new Image(1, 1);
    image.assignPixels(0, 0, 1, 1, 1);
    assertEquals("1 1 1 ", image.toString());
  }
}