import org.junit.Test;

import java.io.StringReader;
import java.util.NoSuchElementException;

import controller.ImageController;
import controller.ImageControllerImpl;
import model.ImageModelImpl;
import view.ImageTextView;
import view.TextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Class to represent testing for all controller methods and commands.
 * The following commands and their exceptions are tested:
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
 * greyscale
 * sepia
 * sharpen
 * blur
 */
public class ImageControllerImplTest {
  Readable input;
  ImageController controller;

  /**
   * Method to test the constructor for an image controller. Should be initialized with an empty
   * map of versions.
   */
  @Test
  public void testConstructor() {
    // no user input
    this.input = new StringReader("");
    this.controller = new ImageControllerImpl(input);
    controller.playGame();
    assertEquals(0, controller.getVersions().size());
    // EXCEPTIONS
    try {
      this.controller = new ImageControllerImpl(null);
      controller.playGame();
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters cannot be null", e.getMessage());
    }
  }

  /**
   * Method to test the controller's parsing of inputs. Tested using a mock implementation of the
   * controller class which adds parsed arguments to the log a log as it reads inputs.
   */
  @Test
  public void testParsing() {
    this.input = new StringReader("load Images/Koala.ppm koala "
            + "brighten 10 koala koala-brighter-10");
    Appendable log = new StringBuilder();
    this.controller = new ImageControllerImpl.VerifyControllerParsing(input, log);
    controller.playGame();
    assertEquals("loadImages/Koala.ppmkoalabrighten10koalakoala-brighter-10",
            log.toString());
  }

  /**
   * Method to represent testing for the load command. Should read a ppm file and create a new Image
   * from the data in that PPM file and add it to the versions map in the controller.
   * Compared our command's output to the image given in the starter code.
   */
  @Test
  public void testLoad() {
    // valid input
    this.input = new StringReader("load Images/Koala.ppm koala");
    this.controller = new ImageControllerImpl(input);
    controller.playGame();
    assertTrue(controller.getVersions().containsKey("koala"));
    assertEquals(1024, controller.getVersions().get("koala").getDimensions()[0]);
    assertEquals(768, controller.getVersions().get("koala").getDimensions()[1]);
    assertEquals(1, controller.getVersions().size());
    // EXCEPTIONS
    // file does not exist
    try {
      this.input = new StringReader("load Images/Koal.ppm koala");
      this.controller = new ImageControllerImpl(input);
      controller.playGame();
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("java.io.FileNotFoundException: Filename Images/Koal.ppm not found!",
              e.getMessage());
    }
    // wrong file type
    try {
      this.input = new StringReader("load Images/Koala.pp koala");
      this.controller = new ImageControllerImpl(input);
      controller.playGame();
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Wrong file type", e.getMessage());
    }
    // invalid command
    try {
      this.input = new StringReader("loa Images/Koala.ppm koala");
      this.controller = new ImageControllerImpl(input);
      controller.playGame();
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid command.", e.getMessage());
    }
    // test overriding the same key
    this.input = new StringReader("load Images/Koala.ppm koala " +
            "load Images/koala-brighter.ppm koala");
    this.controller = new ImageControllerImpl(input);
    controller.playGame();
    assertEquals(1, controller.getVersions().size());
    assertTrue(controller.getVersions().containsKey("koala"));
  }

  /**
   * Method to test the save command.
   * Should take the specified ImageModelImpl and convert it to a PPM
   * file, and save that PPM under a specified name.
   * Tested by verifying the creation of a new PPM file and then loading it to make sure that
   * no values are changed in the save/load process.
   */
  @Test
  public void testSave() {
    // PPM format
    this.input = new StringReader("load res/Sheep.ppm sheep \n"
            + "save res/saveTest.ppm sheep\n"
            + "load res/saveTest.ppm loadedSaveTest");
    this.controller = new ImageControllerImpl(input);
    controller.playGame();
    TextView sheepView = new ImageTextView(controller.getVersions().get("sheep"),
            new StringBuilder());
    TextView saved = new ImageTextView(controller.getVersions().get("loadedSaveTest"),
            new StringBuilder());
    assertEquals(sheepView.toString(), saved.toString());

    // PNG format
    this.input = new StringReader("load res/Sheep.ppm sheep \n"
            + "save res/saveTest.png sheep \n"
            + "load res/saveTest.png loadedSaveTest");
    this.controller = new ImageControllerImpl(input);
    controller.playGame();
    TextView sheepView2 = new ImageTextView(controller.getVersions().get("sheep"),
            new StringBuilder());
    TextView saved2 = new ImageTextView(controller.getVersions().get("loadedSaveTest"),
            new StringBuilder());
    assertEquals(sheepView2.toString(), saved2.toString());

    // JPEG format
    this.input = new StringReader("load res/Sheep.ppm sheep \n"
            + "save res/saveTest.jpeg sheep \n"
            + "load res/saveTest.jpeg test");
    this.controller = new ImageControllerImpl(input);
    controller.playGame();
    TextView sheepView3 = new ImageTextView(controller.getVersions().get("sheep"),
            new StringBuilder());
    TextView saved3 = new ImageTextView(controller.getVersions().get("test"),
            new StringBuilder());
    assertEquals(sheepView3.toString(), saved3.toString());

    // JPG format
    this.input = new StringReader("load res/Sheep.ppm sheep \n"
            + "save res/saveTest.jpg sheep\n"
            + "load res/saveTest.jpg loadedSaveTest");
    this.controller = new ImageControllerImpl(input);
    controller.playGame();
    TextView sheepView4 = new ImageTextView(controller.getVersions().get("sheep"),
            new StringBuilder());
    TextView saved4 = new ImageTextView(controller.getVersions().get("loadedSaveTest"),
            new StringBuilder());
    assertEquals(sheepView4.toString(), saved4.toString());

    // BMP format
    this.input = new StringReader("load res/Sheep.ppm sheep \n"
            + "save res/saveTest.bmp sheep\n"
            + "load res/saveTest.bmp loadedSaveTest");
    this.controller = new ImageControllerImpl(input);
    controller.playGame();
    TextView sheepView5 = new ImageTextView(controller.getVersions().get("sheep"),
            new StringBuilder());
    TextView saved5 = new ImageTextView(controller.getVersions().get("loadedSaveTest"),
            new StringBuilder());
    assertEquals(sheepView5.toString(), saved5.toString());

    // EXCEPTIONS
    // invalid command
    try {
      this.input = new StringReader("load Images/Koala.ppm koala " +
              "\n brighten 10 koala saveTest \n"
              + "save res/saveTest.ppm \n"
              + "load res/saveTest.ppm loadedSaveTest");
      this.controller = new ImageControllerImpl(input);
      controller.playGame();
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid parameters.", e.getMessage());
    }
  }

  /**
   * Method to test the blue component command.
   * Should set all the RGB values of the image to the blue component value of each pixel.
   * Compared our command's output to the image given in the starter code.
   */
  @Test
  public void testBlueComponent() {
    this.input = new StringReader("load Images/koala-blue-greyscale.png koala-blue \n" +
            "load Images/Koala.ppm koala \n blue-component koala testBlue");
    this.controller = new ImageControllerImpl(input);
    controller.playGame();
    TextView koalaView = new ImageTextView(controller.getVersions().get("koala"),
            new StringBuilder());
    TextView testBlueView = new ImageTextView(controller.getVersions().get("testBlue"),
            new StringBuilder());
    TextView koalaBlueView = new ImageTextView(controller.getVersions().get("koala-blue"),
            new StringBuilder());
    assertNotEquals(koalaView.toString(), testBlueView.toString());
    assertEquals(testBlueView.toString(), koalaBlueView.toString());
  }

  /**
   * Method to test the brighten command.
   * Should set all the RGB values of the image to the sum of the current value and the given value.
   * Compared our command's output to the image given in the starter code.
   */
  @Test
  public void testBrighten() {
    this.input = new StringReader("load Images/koala-brighter-by-50.png koala-brighter \n" +
            "load Images/Koala.ppm koala \n brighten 50 koala testBrighter");
    this.controller = new ImageControllerImpl(input);
    controller.playGame();
    TextView koalaView = new ImageTextView(controller.getVersions().get("koala"),
            new StringBuilder());
    TextView testBrightenView = new ImageTextView(controller.getVersions().get("testBrighter"),
            new StringBuilder());
    TextView brightView = new ImageTextView(controller.getVersions().get("koala-brighter"),
            new StringBuilder());
    assertNotEquals(koalaView.toString(), testBrightenView.toString());
    assertEquals(testBrightenView.toString(), brightView.toString());

    // maxed-out brightness
    this.input = new StringReader("load Images/koala-brighter-by-50.png koala-brighter \n" +
            "load Images/Koala.ppm koala \n brighten 256 koala testBrighter");
    this.controller = new ImageControllerImpl(input);
    controller.playGame();
    for (int i = 0; i < controller.getVersions().get("testBrighter").getDimensions()[0] - 1; i++) {
      for (int j = 0; j <
              controller.getVersions().get("testBrighter").getDimensions()[1] - 1; j++) {
        assertEquals(255,
                controller.getVersions().get("testBrighter").getPixel(i, j).getRGB()[0]);
        assertEquals(255,
                controller.getVersions().get("testBrighter").getPixel(i, j).getRGB()[1]);
        assertEquals(255,
                controller.getVersions().get("testBrighter").getPixel(i, j).getRGB()[2]);
      }
    }

    // min brightness
    this.input = new StringReader("load Images/koala-brighter-by-50.png koala-brighter \n" +
            "load Images/Koala.ppm koala \n brighten -256 koala testBrighter");
    this.controller = new ImageControllerImpl(input);
    controller.playGame();
    for (int i = 0; i < controller.getVersions().get("testBrighter").getDimensions()[0] - 1; i++) {
      for (int j = 0; j <
              controller.getVersions().get("testBrighter").getDimensions()[1] - 1; j++) {
        assertEquals(0,
                controller.getVersions().get("testBrighter").getPixel(i, j).getRGB()[0]);
        assertEquals(0,
                controller.getVersions().get("testBrighter").getPixel(i, j).getRGB()[1]);
        assertEquals(0,
                controller.getVersions().get("testBrighter").getPixel(i, j).getRGB()[2]);
      }
    }
  }

  /**
   * Method to test the flip horizontal command.
   * Should move all the pixels on the left half of the picture to the right & vice versa.
   * Compared our command's output to the image given in the starter code.
   */
  @Test
  public void testFlipHorizontal() {
    this.input = new StringReader("load Images/koala-horizontal.png koala-horizontal \n" +
            "load Images/Koala.ppm koala \n horizontal-flip koala testHorizontal");
    this.controller = new ImageControllerImpl(input);
    controller.playGame();
    TextView koalaView = new ImageTextView(controller.getVersions().get("koala"),
            new StringBuilder());
    TextView testHorizontal = new ImageTextView(controller.getVersions().get("testHorizontal"),
            new StringBuilder());
    TextView horizontal = new ImageTextView(controller.getVersions().get("koala-horizontal"),
            new StringBuilder());
    assertNotEquals(koalaView.toString(), testHorizontal.toString());
    assertEquals(testHorizontal.toString(), horizontal.toString());
  }

  /**
   * Method to test the vertical flip command.
   * Should move all the pixels on the bottom half of the picture to the top & vice versa.
   * Compared our command's output to the image given in the starter code.
   */
  @Test
  public void testFlipVertical() {
    this.input = new StringReader("load Images/koala-vertical.png koala-vertical \n" +
            "load Images/Koala.ppm koala \n vertical-flip koala testVertical");
    this.controller = new ImageControllerImpl(input);
    controller.playGame();
    TextView koalaView = new ImageTextView(controller.getVersions().get("koala"),
            new StringBuilder());
    TextView testVertical = new ImageTextView(controller.getVersions().get("testVertical"),
            new StringBuilder());
    TextView vertical = new ImageTextView(controller.getVersions().get("koala-vertical"),
            new StringBuilder());
    assertNotEquals(koalaView.toString(), testVertical.toString());
    assertEquals(testVertical.toString(), vertical.toString());
  }

  /**
   * Method to test the green component command.
   * Should set all the RGB values of the image to the green component value of each pixel.
   * Compared our command's output to the image given in the starter code.
   */
  @Test
  public void testGreenComponent() {
    this.input = new StringReader("load Images/koala-green-greyscale.png koala-green \n" +
            "load Images/Koala.ppm koala \n green-component koala testGreen");
    this.controller = new ImageControllerImpl(input);
    controller.playGame();
    TextView koalaView = new ImageTextView(controller.getVersions().get("koala"),
            new StringBuilder());
    TextView testView = new ImageTextView(controller.getVersions().get("testGreen"),
            new StringBuilder());
    TextView koalaGreenView = new ImageTextView(controller.getVersions().get("koala-green"),
            new StringBuilder());
    assertNotEquals(koalaView.toString(), testView.toString());
    assertEquals(testView.toString(), koalaGreenView.toString());
  }

  /**
   * Method to test the intensity component command.
   * Should set all the RGB values to the average of the three RGB values of each pixel.
   * Compared our command's output to the image given in the starter code.
   */
  @Test
  public void testIntensityComponent() {
    this.input = new StringReader("load Images/koala-intensity-greyscale.png koala-intensity \n"
            + "load Images/Koala.ppm koala \n intensity-component koala test");
    this.controller = new ImageControllerImpl(input);
    controller.playGame();
    TextView koalaView = new ImageTextView(controller.getVersions().get("koala"),
            new StringBuilder());
    TextView testView = new ImageTextView(controller.getVersions().get("test"),
            new StringBuilder());
    TextView intensityView =
            new ImageTextView(controller.getVersions().get("koala-intensity"), new StringBuilder());
    assertNotEquals(koalaView.toString(), testView.toString());
    assertEquals(testView.toString(), intensityView.toString());
  }

  /**
   * Method to test the luma component command.
   * Should set all the RGB values to the weighted luma sum of each pixel's RGB values.
   * Compared our command's output to the image given in the starter code.
   */
  @Test
  public void testLumaComponent() {
    this.input = new StringReader("load Images/koala-luma-greyscale.png koala-luma \n"
            + "load Images/Koala.ppm koala \n luma-component koala test");
    this.controller = new ImageControllerImpl(input);
    controller.playGame();
    TextView koalaView = new ImageTextView(controller.getVersions().get("koala"),
            new StringBuilder());
    TextView testView = new ImageTextView(controller.getVersions().get("test"),
            new StringBuilder());
    TextView luma =
            new ImageTextView(controller.getVersions().get("koala-luma"), new StringBuilder());
    assertNotEquals(koalaView.toString(), testView.toString());
    assertEquals(testView.toString(), luma.toString());
  }

  /**
   * Method to test the red component command.
   * Should set all the RGB values to the red component of each pixel's RGB values.
   * Compared our command's output to the image given in the starter code.
   */
  @Test
  public void testRedComponent() {
    this.input = new StringReader("load Images/koala-red-greyscale.png koala-red \n"
            + "load Images/Koala.ppm koala \n red-component koala test");
    this.controller = new ImageControllerImpl(input);
    controller.playGame();
    TextView koalaView = new ImageTextView(controller.getVersions().get("koala"),
            new StringBuilder());
    TextView testView = new ImageTextView(controller.getVersions().get("test"),
            new StringBuilder());
    TextView red =
            new ImageTextView(controller.getVersions().get("koala-red"), new StringBuilder());
    assertNotEquals(koalaView.toString(), testView.toString());
    assertEquals(testView.toString(), red.toString());
  }

  /**
   * Method to test the value component command.
   * Should set all the RGB values to the highest component value of each pixel's RGB values.
   * (i.e. a pixel of (1, 2, 3) will become (3, 3, 3))
   * Compared our command's output to the image given in the starter code.
   */
  @Test
  public void testValueComponent() {
    this.input = new StringReader("load Images/koala-value-greyscale.png koala-value \n"
            + "load Images/Koala.ppm koala \n value-component koala test");
    this.controller = new ImageControllerImpl(input);
    controller.playGame();
    TextView koalaView = new ImageTextView(controller.getVersions().get("koala"),
            new StringBuilder());
    TextView testView = new ImageTextView(controller.getVersions().get("test"),
            new StringBuilder());
    TextView value =
            new ImageTextView(controller.getVersions().get("koala-value"), new StringBuilder());
    assertNotEquals(koalaView.toString(), testView.toString());
    assertEquals(testView.toString(), value.toString());
  }

  /**
   * Method to test that getVersions() accurately returns the versions in a controller.
   */
  @Test
  public void testGetVersions() {
    this.input = new StringReader("load Images/koala-value-greyscale.png koala-value");
    this.controller = new ImageControllerImpl(input);
    controller.playGame();
    assertEquals(1, controller.getVersions().size());
    assertTrue(controller.getVersions().containsKey("koala-value"));

    // empty command case
    this.input = new StringReader("");
    this.controller = new ImageControllerImpl(input);
    controller.playGame();
    assertEquals(0, controller.getVersions().size());
    assertTrue(controller.getVersions().isEmpty());

    // invalid command case
    try {
      this.input = new StringReader("asdf");
      this.controller = new ImageControllerImpl(input);
      controller.playGame();
      assertEquals(0, controller.getVersions().size());
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid command.", e.getMessage());
    }
    try {
      this.input = new StringReader("asdf asdf");
      this.controller = new ImageControllerImpl(input);
      controller.playGame();
      assertEquals(0, controller.getVersions().size());
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid command.", e.getMessage());
    }
    try {
      this.input = new StringReader("asdf asdf asdf");
      this.controller = new ImageControllerImpl(input);
      controller.playGame();
      assertEquals(0, controller.getVersions().size());
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid command.", e.getMessage());
    }
    try {
      this.input = new StringReader("load");
      this.controller = new ImageControllerImpl(input);
      controller.playGame();
      fail();
    } catch (NoSuchElementException e) {
      assertEquals(NoSuchElementException.class, e.getClass());
      System.out.println(e.getMessage());
    }
    try {
      this.input = new StringReader("load Images/Koala.ppm");
      this.controller = new ImageControllerImpl(input);
      controller.playGame();
      fail();
    } catch (NoSuchElementException e) {
      assertEquals(NoSuchElementException.class, e.getClass());
      System.out.println(e.getMessage());
    }
  }

  /**
   * Method to test the greyscale command in the imageController. Should properly create a new
   * greyscaled image where all three RGB components of a pixel are equal to each other.
   */
  @Test
  public void testGreyscale() {
    this.input = new StringReader("load Images/nyc.png nyc \n greyscale nyc nyc-greyscaled");
    this.controller = new ImageControllerImpl(input);
    controller.playGame();
    TextView view = new ImageTextView(controller.getVersions().get("nyc"), new StringBuilder());
    TextView test = new ImageTextView(controller.getVersions().get("nyc-greyscaled"),
            new StringBuilder());
    assertNotEquals(view.toString(), test.toString());
    for (int i = 0; i < controller.getVersions().get("nyc-greyscaled").getDimensions()[0]; i++) {
      for (int j = 0; j < controller.getVersions().get("nyc-greyscaled").getDimensions()[1]; j++) {
        int red = controller.getVersions().get("nyc-greyscaled").getPixel(i, j).getRGB()[0];
        int green = controller.getVersions().get("nyc-greyscaled").getPixel(i, j).getRGB()[1];
        int blue = controller.getVersions().get("nyc-greyscaled").getPixel(i, j).getRGB()[2];
        assertTrue(red == green && red == blue && green == blue);
      }
    }
  }

  /**
   * Method to test the sepia command in the imageController. Should properly create a new
   * sepia-filtered image. Due to testing complexity, this test only tests that a new version is
   * created and that its text representation of RGB values is different than the starter image,
   * as the sepia modifier is tested elsewhere.
   */
  @Test
  public void testSepia() {
    this.input = new StringReader("load Images/nyc.png nyc \n sepia nyc nyc-sepia");
    this.controller = new ImageControllerImpl(input);
    controller.playGame();
    assertTrue(controller.getVersions().containsKey("nyc-sepia"));
    assertTrue(controller.getVersions().containsValue(controller.getVersions().get("nyc-sepia")));
    assertEquals(ImageModelImpl.class, controller.getVersions().get("nyc-sepia").getClass());
    TextView view = new ImageTextView(controller.getVersions().get("nyc"), new StringBuilder());
    TextView test = new ImageTextView(controller.getVersions().get("nyc-sepia"),
            new StringBuilder());
    assertNotEquals(view.toString(), test.toString());
  }

  /**
   * Method to test the sharpen command in the imageController. Should properly create a new
   * sharpened image. Due to testing complexity, this test only tests that a new version is
   * created and that its text representation of RGB values is different than the starter image,
   * as the sharpen modifier is tested in the modifier tests.
   */
  @Test
  public void testSharpen() {
    this.input = new StringReader("load Images/nyc.png nyc \n sharpen nyc nyc-sharpened");
    this.controller = new ImageControllerImpl(input);
    controller.playGame();
    assertTrue(controller.getVersions().containsKey("nyc-sharpened"));
    assertTrue(controller.getVersions().containsValue(controller.getVersions().get("nyc-sharpened")));
    assertEquals(ImageModelImpl.class, controller.getVersions().get("nyc-sharpened").getClass());
    TextView view = new ImageTextView(controller.getVersions().get("nyc"), new StringBuilder());
    TextView test = new ImageTextView(controller.getVersions().get("nyc-sharpened"),
            new StringBuilder());
    assertNotEquals(view.toString(), test.toString());
  }

  /**
   * Method to test the blur command in the imageController. Should properly create a new
   * blurred image. Due to testing complexity, this test only tests that a new version is
   * created and that its text representation of RGB values is different than the starter image,
   * as the blur modifier is tested in the modifier tests.
   */
  @Test
  public void testBlur() {
    this.input = new StringReader("load Images/nyc.png nyc \n blur nyc nyc-blurred");
    this.controller = new ImageControllerImpl(input);
    controller.playGame();
    assertTrue(controller.getVersions().containsKey("nyc-blurred"));
    assertTrue(controller.getVersions().containsValue(controller.getVersions().get("nyc-blurred")));
    assertEquals(ImageModelImpl.class, controller.getVersions().get("nyc-blurred").getClass());
    TextView view = new ImageTextView(controller.getVersions().get("nyc"), new StringBuilder());
    TextView test = new ImageTextView(controller.getVersions().get("nyc-blurred"),
            new StringBuilder());
    assertNotEquals(view.toString(), test.toString());
  }
}
