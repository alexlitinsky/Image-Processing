package model.modifiers;

import model.ImageModel;
import model.Pixel;

/**
 * Abstract class to represent common methods between transform modifiers. Contains methods to
 * generate new RGB values and apply values to each RGB component of a pixel.
 */
public abstract class ATransform extends AForm {

  /**
   * Constructor for the ATransform class. Just calls the AForm constructor.
   * @param kernel the 2D array of values passed to the modifier, changes based on modifier type
   * @throws IllegalArgumentException if the kernel is even
   */
  public ATransform(double[][] kernel) throws IllegalArgumentException {
    super(kernel);
  }

  /**
   * Generates new RGB values for a pixel by applying the correct kernel value to each item in the
   * linearized kernel.
   * @param p the pixel being worked with
   * @return an array of doubles representing each new RGB value
   */
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

  /**
   * Applies the filter to the RGB values of the pixel at the given coordinates.
   * @param p the pixel being manipulated
   * @param coords the location of the pixel to apply the filter to
   * @return
   */
  public double[] applyToEachPixel(Pixel p, int[] coords) {
    return this.genNewRGBs(p);
  }
}
