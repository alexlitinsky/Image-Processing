import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import model.Image;
import model.Pixel;

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
    Image img = new Image(1, 1);
    //Pixel pixel = new Pixel(1, 1, 1);
    img.assignPixels(0, 0, 1, 1, 1 );
    assertEquals("(1, 1, 1) ", img.toString());


  }
}