package model.modifiers;

import model.ImageModel;
import model.Pixel;

public abstract class ATransform extends AForm {


  public ATransform(double[][] kernel) throws IllegalArgumentException {
    super(kernel);
  }

  protected double[] genNewRGBs(Pixel p) {
    double[] applyRGB = {0.0, 0.0, 0.0};
    applyRGB[0] = p.applyToR(linearKernel.get(0)) + p.applyToG(linearKernel.get(1))
            + p.applyToB(linearKernel.get(2));
    applyRGB[1] = p.applyToR(linearKernel.get(3)) + p.applyToG(linearKernel.get(4))
            + p.applyToB(linearKernel.get(5));
    applyRGB[2] = p.applyToR(linearKernel.get(6)) + p.applyToG(linearKernel.get(7))
            + p.applyToB(linearKernel.get(8));

    return new double[]{
            Math.max(Math.min(applyRGB[0], 255), 0),
            Math.max(Math.min(applyRGB[1], 255), 0), Math.max(Math.min(applyRGB[2], 255), 0)
    };
  }

  public double[] applyToEachPixel(Pixel p, int[] coords) {
    return this.genNewRGBs(p);

  }

}
