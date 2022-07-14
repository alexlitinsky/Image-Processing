package histomodel;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import model.Image;

/**
 * An interface for an ImageHistogram.
 */
public interface ImageHistogram {

  /**
   * Scales a histogram by a certain value.
   * @param s the value being scaled
   */
  void scale(int s);

  /**
   * Updates the image histogram with the updated model.
   * @param model the model being used to update the histogram
   */
  ImageHistogram update(Image model);

  ArrayList<Map<Integer, Integer>> getHistogramData();

  /**
   * Creates an image of the bar chart representing this histogram to be displayed by other methods.
   * @return the image created to represent this histogram
   */
  java.awt.Image createHistogram();
}
