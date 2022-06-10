import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.Scanner;

import controller.ImageControllerImpl;

/**
 * Represents the main Image Processing class. Contains the main method to run the game. Edit
 * game inputs by editing run configuration.
 */
public class ImageProcessing {
  /**
   * Initializes the program to start initializing images.
   *
   * @param args the input and their associated commands.
   */
  public static void main(String[] args) throws FileNotFoundException {
    Scanner sc;
    String fileType = "";
    for (int i = 0; i < args[0].length(); i++) {
      if (args[0].charAt(i) == '.') {
        fileType = args[0].substring(i + 1);
      }
    }
    if (!fileType.equals("txt")) {
      throw new IllegalArgumentException("If entering a script, it must be a .txt file");
    }


    try {
      sc = new Scanner(new FileInputStream(args[0]));
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("Filename " + args[0] + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (!s.equals("") && s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    String commands = builder.toString();

    Readable input = new StringReader(commands);
    ImageControllerImpl controller = new ImageControllerImpl(input);
    controller.playGame();
  }
}
