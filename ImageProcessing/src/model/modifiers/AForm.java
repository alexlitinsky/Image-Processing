package model.modifiers;

import java.util.ArrayList;
import java.util.List;

import model.Image;
import model.ImageImpl;
import model.Pixel;

/**
 * Class to represent abstracted form modifiers.
 */
public abstract class AForm implements Modifier {

  protected Image build;
  protected Image oldModel;
  protected double[][] kernel;
  protected List<Double> linearKernel;

  /**
   * Constructor for the abstract form modifier class. Enforces that all kernels are of odd size.
   *
   * @param kernel the 2D array of values to be applied to each pixel
   * @throws IllegalArgumentException if the kernel is width and height are not odd
   */
  public AForm(double[][] kernel) throws IllegalArgumentException {
    if (!this.isOddKernel(kernel)) {
      throw new IllegalArgumentException("Invalid kernel.");
    }
    this.kernel = kernel;
    this.linearKernel = this.linearize(kernel);
  }

  /**
   * Linearizes the kernel to make manipulation of each pixel easier.
   *
   * @param kernel the 2D array of values to be applied to each pixel
   * @return a list of doubles containing each value to be applied to each pixel
   */
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

  /**
   * Applies this modifier to the image. Returns the new image model.
   *
   * @param model the original image
   * @return the new image model created by applying this transformation filter
   * @throws IllegalArgumentException if the given model is null
   */
  public Image apply(Image model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("invalid model");
    }
    this.oldModel = model;
    this.build = new ImageImpl(model.getDimensions()[0], model.getDimensions()[1]);
    for (int i = 0; i < model.getDimensions()[0]; i++) {
      for (int j = 0; j < model.getDimensions()[1]; j++) {
        double[] pixels = this.applyToEachPixel(model.getPixel(i, j), new int[]{i, j});
        build.assignPixels(i, j, (int) pixels[0], (int) pixels[1], (int) pixels[2]);
      }
    }
    return build;
  }

  /**
   * Checks the the kernel height and width is odd. Cannot have an even sized kernel.
   *
   * @param kernel the 2D array of values to be applied to a pixel
   * @return true if the kernel has an odd width and height
   */
  protected boolean isOddKernel(double[][] kernel) {
    return kernel.length % 2 != 0 && kernel[0].length % 2 != 0;
  }

  /**
   * Applies this modifier to individual pixels. Applies to the pixel at the specified location.
   *
   * @param p      the pixel being modified
   * @param coords the location of the target pixel
   * @return an array of doubles representing the pixel's RGB values multiplied by the kernel
   */
  protected abstract double[] applyToEachPixel(Pixel p, int[] coords);


}
