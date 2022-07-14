package view;

import java.awt.event.ActionListener;
import java.util.List;

import histomodel.ImageHistogram;
import model.Image;

public interface GraphicalView {
  /**
   * Method to display this graphics view.
   */
  void display();

  /**
   * Method to refresh the view to a newer state with newer information.
   */
  void refresh();

  /**
   * Prints a given message to the user.
   *
   * @param msg the message to be printed
   */
  void alert(String msg);

  /**
   * Sets the histogram for an image.
   *
   * @param histo the histogram to be displayed
   */
  void updateHistogram(ImageHistogram histo);

  /**
   * Acts as a liasion between the view and dialog outputs of various methods.
   *
   * @param dialog the dialog to be monitored (based on which command is executed)
   * @return the output of each command as a list of strings
   */
  List<String> dialogHandler(String dialog);

  /**
   * Sets the action listener for the view. Necessary for proper GUI click functionality.
   *
   * @param listener the action listener
   */
  void setListener(ActionListener listener);

  /**
   * Updates the view model to the image model currently being manipulated by the user.
   *
   * @param newModel the new image model
   */
  void updateModel(Image newModel);
}
