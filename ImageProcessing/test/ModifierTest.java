import org.junit.Test;

import model.Image;
import model.ImageImpl;
import model.modifiers.BlueCompModifier;
import model.modifiers.BlurModifier;
import model.modifiers.BrightnessModifier;
import model.modifiers.FlipModifier;
import model.modifiers.GreenCompModifier;
import model.modifiers.GreyscaleModifier;
import model.modifiers.IntensityCompModifier;
import model.modifiers.LumaCompModifier;
import model.modifiers.Modifier;
import model.modifiers.RedCompModifier;
import model.modifiers.SepiaModifier;
import model.modifiers.SharpenModifier;
import model.modifiers.ValueCompModifier;
import view.ImageTextView;
import view.TextView;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Class to represent testing for all of our modifiers. The following modifiers are tested:
 * COMPONENT MODIFIERS
 * blue component
 * brightness
 * flip (vertical & horizontal)
 * green component
 * intensity component
 * luma component
 * red component
 * value component
 *
 * FILTER MODIFIERS
 * blur
 * sharpen
 *
 * TRANSFORM MODIFIERS
 * greyscale modifier
 * sepia modifier
 */
public class ModifierTest {
  private Image img;
  private Appendable out;

  /**
   * Method to abstract testing modifiers and initialize a commonly used test model.
   */
  private void initModel1x1() {
    img = new ImageImpl(1, 1);
    img.assignPixels(0, 0, 1, 1, 1);
    out = new StringBuilder();
  }

  /**
   * Method to abstract testing modifiers and initialize a commonly used test model.
   */
  private void initModel2x2() {
    img = new ImageImpl(2, 2);
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

  /**
   * Method to test the blur filter modifier. Each pixel's RGB values should be changed according
   * to the blur filter modifier's formula.
   */
  @Test
  public void testBlur() {
    initModel2x2();
    // pre-modifier check
    ImageTextView view = new ImageTextView(this.img, out);
    assertEquals("(0, 0, 0) (1, 1, 1) \n"
            + "(2, 2, 2) (3, 3, 3) ", view.toString());
    Modifier mod = new BlurModifier();
    this.img = mod.apply(this.img);
    // post-modifier check
    ImageTextView view2 = new ImageTextView(this.img, out);
    assertEquals("(0, 0, 0) (0, 0, 0) \n"
            + "(0, 0, 0) (1, 1, 1) ", view2.toString());
  }

  /**
   * Method to test the sharpen filter modifier. Each pixel's RGB values should be changed according
   * to the sharpen modifier's formula.
   */
  @Test
  public void testSharpen() {
    initModel2x2();
    // pre-modifier check
    ImageTextView view = new ImageTextView(this.img, out);
    assertEquals("(0, 0, 0) (1, 1, 1) \n"
            + "(2, 2, 2) (3, 3, 3) ", view.toString());
    Modifier mod = new SharpenModifier();
    this.img = mod.apply(this.img);
    // post-modifier check
    ImageTextView view2 = new ImageTextView(this.img, out);
    assertEquals("(1, 1, 1) (2, 2, 2) \n"
            + "(3, 3, 3) (3, 3, 3) ", view2.toString());
  }

  /**
   * Method to test the greyscale transform modifier. Each pixel's RGB values should be changed
   * according to the greyscale modifier's formula.
   */
  @Test
  public void testGreyscale() {
    // Case 1: all pixels are already grey
    initModel2x2();
    // pre-modifier check
    ImageTextView view = new ImageTextView(this.img, out);
    assertEquals("(0, 0, 0) (1, 1, 1) \n"
            + "(2, 2, 2) (3, 3, 3) ", view.toString());
    Modifier mod = new GreyscaleModifier();
    this.img = mod.apply(this.img);
    // post-modifier check - should be no change as all pixels started as shades of grey
    ImageTextView view2 = new ImageTextView(this.img, out);
    assertEquals(view.toString(), view2.toString());

    // Case 2: pixels are not already grey
    this.img = new ImageImpl(2, 2);
    this.img.assignPixels(0, 0, 1, 2, 3);
    this.img.assignPixels(1, 0, 4, 5, 6);
    this.img.assignPixels(0, 1, 7, 8, 9);
    this.img.assignPixels(1, 1, 10, 11, 12);
    // pre-modifier check
    ImageTextView view3 = new ImageTextView(this.img, out);
    assertEquals("(1, 2, 3) (4, 5, 6) \n"
            + "(7, 8, 9) (10, 11, 12) ", view3.toString());
    this.img = mod.apply(this.img);
    // post-modifier check
    ImageTextView view4 = new ImageTextView(this.img, out);
    assertNotEquals(view3.toString(), view4.toString());
    assertEquals("(1, 1, 1) (4, 4, 4) \n"
            + "(7, 7, 7) (10, 10, 10) ", view4.toString());
  }

  /**
   * Method to test the sepia transform modifier. Each pixel's RGB values should be changed
   * according to the sepia modifier's formula.
   */
  @Test
  public void testSepia() {
    initModel2x2();
    // pre-modifier check
    ImageTextView view = new ImageTextView(this.img, out);
    assertEquals("(0, 0, 0) (1, 1, 1) \n"
            + "(2, 2, 2) (3, 3, 3) ", view.toString());
    Modifier mod = new SepiaModifier();
    this.img = mod.apply(this.img);
    // post-modifier check
    ImageTextView view2 = new ImageTextView(this.img, out);
    assertEquals("(0, 0, 0) (1, 1, 0) \n"
            + "(2, 2, 1) (4, 3, 2) ", view2.toString());
  }
}