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
  void update(Image model);

  ArrayList<Map<Integer, Integer>> getHistogramData();

}
