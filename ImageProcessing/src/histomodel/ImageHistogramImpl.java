package histomodel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.*;

import model.Image;
import model.Pixel;
import view.BarChartGraphic;

/**
 * Represents the implementation for an ImageHistogram
 */
public class ImageHistogramImpl implements ImageHistogram {
  private Map<Integer, Integer> imageDataR;
  private Map<Integer, Integer> imageDataG;
  private Map<Integer, Integer> imageDataB;
 private Map<Integer, Integer> imageDataI;

  /**
   * A Constructor for an ImageHistogram.
   */
  public ImageHistogramImpl() {
    this.imageDataR = new TreeMap<Integer, Integer>();
    this.imageDataB = new TreeMap<Integer, Integer>();
    this.imageDataG = new TreeMap<Integer, Integer>();
    this.imageDataI = new TreeMap<Integer, Integer>();
  }

  @Override
  public void scale(int s) {
    Map<Integer, Integer> newDataR = new TreeMap<Integer, Integer>();
    Map<Integer, Integer> newDataG = new TreeMap<Integer, Integer>();
    Map<Integer, Integer> newDataB = new TreeMap<Integer, Integer>();
    Map<Integer, Integer> newDataI = new TreeMap<Integer, Integer>();
    for (int i = 0; i < imageDataR.size(); i++) {
      newDataR.put(i, Math.min(Math.max(0, imageDataR.get(i) * s), 255));
      newDataG.put(i, Math.min(Math.max(0, imageDataG.get(i) * s), 255));
      newDataB.put(i, Math.min(Math.max(0, imageDataB.get(i) * s), 255));
      newDataI.put(i, Math.min(Math.max(0, imageDataI.get(i) * s), 255));
    }

    this.imageDataR = newDataR;
    this.imageDataG = newDataG;
    this.imageDataB = newDataB;
    this.imageDataI = newDataI;
  }

  @Override
  public void update(Image model) {
    this.imageDataR = new TreeMap<Integer, Integer>();
    this.imageDataB = new TreeMap<Integer, Integer>();
    this.imageDataG = new TreeMap<Integer, Integer>();
    this.imageDataI = new TreeMap<Integer, Integer>();

    for (int i = 0; i < model.getDimensions()[0]; i++) {
      for (int j = 0; j < model.getDimensions()[1]; j++) {
        Pixel cur = model.getPixel(i, j);
        if (imageDataR.containsKey(cur.getRGB()[0])) { imageDataR.put(cur.getRGB()[0],
                imageDataR.get(cur.getRGB()[0]) + 1); }
        else { imageDataR.put(cur.getRGB()[0], 0); }
        if (imageDataG.containsKey(cur.getRGB()[1])) { imageDataG.put(cur.getRGB()[1],
                imageDataG.get(cur.getRGB()[1]) + 1); }
        else { imageDataG.put(cur.getRGB()[1], 0); }
        if (imageDataB.containsKey(cur.getRGB()[2])) { imageDataB.put(cur.getRGB()[2],
                imageDataB.get(cur.getRGB()[2]) + 1); }
        else { imageDataB.put(cur.getRGB()[2], 0); }
        int intensity = (cur.getRGB()[0] + cur.getRGB()[1] + cur.getRGB()[2]) / 3;
        if (imageDataI.containsKey(intensity)) { imageDataI.put(intensity,
                imageDataI.get(intensity) + 1); }
        else { imageDataI.put(intensity, 0); }
      }
    }
  }

  @Override
  public ArrayList<Map<Integer, Integer>> getHistogramData() {
    ArrayList<Map<Integer, Integer>> data = new ArrayList<>();
    data.add(imageDataR);
    data.add(imageDataG);
    data.add(imageDataB);
    data.add(imageDataI);
    return data;
  }

  /**
   * Creates an image of the bar chart representing this histogram to be displayed by other methods.
   *
   * @return the image created to represent this histogram
   */
  @Override
  public java.awt.Image createHistogram() {
    BarChartGraphic chart = new BarChartGraphic();
    chart.addBar(Color.red, imageDataR.size());
    chart.addBar(Color.green, imageDataG.size());
    chart.addBar(Color.blue, imageDataB.size());
    chart.addBar(Color.black, imageDataI.size());
    BufferedImage image = new BufferedImage(chart.getPreferredSize().width,
            chart.getPreferredSize().height,
            BufferedImage.TYPE_INT_RGB);
    Graphics graphics = image.createGraphics();
    chart.paint(graphics);
    graphics.drawImage(image, image.getWidth(new JLabel()), image.getHeight(new JLabel()),
            new JLabel());
    graphics.dispose();
    return image;
  }


}
