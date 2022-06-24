package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JPanel;

/**
 * Class to represent a bar chart graphic - is displayed in the graphical view.
 */
public class BarChartGraphic extends JPanel {
  private Map<Color, Integer> bars = new LinkedHashMap<Color, Integer>();

  /**
   * Adds a new bar to the chart.
   * @param color color to display bar
   * @param value size of bar
   */
  public void addBar(Color color, int value)
  {
    bars.put(color, value);
    repaint();
  }

  /**
   * Paints the bars of a bar chart using a Graphics object.
   *
   * @param g the Graphics object to paint
   */
  @Override
  protected void paintComponent(Graphics g)
  {
    // determines the longest bar (for sizing purposes)
    int max = 0;
    for (Integer value : bars.values())
    {
      max = Math.max(max, value);
    }

    // paints the bars
    int width = (getWidth() / bars.size()) - 2;
    int x = 1;
    for (Color color : bars.keySet())
    {
      int value = bars.get(color);
      int height = (int)
              ((getHeight() - 5) * ((double) value / max));
      g.setColor(color);
      g.fillRect(x, getHeight() - height, width, height);
      g.setColor(Color.black);
      g.drawRect(x, getHeight() - height, width, height);
      x += (width + 2);
    }
  }

  /**
   * Retrieves the preferred size for this bar chart.
   * @return a Dimension representing the preferred size for this bar chart
   */
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(bars.size() * 10 + 2, 50);
  }
}
