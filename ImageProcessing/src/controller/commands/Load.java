package controller.commands;

import controller.ImageControllerImpl;
import utilities.ImageUtil;

/**
 * filler
 */
public class Load implements Command {
  String newName;
  String source;
  ImageControllerImpl c;

  // should throw an exception if path name isn't available
  public Load(String source, String newName, ImageControllerImpl c) {
    this.newName = newName;
    this.source = source;
    this.c = c;
  }

  /**
   * Method to apply this command. Gets the map of versions from the controller, reads the source
   * file generating an image, and puts it in the map of versions under the new name.
   */
  @Override
  public void go() {
    c.getVersions().put(newName, ImageUtil.readPPM(source));
  }

}
