package model.modifiers;

import model.ImageModel;

public class LumaCompModifier implements Modifier {

  public LumaCompModifier() {
  }

  @Override
  public ImageModel apply(ImageModel model) {
    ImageModel build = new ImageModel(model.getDimensions()[0], model.getDimensions()[1]);
    for (int i = 0; i < model.getDimensions()[0]; i++) {
      for (int j = 0; j < model.getDimensions()[1]; j++) {
        int luma = (int) Math.round(model.getPixel(i, j).getRGB()[0] * .2126
                + model.getPixel(i, j).getRGB()[1] * .7152 +
                model.getPixel(i, j).getRGB()[2] * .0722);
        build.assignPixels(i, j, luma, luma, luma);
      }
    }
    return build;
  }
}