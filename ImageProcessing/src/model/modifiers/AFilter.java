package model.modifiers;

import java.util.List;

import model.ImageModel;
import model.Pixel;

public abstract class AFilter extends AForm {

  public AFilter(double[][] kernel) throws IllegalArgumentException {
    super(kernel);
  }

  protected double[] genNewRGBs(ImageModel mod, Pixel p, int[] coords) {
    double[] applyRGB = {0.0, 0.0, 0.0};
    int key = (kernel.length - 1) / 2;
    int x = coords[0];
    int y = coords[1];
    int kInd = 0;
    for (int i = -1 * key; i <= key; i++) {
      for (int j = -1 * key; j <= key; j++) {
        int[] ind = new int[]{x + j, y + i};
        if (!(ind[0] < 0 || ind[1] < 0 || ind[0] >= mod.getDimensions()[0] || ind[1] >= mod.getDimensions()[1])) {
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

  @Override
  protected double[] applyToEachPixel(Pixel p, int[] coords) {
    return this.genNewRGBs(oldModel, p, coords);
  }


}
