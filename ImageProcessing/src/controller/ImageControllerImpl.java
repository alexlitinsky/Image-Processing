package controller;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.BlueComp;
import controller.commands.Brighten;
import controller.commands.Command;
import controller.commands.FlipHorizontal;
import controller.commands.FlipVertical;
import controller.commands.GreenComp;
import controller.commands.IntensityComp;
import controller.commands.Load;
import controller.commands.LumaComp;
import controller.commands.RedComp;
import controller.commands.Save;
import controller.commands.ValueComp;
import model.ImageModelImpl;

/**
 * Class to represent the controller for an image. Takes in a readable input. Has a set list of
 * commands to occupy the controller. Uses a hashmap to keep track all the versions of the models.
 */
public class ImageControllerImpl {
  private final Readable input;
  private final Map<String, Function<Scanner, Command>> allCommands;
  private Map<String, ImageModelImpl> versions;

  /**
   * Constructor for this implementation of an image controller.
   *
   * @param input the input being given to the controller
   * @throws IllegalArgumentException if one of the parameters passed to it is null
   */
  public ImageControllerImpl(Readable input)
          throws IllegalArgumentException {
    if (input == null) {
      throw new IllegalArgumentException("Parameters cannot be null");
    }
    this.input = input;
    this.versions = new HashMap<>();
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
  }

  /**
   * The method to start the game. Scans the user's inputs for commands to control the game,
   * as well as stores each new version of the image created from running commands.
   *
   * @throws IllegalStateException if the game cannot be played properly
   */
  public void playGame() throws IllegalStateException {
    Scanner scanner = new Scanner(input);
    int argCount = 0;
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
  public Map<String, ImageModelImpl> getVersions() {
    return this.versions;
  }

}

