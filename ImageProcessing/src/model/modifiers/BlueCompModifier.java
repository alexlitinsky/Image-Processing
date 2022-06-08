package model.modifiers;

import model.ImageModel;

public class BlueCompModifier implements Modifier {

  public BlueCompModifier() {}

  @Override
  public ImageModel apply(ImageModel model) {
    ImageModel build = new ImageModel(model.getDimensions()[0], model.getDimensions()[1]);
    for (int i = 0; i < model.getDimensions()[0]; i++) {
      for (int j = 0; j < model.getDimensions()[1]; j++) {
        int blue = model.getPixel(i, j).getRGB()[1];
        build.assignPixels(i, j, blue, blue, blue);
      }
    }
    return build;
  }
}