package controller.commands;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import controller.ImageControllerImpl;
import view.ImageTextView;

public class Save implements Command {

  private final String filename;
  private final ImageTextView view;


  public Save(ImageControllerImpl c, String filename, String name) {
    this.filename = filename;
    Appendable app = new StringBuilder();
    this.view = new ImageTextView(c.getVersions().get(name), app);

  }


  @Override
  public void go() {
    String justName = "";
    for (int i = 0; i < filename.length(); i++) {
      if (Character.compare(filename.charAt(i), '/') == 0) {
        justName = filename.substring(i + 1);
      }
    }
    try {
      File ppm = new File(filename);
      FileWriter ppmWriter = new FileWriter(justName);
      ppmWriter.write(this.view.toString());
      ppmWriter.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    System.out.println("It worked??");

  }
}
