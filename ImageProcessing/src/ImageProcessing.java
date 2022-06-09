import java.io.StringReader;
import java.util.Arrays;

import controller.ImageControllerImpl;

public class ImageProcessing {
  public static void main(String[] args) {

    String commands = Arrays.toString(args);
    Readable input = new StringReader(commands);
    ImageControllerImpl controller = new ImageControllerImpl(input);
    controller.playGame();
  }
}
