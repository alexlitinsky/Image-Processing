package model.modifiers;

import model.ImageModel;

public class IntensityCompModifier implements Modifier {

  public IntensityCompModifier() {}

  @Override
  public ImageModel apply(ImageModel model) {
    ImageModel build = new ImageModel(model.getDimensions()[0], model.getDimensions()[1]);
    for (int i = 0; i < model.getDimensions()[0]; i++) {
      for (int j = 0; j < model.getDimensions()[1]; j++) {
        int its = (model.getPixel(i, j).getRGB()[0] + model.getPixel(i, j).getRGB()[1] +
                model.getPixel(i, j).getRGB()[2]) / 3;
        build.assignPixels(i, j, its, its, its);
      }
    }
    return build;
  }
}