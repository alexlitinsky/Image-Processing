package controller.commands;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import controller.ImageControllerImpl;
import model.ImageModel;
import model.Pixel;
import view.ImageTextView;

/**
 * Class to represent the save command. Running the command takes the current view and writes
 * it to a new PPM file with the specified file name and path.
 */
public class Save implements Command {

  private final String filename;
  private final ImageTextView view;
  private final ImageModel model;


  /**
   * Constructor for the save command. Consumes a controller to access its list of versions, the
   * name of the file to save, and the name and path of the new file created by saving.
   * @param c the controller controlling the command
   * @param filename the path and name to save the file to
   * @param name the name of the PPM file being saved
   */
  public Save(ImageControllerImpl c, String filename, String name) {
    this.filename = filename;
    Appendable app = new StringBuilder();
    this.model = c.getVersions().get(name);
    this.view = new ImageTextView(this.model, app);
  }

  /**
   * This method actually runs the command, and is required of all command objects.
   * The method goes through the path string given to it, and searches for just the name of the file.
   * It then takes this name and writes a new PPM file of the given image
   * with that name in the specified path.
   */
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
      FileWriter ppmWriter = new FileWriter(filename);
      StringBuilder writtenFile = new StringBuilder("P3\n" + this.model.getDimensions()[0] + "\n"
              + this.model.getDimensions()[1] + "\n255\n");
      for (int i = 0; i < this.model.getDimensions()[1]; i++) {
        for (int j = 0; j < this.model.getDimensions()[0]; j++) {
          Pixel cur = this.model.getPixel(j, i);
          writtenFile.append(cur.getRGB()[0]).append(" ")
                  .append(cur.getRGB()[1]).append(" ").append(cur.getRGB()[2]).append(" ");
        }
        writtenFile.append("\n");
      }
      ppmWriter.write(writtenFile.toString());
      ppmWriter.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    System.out.println("It worked??");

  }
}
