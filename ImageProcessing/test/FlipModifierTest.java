import org.junit.Test;

import model.ImageModel;
import model.modifiers.FlipModifier;
import model.modifiers.Modifier;
import view.ImageTextView;
import view.TextView;

import static org.junit.Assert.assertEquals;

/**
 * Class to represent a flip modifier.
 */
public class FlipModifierTest {
  private ImageModel img;
  private Appendable out;

  /**
   * Method to abstract testing modifiers and initialize a commonly used test model.
   */
  private void initModel2x2() {
    img = new ImageModel("test", 2, 2);
    img.assignPixels(0, 0, 0, 0, 0);
    img.assignPixels(1, 0, 1, 1, 1);
    img.assignPixels(0, 1, 2, 2, 2);
    img.assignPixels(1, 1, 3, 3, 3);
    out = new StringBuilder();
  }

  /**
   * Method to test a vertical flip of an image model.
   */
  @Test
  public void testApplyVertical() {
    this.initModel2x2();
    // test the pre-modifier view
    TextView view = new ImageTextView(img, out);
    assertEquals("(0, 0, 0) (1, 1, 1) \n"
            + "(2, 2, 2) (3, 3, 3) ", view.toString());

    Modifier mod = new FlipModifier("test flip", img, true);
    mod.apply();
    // test the post-modifier view
    TextView view2 = new ImageTextView(img.getVersion("test flip"), out);
    assertEquals("(2, 2, 2) (3, 3, 3) \n"
            + "(0, 0, 0) (1, 1, 1) ", view2.toString());
  }

  /**
   * Method to test a horizontal flip of an image model.
   */
  @Test
  public void testApplyHorizontal() {
    this.initModel2x2();
    // test the pre-modifier view
    TextView view = new ImageTextView(img, out);
    assertEquals("(0, 0, 0) (1, 1, 1) \n"
            + "(2, 2, 2) (3, 3, 3) ", view.toString());

    Modifier mod = new FlipModifier("test flip", img, false);
    mod.apply();
    // test the post-modifier view
    TextView view2 = new ImageTextView(img.getVersion("test flip"), out);
    assertEquals("(1, 1, 1) (0, 0, 0) \n"
            + "(3, 3, 3) (2, 2, 2) ", view2.toString());
  }
}