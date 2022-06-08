import org.junit.Test;

import model.ImageModel;
import model.modifiers.BrightnessModifier;
import model.modifiers.Modifier;
import view.ImageTextView;
import view.TextView;

import static org.junit.Assert.assertEquals;

public class ImageModelTest {
  private ImageModel img;
  private Appendable out;

  /**
   * Method to abstract testing modifiers and initialize a commonly used test model.
   */
  private void initModel1x1() {
    img = new ImageModel(1, 1);
    img.assignPixels(0, 0, 1, 1, 1);
    out = new StringBuilder();
  }

  /**
   * Method to abstract testing modifiers and initialize a commonly used test model.
   */
  private void initModel2x2() {
    img = new ImageModel(2, 2);
    img.assignPixels(0, 0, 0, 0, 0);
    img.assignPixels(1, 0, 1, 1, 1);
    img.assignPixels(0, 1, 2, 2, 2);
    img.assignPixels(1, 1, 3, 3, 3);
    out = new StringBuilder();
  }

  @Test
  public void testConstructor1() {
    ImageModel image = new ImageModel(1, 1);
  }

  @Test
  public void testAssignPixels() {

  }

  @Test
  public void testNewModdedImage() {
    initModel1x1();
    Modifier mod = new BrightnessModifier(1);
    TextView view = new ImageTextView(img, new StringBuilder());
    assertEquals("(1, 1, 1) ", view.toString());
    ImageModel test = img.newModdedImage(mod);
    TextView view2 = new ImageTextView(test, new StringBuilder());
    assertEquals("(2, 2, 2) ", view2.toString());
  }
}