import org.junit.Test;

import model.ImageModel;
import model.modifiers.BrightnessModifier;
import model.modifiers.Modifier;
import view.ImageTextView;
import view.TextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Contains testing for constructor and all methods in the text view class.
 */
public class ImageTextViewTest {

  /**
   * Method to test the toString method for the ImageVILTextView class. The string output will
   * depend on the type of view requested: value, intensity or luma.
   */
  @Test
  public void testToString() {
    ImageModel img = new ImageModel(2, 2);
    img.assignPixels(0, 0, 2, 3, 4);
    img.assignPixels(1, 0, 23, 255, 4);
    img.assignPixels(0, 1, 100, 3, 6);
    img.assignPixels(1, 1, 2, 1, 0);
    Appendable out = new StringBuilder();

    // testing string output of RGB view
    TextView view = new ImageTextView(img, out);
    assertEquals("(2, 3, 4) (23, 255, 4) \n"
            + "(100, 3, 6) (2, 1, 0) ", view.toString());
  }

  /**
   * Method to test that instantiating a new view with an updated model is different.
   */
  @Test
  public void testViewChanges() {
    ImageModel img = new ImageModel(2, 2);
    img.assignPixels(0, 0, 2, 3, 4);
    img.assignPixels(1, 0, 23, 255, 4);
    img.assignPixels(0, 1, 100, 3, 6);
    img.assignPixels(1, 1, 2, 1, 0);
    Appendable out = new StringBuilder();
    Modifier mod = new BrightnessModifier(1);
    // pre-mod view
    TextView view1 = new ImageTextView(img, out);
    img = img.newModdedImage(mod);
    // post-mod view
    TextView view2 = new ImageTextView(img, out);
    assertNotEquals(view1.toString(), view2.toString());
  }
}