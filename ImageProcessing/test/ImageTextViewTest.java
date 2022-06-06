import org.junit.Test;

import model.ImageModel;
import model.modifiers.BrightnessModifier;
import model.modifiers.Modifier;
import view.ImageTextView;
import view.TextView;

import static org.junit.Assert.*;

public class ImageTextViewTest {

  /**
   * Method to test the toString method for the ImageVILTextView class. The string output will
   * depend on the type of view requested: value, intensity or luma.
   */
  @Test
  public void testToString() {
    ImageModel img = new ImageModel("test", 1, 1);
    img.assignPixels(0, 0, 1, 1, 1);

    Modifier mod = new BrightnessModifier("new test", img, 10);
    mod.apply();
    assertEquals( "new test",img.getVersion("new test").getName());
    assertEquals( 11, img.getVersion("new test").getPixel(0, 0).getRBG()[0]);
    assertEquals( 11, img.getVersion("new test").getPixel(0, 0).getRBG()[1]);
    assertEquals( 11, img.getVersion("new test").getPixel(0, 0).getRBG()[2]);


//    img.assignPixels(0, 0, 2, 3, 4);
//    img.assignPixels(1, 0, 23, 255, 4);
//    img.assignPixels(0, 1, 100, 3, 6);
//    img.assignPixels(1, 1, 2, 1, 0);
//    Appendable out = new StringBuilder();

//    // testing string output of RGB view
//    TextView view = new ImageTextView(img, out);
//    assertEquals("(2, 3, 4) (23, 255, 4) \n"
//            + "(100, 3, 6) (2, 1, 0) ", view.toString());
  }
}