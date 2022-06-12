package model.modifiers;

import java.util.List;

import model.ImageModel;
import model.Pixel;

public abstract class AFilter extends AForm {

  public AFilter(double[][] kernel) throws IllegalArgumentException {
    super(kernel);
  }

  protected double[] genNewRGBs(ImageModel mod, Pixel p) {
    double[] applyRGB = {0.0, 0.0, 0.0};
    int key = (kernel.length - 1) / 2;
    int x = mod.findPixel(p)[0];
    int y = mod.findPixel(p)[1];
    List<Pixel> convList = mod.convertToList();
    int kInd = 0;
    for (int i = -1 * key; i <= key; i++) {
      for (int j = -1 * key; j <= key; j++) {
        int ind = ((y + i) * mod.getDimensions()[0]) + (x + j);
        if (!(ind < 0 || ind >= convList.size() || (x + j < 0) || (y + i < 0)
        || (x + j) > mod.getDimensions()[0] || (y + i) > mod.getDimensions()[1] -1)) {
          double[] cur = convList.get(ind).applyToAll(linearKernel.get(kInd));
          for (int k = 0; k < applyRGB.length; k++) {
            applyRGB[k] += cur[k];
          }
        }
        kInd++;
      }
    }
    return applyRGB;
  }

  @Override
  protected double[] applyToEachPixel(Pixel p) {
    return this.genNewRGBs(build, p);
  }


}
