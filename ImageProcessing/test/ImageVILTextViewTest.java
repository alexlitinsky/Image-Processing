import org.junit.Test;
import org.w3c.dom.Text;

import model.ImageModel;
import view.ImageRBGTextView;
import view.ImageVILTextView;
import view.TextView;

import static org.junit.Assert.*;

public class ImageVILTextViewTest {

  /**
   * Method to test the toString method for the ImageVILTextView class. The string output will
   * depend on the type of view requested: value, intensity or luma.
   */
  @Test
  public void testToString() {
    ImageModel img = new ImageModel(1, 1);
    img.assignPixels(0, 0, 2, 3, 4);
    Appendable out = new StringBuilder();

    // testing the output of the value view
    TextView value = new ImageVILTextView(img, out, ImageVILTextView.VILType.Value);
    assertEquals("(256, 256, 256) ", value.toString());

    // testing the output of the intensity view
    TextView intensity = new ImageVILTextView(img, out, ImageVILTextView.VILType.Intensity);
    assertEquals("9", intensity.toString());

    // testing the output of the luma view
    TextView luma = new ImageVILTextView(img, out, ImageVILTextView.VILType.Luma);
    assertEquals("2.8596000000000004", luma.toString());
  }
}