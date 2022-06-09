import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.Arrays;

import controller.ImageControllerImpl;

/**
 * Represents the main Image Processing class
 */
public class ImageProcessing {
  /**
   * Initializes the program to start initializing images.
   * @param args the input and their associated commands.
   */
  public static void main(String[] args) throws FileNotFoundException {

    String commands = Arrays.toString(args);
    Readable input = new StringReader(commands);
    ImageControllerImpl controller = new ImageControllerImpl(input);
    controller.playGame();
  }
}
