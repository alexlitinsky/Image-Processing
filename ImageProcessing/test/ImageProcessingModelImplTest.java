import org.junit.Test;

import java.util.HashMap;

import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Class to represent testing for the image processing model.
 */
public class ImageProcessingModelImplTest {
  private final ImageProcessingModel model = new ImageProcessingModelImpl();

  /**
   * Method to test the constructor for this model. Verifies that the model has been properly
   * initialized. Should be empty on construction.
   */
  @Test
  public void testConstructor() {
    assertTrue(model.getImages().isEmpty());
  }

  /**
   * Method to test that getImages is correctly retrieving the map of images in the model.
   * Should return an empty HashMap.
   */
  @Test
  public void getImages() {
    assertEquals(HashMap.class, model.getImages().getClass());
    assertTrue(model.getImages().isEmpty());
  }
}