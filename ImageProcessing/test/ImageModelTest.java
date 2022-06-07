import org.junit.Test;

import model.ImageModel;
import model.modifiers.BrightnessModifier;
import model.modifiers.Modifier;
import view.ImageTextView;
import view.TextView;

import static org.junit.Assert.*;

public class ImageModelTest {
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
  @Test
  public void testConstructor1() {
    ImageModel image = new ImageModel("test",1, 1);
  }

  @Test
  public void testAssignPixels() {

  }

  @Test
  public void testApplyFilter() {
    initModel2x2();
    Modifier mod = new BrightnessModifier("modified", img, 1);
    TextView view = new ImageTextView(img, new StringBuilder());
    img.applyFilter(mod);
    assertEquals("", view.toString());
  }
}