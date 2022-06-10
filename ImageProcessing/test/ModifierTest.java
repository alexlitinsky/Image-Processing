import org.junit.Test;

import model.ImageModel;
import model.ImageModelImpl;
import model.modifiers.BlueCompModifier;
import model.modifiers.BrightnessModifier;
import model.modifiers.FlipModifier;
import model.modifiers.GreenCompModifier;
import model.modifiers.IntensityCompModifier;
import model.modifiers.LumaCompModifier;
import model.modifiers.Modifier;
import model.modifiers.RedCompModifier;
import model.modifiers.ValueCompModifier;
import view.ImageTextView;
import view.TextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Class to represent testing for all of our modifiers. The following modifiers are tested:
 * blue component
 * brightness
 * flip (vertical & horizontal)
 * green component
 * intensity component
 * luma component
 * red component
 * value component
 */
public class ModifierTest {
  private ImageModel img;
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
   * Method to test blue component modifiers. Should change RGB values of every pixel to be the blue
   * value before being modified.
   */
  @Test
  public void testBlueComponent() {
    initModel2x2();
    this.img.assignPixels(0, 0, 1, 1, 2);
    this.img.assignPixels(1, 0, 1, 1, 3);
    this.img.assignPixels(0, 1, 1, 1, 4);
    this.img.assignPixels(1, 1, 1, 1, 5);
    Modifier mod = new BlueCompModifier();
    this.img = this.img.newModdedImage(mod);
    for (int i : this.img.getPixel(0, 0).getRGB()) {
      assertEquals(2, i);
    }
    for (int i : this.img.getPixel(1, 0).getRGB()) {
      assertEquals(3, i);
    }
    for (int i : this.img.getPixel(0, 1).getRGB()) {
      assertEquals(4, i);
    }
    for (int i : this.img.getPixel(1, 1).getRGB()) {
      assertEquals(5, i);
    }

  }

  /**
   * Method to test brightness modifiers. Should change the RGB values of every pixel by the given
   * amount.
   */
  @Test
  public void testBrightnessModifier() {
    initModel1x1();
    // check the brightness of the pixel in the model before applying the filter
    for (int i : this.img.getPixel(0, 0).getRGB()) {
      assertEquals(1, i);
    }

    // instantiate & apply modifier
    Modifier mod = new BrightnessModifier(10);
    this.img = this.img.newModdedImage(mod);
    for (int i : this.img.getPixel(0, 0).getRGB()) {
      assertEquals(11, i);
    }

    // test the limits - set to 255
    Modifier mod2 = new BrightnessModifier(244);
    this.img = this.img.newModdedImage(mod2);
    for (int i : this.img.getPixel(0, 0).getRGB()) {
      assertEquals(255, i);
    }

    // test the limits - set to 0
    Modifier mod3 = new BrightnessModifier(-255);
    this.img = this.img.newModdedImage(mod3);
    for (int i : this.img.getPixel(0, 0).getRGB()) {
      assertEquals(0, i);
    }

    // absolutely invalid brightness value checks - invalid no matter the current RGB values

    // locally invalid brightness value checks - could be valid modifier, but invalid in this case

  }

  /**
   * Method to test a vertical flip of an image model. Should have the same RGB values,
   * just flipped vertically from pre-modifier (what was on the top is now on the bottom
   * and vice versa).
   */
  @Test
  public void testVerticalFlipModifier() {
    this.initModel2x2();
    // test the pre-modifier view
    TextView view = new ImageTextView(img, out);
    assertEquals("(0, 0, 0) (1, 1, 1) \n"
            + "(2, 2, 2) (3, 3, 3) ", view.toString());

    Modifier mod = new FlipModifier(true);
    this.img = this.img.newModdedImage(mod);
    // test the post-modifier view
    TextView view2 = new ImageTextView(img, out);
    assertEquals("(2, 2, 2) (3, 3, 3) \n"
            + "(0, 0, 0) (1, 1, 1) ", view2.toString());
  }

  /**
   * Method to test a horizontal flip of an image model. Should have the same RGB values,
   * just flipped horizontally from pre-modifier (what was on the left is now on the right
   * and vice versa).
   */
  @Test
  public void testHorizontalFlipModifier() {
    this.initModel2x2();
    // test the pre-modifier view
    TextView view = new ImageTextView(img, out);
    assertEquals("(0, 0, 0) (1, 1, 1) \n"
            + "(2, 2, 2) (3, 3, 3) ", view.toString());

    Modifier mod = new FlipModifier(false);
    this.img = this.img.newModdedImage(mod);
    // test the post-modifier view
    TextView view2 = new ImageTextView(img, out);
    assertEquals("(1, 1, 1) (0, 0, 0) \n"
            + "(3, 3, 3) (2, 2, 2) ", view2.toString());
  }

  /**
   * Method to test green component modifiers. RGB values should be set to green value pre-modifier.
   */
  @Test
  public void testGreenComponent() {
    initModel1x1();
    this.img.assignPixels(0, 0, 1, 2, 1);
    Modifier mod = new GreenCompModifier();
    this.img = this.img.newModdedImage(mod);
    for (int i : this.img.getPixel(0, 0).getRGB()) {
      assertEquals(2, i);
    }
  }

  /**
   * Method to test intensity component modifiers. Should make it so all components are set to
   * the average of the three components pre-modifier.
   */
  @Test
  public void testIntensityComponent() {
    initModel1x1();
    this.img.assignPixels(0, 0, 1, 2, 3);
    Modifier mod = new IntensityCompModifier();
    this.img = this.img.newModdedImage(mod);
    for (int i : this.img.getPixel(0, 0).getRGB()) {
      assertEquals(2, i);
    }
  }

  /**
   * Method to test the luma comp modifier. Each value should be set to the rounded, weighted luma
   * sum.
   */
  @Test
  public void testLumaComponent() {
    initModel1x1();
    this.img.assignPixels(0, 0, 1, 2, 3);
    Modifier mod = new LumaCompModifier();
    this.img = this.img.newModdedImage(mod);
    for (int i : this.img.getPixel(0, 0).getRGB()) {
      assertEquals(1, i);
    }
  }

  /**
   * Method to test the red comp modifier. Each value should be set to the red value pre-modifier.
   */
  @Test
  public void testRedComponent() {
    initModel1x1();
    this.img.assignPixels(0, 0, 1, 2, 3);
    Modifier mod = new RedCompModifier();
    this.img = this.img.newModdedImage(mod);
    for (int i : this.img.getPixel(0, 0).getRGB()) {
      assertEquals(1, i);
    }
  }

  /**
   * Method to test the value comp modifier. Each value should be set to the highest RGB value
   * pre-modifier.
   */
  @Test
  public void testValueComponent() {
    initModel1x1();
    this.img.assignPixels(0, 0, 1, 2, 3);
    Modifier mod = new ValueCompModifier();
    this.img = this.img.newModdedImage(mod);
    for (int i : this.img.getPixel(0, 0).getRGB()) {
      assertEquals(3, i);
    }
  }
}