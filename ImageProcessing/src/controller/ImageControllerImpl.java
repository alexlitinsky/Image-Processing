package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.BlueComp;
import controller.commands.Blur;
import controller.commands.Brighten;
import controller.commands.Command;
import controller.commands.FlipHorizontal;
import controller.commands.FlipVertical;
import controller.commands.GreenComp;
import controller.commands.Greyscale;
import controller.commands.IntensityComp;
import controller.commands.Load;
import controller.commands.LumaComp;
import controller.commands.RedComp;
import controller.commands.Save;
import controller.commands.Sepia;
import controller.commands.Sharpen;
import controller.commands.ValueComp;
import model.Image;
import model.ImageProcessingModel;

/**
 * Class to represent the controller for an image. Takes in a readable input. Has a set list of
 * commands to occupy the controller. Uses a hashmap to keep track all the versions of the models.
 */
public class ImageControllerImpl implements ImageController {
  private final Readable input;
  private final Map<String, Function<Scanner, Command>> allCommands;
  private ImageProcessingModel model;

  /**
   * Constructor for this implementation of an image controller.
   *
   * @param input the input being given to the controller
   * @param model the model being manipulated by the controller
   * @throws IllegalArgumentException if one of the parameters passed to it is null
   */
  public ImageControllerImpl(Readable input, ImageProcessingModel model)
          throws IllegalArgumentException {
    if (input == null || model == null) {
      throw new IllegalArgumentException("Parameters cannot be null");
    }
    this.input = input;
    this.model = model;
    allCommands = new HashMap<>();
    allCommands.put("load", s -> new Load(s.next(), s.next(), this));
    allCommands.put("save", s -> new Save(this, s.next(), s.next()));
    allCommands.put("brighten", s -> new Brighten(this, s.next(), s.next(), s.next()));
    allCommands.put("vertical-flip", s -> new FlipVertical(this, s.next(), s.next()));
    allCommands.put("horizontal-flip", s -> new FlipHorizontal(this, s.next(), s.next()));
    allCommands.put("value-component", s -> new ValueComp(this, s.next(), s.next()));
    allCommands.put("red-component", s -> new RedComp(this, s.next(), s.next()));
    allCommands.put("green-component", s -> new GreenComp(this, s.next(), s.next()));
    allCommands.put("blue-component", s -> new BlueComp(this, s.next(), s.next()));
    allCommands.put("luma-component", s -> new LumaComp(this, s.next(), s.next()));
    allCommands.put("intensity-component", s -> new IntensityComp(this, s.next(), s.next()));
    allCommands.put("greyscale", s -> new Greyscale(this, s.next(), s.next()));
    allCommands.put("sepia", s -> new Sepia(this, s.next(), s.next()));
    allCommands.put("sharpen", s -> new Sharpen(this, s.next(), s.next()));
    allCommands.put("blur", s -> new Blur(this, s.next(), s.next()));

  }

  /**
   * The method to start the game. Scans the user's inputs for commands to control the game,
   * as well as stores each new version of the image created from running commands.
   *
   * @throws IllegalStateException if the game cannot be played properly
   */
  @Override
  public void playGame() throws IllegalStateException {
    Scanner scanner = new Scanner(input);
    while (scanner.hasNext()) {
      Command c;
      String in = scanner.next();
      Function<Scanner, Command> cmd = allCommands.getOrDefault(in, null);
      if (cmd == null) {
        throw new IllegalArgumentException("Invalid command.");
      } else {
        c = cmd.apply(scanner);
        try {
          c.commandApply();
        } catch (FileNotFoundException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  /**
   * Retrieves the versions of the current models.
   *
   * @return the versions of the models
   */
  @Override
  public Map<String, Image> getVersions() {
    return this.model.getImages();
  }

  /**
   * Mock controller implementation. Exists to verify parsing of inputs passed to the controller.
   * Contains a log to record what the controller parses in the playGame() method, does not do
   * anything for getVersions() class as there will be no versions to get/be created since
   * playgame() only records parsed inputs for this controller implementation.
   */
  public static class VerifyControllerParsing implements ImageController {
    private final Appendable log;
    private final Readable input;

    /**
     * Constructor for this mock controller class. Verifies the parsing of inputs by the controller.
     *
     * @param log the appendable recording what the controller parses
     */
    public VerifyControllerParsing(Readable input, Appendable log) {
      this.input = input;
      this.log = Objects.requireNonNull(log);
    }

    /**
     * Parses the input passed to the controller and adds them to the log.
     *
     * @throws IllegalStateException if the controller breaks down
     */
    @Override
    public void playGame() throws IllegalStateException {
      Scanner scanner = new Scanner(input);
      while (scanner.hasNext()) {
        String in = scanner.next();
        try {
          log.append(in);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    }

    /**
     * Gets the versions of images of a specific controller. We don't care about this for parsing
     * checks.
     *
     * @return null as we don't care about the versions of this controller type
     */
    @Override
    public Map<String, Image> getVersions() {
      return null;
    }
  }

}

