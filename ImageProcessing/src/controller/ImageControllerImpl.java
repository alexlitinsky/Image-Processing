package controller;

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
import model.ImageModel;
import view.TextView;

/**
 * Class to represent the controller for an image.
 */
public class ImageControllerImpl {
  private final Readable input;
  private Map versions;

  private final Map<String, Function<Scanner, Command>> allCommands;

  /**
   * Constructor for this implementation of an image controller.
   *
   * @param input the input being given to the controller
   * @throws IllegalArgumentException
   */
  public ImageControllerImpl(Readable input)
          throws IllegalArgumentException {
    if (input == null) {
      throw new IllegalArgumentException("Parameters cannot be null");
    }
    this.input = input;
    this.versions = new HashMap<String, ImageModel>();
    allCommands = new HashMap<>();
    allCommands.put("load", s -> new Load(s.next(), s.next(), this));
//    allCommands.put("save", new Save());
//    allCommands.put("brighten", s -> new Brighten(s.nextInt()));
//    allCommands.put("flip-vertical", new FlipVertical());
//    allCommands.put("flip-horizontal", new FlipHorizontal());
//    allCommands.put("value-component", new ValueComp());
//    allCommands.put("red-component", new RedComp());
//    allCommands.put("green-component", new GreenComp());
//    allCommands.put("blue-component", new BlueComp());
//    allCommands.put("luma-component", new LumaComp());
//    allCommands.put("intensity-component", new IntensityComp());

  }

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
        c.go();
      }
    }
  }

  public Map getVersions() {
    return this.versions;
  }


  // when a filter is applied
  // make filter object
  // apply it, and add the returned model to versions:
  // versions.put(new name, new model)
}

