import java.io.FileNotFoundException;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import controller.GUIControllerImpl;
import controller.ImageController;
import histomodel.ImageHistogram;
import histomodel.ImageHistogramImpl;
import model.Image;
import model.ImageImpl;
import view.GraphicalView;
import view.GraphicalViewImpl;

/**
 * Represents the main class for a GUI-based image processing application
 */
public class GraphicalImageProcessing {
  /**
   * The main method
   * @param args the string, either a script or interactive
   */
  public static void main(String [] args) {

    if (args[0].equals("script")) {
      try {
        ImageProcessingScript.runScript(args);
      } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
      }
    } else if (args[0].equals("interactive")) {
      try {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        ImageHistogram histo = new ImageHistogramImpl();
        Image model = new ImageImpl(0, 0);
        GraphicalView gView = new GraphicalViewImpl("GRIME", histo, model);
        ImageController gController = new GUIControllerImpl(gView, histo);
        gController.playGame();

      } catch (UnsupportedLookAndFeelException e) {
        throw new RuntimeException(e);
      }

    } else {
      throw new IllegalArgumentException("Invalid arguments.");
    }



  }
}
