package controller.commands;

import java.io.FileNotFoundException;

import controller.ImageControllerImpl;
import utilities.ImageUtil;
import view.ImageTextView;

/**
 * Class to represent the command to load an image from a given file.
 * This command instantiates an ImageUtil
 * and calls readPPM() to read the specified PPM file, which returns a new Image
 * object representing that PPM file.
 */
public class Load implements Command {
  String newName;
  String source;
  ImageControllerImpl c;

  /**
   * Constructor for the load command. Consumes a source, new name and controller.
   *
   * @param source the filepath for the source file
   * @param newName the new name for the version
   * @param c the current controller
   * @throws IllegalArgumentException if any of the arguments are null
   */
  public Load(String source, String newName, ImageControllerImpl c)
          throws IllegalArgumentException {
    if (source == null || newName == null || c == null) {
      throw new IllegalArgumentException("Invalid parameters");
    }
    this.newName = newName;
    this.source = source;
    this.c = c;
  }

  /**
   * Method to apply this command. Gets the map of versions from the controller, reads the source
   * file generating an image, and puts it in the map of versions under the new name.
   */
  @Override
  public void commandApply() {
    try {
      if (c.getVersions().containsKey(newName)) {
        c.getVersions().replace(newName, ImageUtil.readPPM(source));
      }
      c.getVersions().put(newName, ImageUtil.readPPM(source));
      ImageTextView view = new ImageTextView(ImageUtil.readPPM(source), new StringBuilder());
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException(e);
    }
  }
}
