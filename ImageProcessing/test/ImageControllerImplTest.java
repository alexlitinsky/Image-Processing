import org.junit.Test;

import java.io.StringReader;

import controller.ImageControllerImpl;

import static org.junit.Assert.*;

/**
 * Class to represent testing for all controller methods and commands. The following commands are tested:
 * blue component
 * brighten
 * flip horizontal
 * flip vertical
 * green component
 * intensity component
 * load
 * luma component
 * red component
 * save
 * value component
 */
public class ImageControllerImplTest {
  Readable input;
  ImageControllerImpl controller;

  /**
   * Class to represent testing for the load command. Should read a ppm file and create a new Image
   * from the data in that PPM file and add it to the versions map in the controller.
   */
  @Test
  public void testLoad() {
    this.input = new StringReader("load Images/Koala.ppm koala");
    this.controller = new ImageControllerImpl(input);
    controller.playGame();
    assertEquals(768, controller.getVersions().get("koala").getDimensions()[0]);
    assertEquals(1024, controller.getVersions().get("koala").getDimensions()[1]);
    assertEquals(1, controller.getVersions().size());
    assertTrue(controller.getVersions().containsKey("koala"));
  }

  /**
   * Method to test the blue component command.
   * Should set all the RGB values of the image to the blue component value of each pixel.
   */
  @Test
  public void testBlueComponent() {
    this.input = new StringReader("load Images/Koala.ppm koala");
    this.controller = new ImageControllerImpl(input);
    controller.playGame();
    this.input = new StringReader("blue-component koala koala-blue");
    controller.playGame();
    fail();
  }

  /**
   * Method to test that playGame() works as expected and correctly runs the game.
   */
  @Test
  public void testPlayGame() {

  }

  /**
   * Method to test that getVersions() accurately returns the versions in a controller.
   */
  @Test
  public void testGetVersions() {

  }
}