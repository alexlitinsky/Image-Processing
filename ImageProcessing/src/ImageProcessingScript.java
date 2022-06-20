import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.Scanner;

import controller.ImageControllerImpl;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;

/**
 * Represents the  Image Processing class for a script. Contains a method to run the game. Edit
 * game inputs by editing run configuration.
 */
public class ImageProcessingScript {
  /**
   * Initializes the program to start initializing images with a script.
   *
   * @param args the input (text file) and their associated commands.
   */
  public static void runScript(String[] args) throws FileNotFoundException {
    Scanner sc;
    String fileType = "";
    for (int i = 0; i < args[1].length(); i++) {
      if (args[1].charAt(i) == '.') {
        fileType = args[1].substring(i + 1);
      }
    }
    if (!fileType.equals("txt")) {
      throw new IllegalArgumentException("If entering a script, it must be a .txt file");
    }

    try {
      sc = new Scanner(new FileInputStream(args[1]));
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("Filename " + args[1] + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (!s.equals("") && s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    String commands = builder.toString();

    ImageProcessingModel model = new ImageProcessingModelImpl();
    Readable input = new StringReader(commands);
    ImageControllerImpl controller = new ImageControllerImpl(input, model);
    controller.playGame();
  }
}
