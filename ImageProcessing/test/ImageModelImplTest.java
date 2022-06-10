import org.junit.Test;

import model.ImageModelImpl;
import model.modifiers.BrightnessModifier;
import model.modifiers.Modifier;
import view.ImageTextView;
import view.TextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Class to contain testing methods for the image model class. Tests the constructor and its
 * exceptions, as well as the assignPixels() and newModdedImage() method.
 */
public class ImageModelImplTest {
  private ImageModelImpl img;
  private Appendable out;

  /**
   * Method to abstract testing modifiers and initialize a commonly used test model.
   */
  private void initModel1x1() {
    img = new ImageModelImpl(1, 1);
    img.assignPixels(0, 0, 1, 1, 1);
    out = new StringBuilder();
  }

  /**
   * Method to abstract testing modifiers and initialize a commonly used test model.
   */
  private void initModel2x2() {
    img = new ImageModelImpl(2, 2);
    img.assignPixels(0, 0, 0, 0, 0);
    img.assignPixels(1, 0, 1, 1, 1);
    img.assignPixels(0, 1, 2, 2, 2);
    img.assignPixels(1, 1, 3, 3, 3);
    out = new StringBuilder();
  }

  /**
   * Method to test that the constructor initializes an image of the right size. Also checks that
   * it throws the right exception in a variety of invalid argument cases.
   */
  @Test
  public void testConstructor() {
    ImageModelImpl image = new ImageModelImpl(1, 1);
    assertEquals(1, image.getDimensions()[0]);
    assertEquals(1, image.getDimensions()[1]);
    // EXCEPTIONS
    try {
      ImageModelImpl img = new ImageModelImpl(0, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid width or height.", e.getMessage());
    }
    try {
      ImageModelImpl img = new ImageModelImpl(1, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid width or height.", e.getMessage());
    }
    try {
      ImageModelImpl img = new ImageModelImpl(0, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid width or height.", e.getMessage());
    }
    try {
      ImageModelImpl img = new ImageModelImpl(-1, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid width or height.", e.getMessage());
    }
    try {
      ImageModelImpl img = new ImageModelImpl(-1, -1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid width or height.", e.getMessage());
    }
    try {
      ImageModelImpl img = new ImageModelImpl(0, -1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid width or height.", e.getMessage());
    }
  }

  /**
   * Method to test the assignPixels() method in the model. Should assign the pixel at the given
   * position the given RGB components, as well as throw exceptions for illegal arguments.
   */
  @Test
  public void testAssignPixels() {
    initModel1x1();
    // pre-assign check
    assertEquals(1, img.getPixel(0, 0).getRGB()[0]);
    assertEquals(1, img.getPixel(0, 0).getRGB()[1]);
    assertEquals(1, img.getPixel(0, 0).getRGB()[2]);
    img.assignPixels(0, 0, 2, 3, 4);
    assertEquals(2, img.getPixel(0, 0).getRGB()[0]);
    assertEquals(3, img.getPixel(0, 0).getRGB()[1]);
    assertEquals(4, img.getPixel(0, 0).getRGB()[2]);
    // EXCEPTIONS
    // all neg
    try {
      img.assignPixels(0, 0, -1, -1, -1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid rgbs", e.getMessage());
    }
    // each one negative
    try {
      img.assignPixels(0, 0, -1, 0, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid rgbs", e.getMessage());
    }
    try {
      img.assignPixels(0, 0, 0, -1, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid rgbs", e.getMessage());
    }
    try {
      img.assignPixels(0, 0, 0, 0, -1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid rgbs", e.getMessage());
    }
    // bigger than 255
    try {
      img.assignPixels(0, 0, 256, 0, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid rgbs", e.getMessage());
    }
    // invalid position
    try {
      img.assignPixels(1, 0, 0, 0, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Pixel to be assigned must be in the image", e.getMessage());
    }
    try {
      img.assignPixels(-1, 0, 0, 0, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Pixel to be assigned must be in the image", e.getMessage());
    }
    try {
      img.assignPixels(0, 1, 0, 0, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Pixel to be assigned must be in the image", e.getMessage());
    }
  }

  /**
   * Test the newModdedImage() method, which applies a modifier to a model and returns the new,
   * modified model. Should alter the model in the expected way.
   */
  @Test
  public void testNewModdedImage() {
    initModel1x1();
    Modifier mod = new BrightnessModifier(1);
    TextView view = new ImageTextView(img, new StringBuilder());
    assertEquals("(1, 1, 1) ", view.toString());
    ImageModelImpl test = img.newModdedImage(mod);
    TextView view2 = new ImageTextView(test, new StringBuilder());
    assertEquals("(2, 2, 2) ", view2.toString());

    // null modifier case
    try {
      Modifier failMod = null;
      this.img = img.newModdedImage(failMod);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Null modifier", e.getMessage());
    }
  }
}