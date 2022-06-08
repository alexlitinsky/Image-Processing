package model.modifiers;

import model.ImageModel;

public class RedCompModifier implements Modifier {

  public RedCompModifier() {}

  @Override
  public ImageModel apply(ImageModel model) {
    ImageModel build = new ImageModel(model.getDimensions()[0], model.getDimensions()[1]);
    for (int i = 0; i < model.getDimensions()[0]; i++) {
      for (int j = 0; j < model.getDimensions()[1]; j++) {
        int red = model.getPixel(i, j).getRGB()[0];
        build.assignPixels(i, j, red, red, red);
      }
    }
    return build;
  }
}
