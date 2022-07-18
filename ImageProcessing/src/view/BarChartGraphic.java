package view;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

/**
 * Class to represent a bar chart graphic - is displayed in the graphical view.
 */
public class BarChartGraphic extends JPanel {

  private Map<Color, Map<Integer, Integer>> color_bars = new HashMap<Color, Map<Integer, Integer>>();

  /**
   * Adds a new bar to the chart.
   * @param color color to display bar
   * @param value size of bar
   */
  public void addBar(Color color, Map<Integer, Integer> value)
  {
    //bars.put(color, value);
    color_bars.put(color, value);
    repaint();
  }

  /**
   * Paints the bars of a bar chart using a Graphics object.
   *
   * @param g the Graphics object to paint
   */
//  @Override
//  public void paint(Graphics g) {
//    super.paint(g);
//    Graphics2D g2d = (Graphics2D) g;
//    int height = 120;
//    int width = 0;
////    g.setColor(Color.white);
////    g.fillRect(0, 0, width, height);
//    // for each color
//    for (Color col : color_bars.keySet()) {
//      Map<Integer, Integer> values = color_bars.get(col);
//      g2d.setColor(col);
//      // for each individual value in the hashmap
//      for (Integer map_vals : values.values()) {
//        width+= 1;
//        g2d.fillRect(width, height, 1,  (int)( .5 * map_vals));
//        g2d.rotate(Math.PI);
//      }
//      // reset width
//      width = 0;
//      // draw axis
//      g2d.drawLine(width, height, width + 255, height);
//      g2d.drawLine(width, height, width, height - 300);
//      // increment height to separate the graphs
//      height += 300;
//    }
//  }

  @Override
  public void paint(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    int height = 0;
    int width = 0;
    g2d.setColor(Color.white);
    g2d.fillRect(0, 0, width, height);
    // for each color
    for (Color col : color_bars.keySet()) {
      Map<Integer, Integer> values = color_bars.get(col);
      g2d.setColor(col);
      // for each individual value in the hashmap
      int min = 0;
      int max = 0;
      g2d.drawLine(width + 3, height, width + 3, height + 140);
      g2d.drawLine(width, height, width + 255, height);
      for (Integer map_vals : values.values()) {
        width+= 1;
        max = Math.max((int)( .05 * map_vals), max);
        min = Math.min((int)( .05 * map_vals), 140);
        g2d.fillRect(width, height, 1,  min);
        g2d.rotate(Math.PI);
      }
      // reset width
      width = 0;
      // draw axis
      //g2d.drawLine(width, height, width + 255, height);
      //g2d.drawLine(width, height, width, height + Math.min(max, 140));
      // increment height to separate the graphs
      // 120 before
      height += Math.min(max, 140);
    }
  }

  /**
   * Retrieves the preferred size for this bar chart.
   * @return a Dimension representing the preferred size for this bar chart
   */
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(258, 1200);
  }
}

/* BAR CHART PSEUDOCODE
   4 charts - red, green, blue, intensity
   Bar Chart takes in an instance of a histogram (how would we update it?) - I think it does it automatically given the code)
   So now we have 4 hashmaps of values

   width - 255 (amount of distinct values)
   height - the max value of each specific hashmap

   30 pixel differential among the graphs


 */
