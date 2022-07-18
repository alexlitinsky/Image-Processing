package histomodel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


import javax.swing.*;

import model.Image;
import model.Pixel;
import view.BarChartGraphic;

/**
 * Represents the implementation for an ImageHistogram
 */
public class ImageHistogramImpl implements ImageHistogram {
  private int[] betterDataR;
  private int[] betterDataG;
  private int[] betterDataB;
  private int[] betterDataI;



  /**
   * A Constructor for an ImageHistogram.
   */
  public ImageHistogramImpl() {

    this.betterDataR = new int[256];
    this.betterDataG = new int[256];
    this.betterDataB = new int[256];
    this.betterDataI = new int[256];
  }

  @Override
  public void scale(int s) {
//    Map<Integer, Integer> newDataR = new TreeMap<Integer, Integer>();
//    Map<Integer, Integer> newDataG = new TreeMap<Integer, Integer>();
//    Map<Integer, Integer> newDataB = new TreeMap<Integer, Integer>();
//    Map<Integer, Integer> newDataI = new TreeMap<Integer, Integer>();
//    for (int i = 0; i < imageDataR.size(); i++) {
//      newDataR.put(i, Math.min(Math.max(0, imageDataR.get(i) * s), 255));
//      newDataG.put(i, Math.min(Math.max(0, imageDataG.get(i) * s), 255));
//      newDataB.put(i, Math.min(Math.max(0, imageDataB.get(i) * s), 255));
//      newDataI.put(i, Math.min(Math.max(0, imageDataI.get(i) * s), 255));
//    }
//
//    this.imageDataR = newDataR;
//    this.imageDataG = newDataG;
//    this.imageDataB = newDataB;
//    this.imageDataI = newDataI;
  }

  @Override
  public ImageHistogram update(Image model) {

    this.betterDataR = new int[256];
    this.betterDataG = new int[256];
    this.betterDataB = new int[256];
    this.betterDataI = new int[256];

    for (int i = 0; i < model.getDimensions()[0]; i++) {
      for (int j = 0; j < model.getDimensions()[1]; j++) {
        Pixel cur = model.getPixel(i, j);
        this.betterDataR[cur.getRGB()[0]] += 1;
        this.betterDataG[cur.getRGB()[1]] += 1;
        this.betterDataB[cur.getRGB()[2]] += 1;
        int intensity = (cur.getRGB()[0] + cur.getRGB()[1] + cur.getRGB()[2]) / 3;
        this.betterDataI[intensity] += 1;
      }
    }
    return this;
  }

  @Override
  public ArrayList<int[]> getHistogramData() {
    ArrayList<int[]> data = new ArrayList<>();
    data.add(betterDataR);
    data.add(betterDataG);
    data.add(betterDataB);
    data.add(betterDataI);
    return data;
  }

  /**
   * Creates an image of the bar chart representing this histogram to be displayed by other methods.
   *
   * @return the image created to represent this histogram
   */
  @Override
  public BufferedImage createHistogram() {
    BarChartGraphic chart = new BarChartGraphic();
    chart.addBar(Color.red, this.getHistogramData().get(0)); //addBar expands the image
    chart.addBar(Color.green, this.getHistogramData().get(1));
    chart.addBar(Color.blue, this.getHistogramData().get(2));
    chart.addBar(Color.white, this.getHistogramData().get(3));
    BufferedImage image = new BufferedImage(chart.getPreferredSize().width,
            chart.getPreferredSize().height,
            BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics = image.createGraphics();
    chart.paint(graphics);
    graphics.drawImage(image, image.getWidth(new JLabel()), image.getHeight(new JLabel()),
            new JLabel());
    graphics.dispose();
    return image;
  }


}
