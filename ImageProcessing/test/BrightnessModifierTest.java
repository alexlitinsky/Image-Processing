import org.junit.Test;

import model.ImageModel;
import model.modifiers.BrightnessModifier;
import model.modifiers.Modifier;

import static org.junit.Assert.*;

public class BrightnessModifierTest {

  @Test
  public void testApply() {
    ImageModel img = new ImageModel("test", 1, 1);
    img.assignPixels(0, 0, 1, 1, 1);
    // check the brightness of the pixel in the model before applying the filter
    assertEquals(1, img.getPixel(0,0).getRBG()[0]);
    assertEquals(1, img.getPixel(0,0).getRBG()[1]);
    assertEquals(1, img.getPixel(0,0).getRBG()[2]);

    // instantiate & apply modifier
    Modifier mod = new BrightnessModifier("new test", img, 10);
    mod.apply();
    assertEquals( "new test",img.getVersion("new test").getName());
    assertEquals( 11, img.getVersion("new test").getPixel(0, 0).getRBG()[0]);
    assertEquals( 11, img.getVersion("new test").getPixel(0, 0).getRBG()[1]);
    assertEquals( 11, img.getVersion("new test").getPixel(0, 0).getRBG()[2]);
  }
}