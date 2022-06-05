import org.junit.Test;

import model.ImageModel;
import view.ImageRBGTextView;
import view.TextView;

import static org.junit.Assert.*;

public class ImageRBGTextViewTest {

  @Test
  public void testToString() {
    ImageModel img = new ImageModel(1, 1);
    img.assignPixels(0, 0, 1, 1, 1);
    Appendable out = new StringBuilder();
    TextView view = new ImageRBGTextView(img, out);
    assertEquals("(1, 1, 1) ", view.toString());
  }
}