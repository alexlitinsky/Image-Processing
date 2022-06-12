package model.modifiers;

import java.util.ArrayList;
import java.util.List;

import model.ImageModel;
import model.ImageModelImpl;
import model.Pixel;

public abstract class AForm implements Modifier {

  protected ImageModel build;
  protected ImageModel oldModel;
  protected double[][] kernel;
  protected List<Double> linearKernel;

  public AForm(double[][] kernel) throws IllegalArgumentException {
    if (!this.isOddKernel(kernel)) { throw new IllegalArgumentException("Invalid kernel."); }
    this.kernel = kernel;
    this.linearKernel = this.linearize(kernel);
  }

  protected List<Double> linearize(double[][] kernel) {
    List<Double> linearKernel = new ArrayList<>();
    // change possibly to for each
    for (int i = 0; i < kernel[0].length; i++) {
      for (int j = 0; j < kernel.length; j++) {
        linearKernel.add(kernel[i][j]);
      }
    }
    return linearKernel;
  }

  public ImageModel apply(ImageModel model) throws IllegalArgumentException {
    this.oldModel = model;
    this.build = new ImageModelImpl(model.getDimensions()[0], model.getDimensions()[1]);
    for (int i = 0; i < model.getDimensions()[0]; i++) {
      for (int j = 0; j < model.getDimensions()[1]; j++) {
        double[] pixels = this.applyToEachPixel(model.getPixel(i, j), new int[] {i, j});
        build.assignPixels(i, j, (int) pixels[0], (int) pixels[1], (int) pixels[2]);
      }
    }

    return build;
  }

  protected boolean isOddKernel(double[][] kernel) {
    return kernel.length % 2 != 0 && kernel[0].length % 2 != 0;
  }

  protected abstract double[] applyToEachPixel(Pixel p, int[] coords);


}
