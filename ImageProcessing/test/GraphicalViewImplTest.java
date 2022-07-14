import org.junit.Test;

import histomodel.ImageHistogram;
import histomodel.ImageHistogramImpl;
import model.ImageImpl;
import view.GraphicalView;
import view.GraphicalViewImpl;

import static org.junit.Assert.*;

/**
 * Class to represent testing for the graphical view implementation.
 */
public class GraphicalViewImplTest {
  GraphicalView test;

  /**
   * Private method to initialize a testing GUI view.
   */
  private void init1() {
    test = new GraphicalViewImpl("test", new ImageHistogramImpl(),
            new ImageImpl(1, 1));
  }

  /**
   * Method to test the graphical view implementation constructor.
   */
  @Test
  public void testConstructor() {
    init1();
    assertFalse(test == null);
  }

  /**
   * Method to test the setHistogram() method.
   */
  @Test
  public void setHistogram() {
    init1();
    ImageHistogram histo = new ImageHistogramImpl();
    GraphicalView test2 = new GraphicalViewImpl("test", new ImageHistogramImpl(),
            new ImageImpl(1, 1));
    test2.updateHistogram(histo);
    assertFalse(test.equals(test2));

  }
}