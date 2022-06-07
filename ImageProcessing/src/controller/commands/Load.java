package controller.commands;

import controller.ImageControllerImpl;
import model.ImageModel;
import utilities.ImageUtil;
import view.ImageTextView;

/**
 * filler
 */
public class Load implements Command {
  String name;
  String source;
  ImageControllerImpl c;





  // should throw an exception if path name isn't available
  public Load(String source, String name, ImageControllerImpl c) {
    this.name = name;
    this.source = source;
    this.c = c;

  }

  @Override
  public void go()  {
    // look up the
    c.getVersions().put(name, ImageUtil.readPPM(source));


  }
}
