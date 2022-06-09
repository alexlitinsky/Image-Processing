import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import controller.ImageControllerImpl;
import view.ImageTextView;

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
    assertEquals(1024, controller.getVersions().get("koala").getDimensions()[0]);
    assertEquals(768, controller.getVersions().get("koala").getDimensions()[1]);
    assertEquals(1, controller.getVersions().size());
    assertTrue(controller.getVersions().containsKey("koala"));
  }

  /**
   * Method to test the blue component command.
   * Should set all the RGB values of the image to the blue component value of each pixel.
   */
  @Test
  public void testBlueComponent() {
    this.input = new StringReader("load Images/koala-blue-greyscale.png koala-blue \n" +
            "load Images/Koala.ppm koala \n blue-component koala testBlue");
    this.controller = new ImageControllerImpl(input);
    controller.playGame();
    ImageTextView koalaView = new ImageTextView(controller.getVersions().get("koala"),
            new StringBuilder());
    ImageTextView testBlueView = new ImageTextView(controller.getVersions().get("testBlue"),
            new StringBuilder());
    ImageTextView koalaBlueView = new ImageTextView(controller.getVersions().get("koala-blue"),
            new StringBuilder());
    assertNotEquals(koalaView.toString(), testBlueView.toString());
    assertEquals(testBlueView.toString(), koalaBlueView.toString());
  }

  /**
   * Method to test the brighten command.
   * Should set all the RGB values of the image to the sum of the current value and the given value.
   */
  @Test
  public void testBrighten() {
    this.input = new StringReader("load Images/koala-brighter-by-50.png koala-brighter \n" +
            "load Images/Koala.ppm koala \n brighten 50 koala testBrighter");
    this.controller = new ImageControllerImpl(input);
    controller.playGame();
    ImageTextView koalaView = new ImageTextView(controller.getVersions().get("koala"),
            new StringBuilder());
    ImageTextView testBrightenView = new ImageTextView(controller.getVersions().get("testBrighter"),
            new StringBuilder());
    ImageTextView brightView = new ImageTextView(controller.getVersions().get("koala-brighter"),
            new StringBuilder());
    assertNotEquals(koalaView.toString(), testBrightenView.toString());
    assertEquals(testBrightenView.toString(), brightView.toString());
  }

  /**
   * Method to test the flip horizontal command.
   * Should move all the pixels on the left half of the picture to the right & vice versa.
   */
  @Test
  public void testFlipHorizontal() {
    this.input = new StringReader("load Images/koala-horizontal.png koala-horizontal \n" +
            "load Images/Koala.ppm koala \n horizontal-flip koala testHorizontal");
    this.controller = new ImageControllerImpl(input);
    controller.playGame();
    ImageTextView koalaView = new ImageTextView(controller.getVersions().get("koala"),
            new StringBuilder());
    ImageTextView testHorizontal = new ImageTextView(controller.getVersions().get("testHorizontal"),
            new StringBuilder());
    ImageTextView horizontal = new ImageTextView(controller.getVersions().get("koala-horizontal"),
            new StringBuilder());
    assertNotEquals(koalaView.toString(), testHorizontal.toString());
    assertEquals(testHorizontal.toString(), horizontal.toString());
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


  @Rule
  public TemporaryFolder temp = new TemporaryFolder();

  @Test
  public void testSave() {
    this.input = new StringReader("load Images/Koala.ppm koala " +
            "\n brighten 50 koala testBlue \n" +
            "save Images/koalaBlue.ppm testBlue");
    this.controller = new ImageControllerImpl(input);
    controller.playGame();


  }
}