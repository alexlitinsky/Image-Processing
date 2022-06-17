package model.modifiers;

import model.Image;
import model.Pixel;

/**
 * Class to represent filter modifiers. These modifiers apply a filter to a kernal, changing the
 * look of an image by manipulating its RGB values. Contains method implementations common to
 * all filters.
 */
public abstract class AFilter extends AForm {

  /**
   * Constructor for an abstract filter. Calls super constructor for the only argument.
   *
   * @param kernel the 2D array of values to multiply pixels by
   * @throws IllegalArgumentException if the kernel is invalid (null or odd)
   */
  public AFilter(double[][] kernel) throws IllegalArgumentException {
    super(kernel);
  }

  /**
   * Method to generate new RGB values based on the modification kernel being used.
   *
   * @param mod    the modifier being used
   * @param coords the coordinates of the pixel being modified
   * @return an array of doubles for the new values
   */
  protected double[] genNewRGBs(Image mod, int[] coords) {
    double[] applyRGB = {0.0, 0.0, 0.0};
    int key = (kernel.length - 1) / 2;
    int x = coords[0];
    int y = coords[1];
    int kInd = 0;
    for (int i = -1 * key; i <= key; i++) {
      for (int j = -1 * key; j <= key; j++) {
        int[] ind = new int[]{x + j, y + i};
        if (!(ind[0] < 0 || ind[1] < 0 || ind[0] >= mod.getDimensions()[0] || ind[1]
                >= mod.getDimensions()[1])) {
          double[] cur = mod.getPixel(ind[0], ind[1]).applyToAll(linearKernel.get(kInd));
          for (int k = 0; k < applyRGB.length; k++) {
            applyRGB[k] += cur[k];
          }
        }
        kInd++;
      }
    }
    return new double[]{
            Math.max(Math.min(applyRGB[0], 255), 0),
            Math.max(Math.min(applyRGB[1], 255), 0), Math.max(Math.min(applyRGB[2], 255), 0)
    };
  }

  /**
   * Applies the modifier to a specific pixel at a given location.
   *
   * @param p      the pixel being modified
   * @param coords the location of the target pixel
   * @return the new RGB values in an array of doubles
   */
  @Override
  protected double[] applyToEachPixel(Pixel p, int[] coords) {
    return this.genNewRGBs(oldModel, coords);
  }
}
