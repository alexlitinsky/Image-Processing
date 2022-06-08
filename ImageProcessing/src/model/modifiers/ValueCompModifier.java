package model.modifiers;

import model.ImageModel;

public class ValueCompModifier implements Modifier {

  public ValueCompModifier() {
  }

  @Override
  public ImageModel apply(ImageModel model) {
    ImageModel build = new ImageModel(model.getDimensions()[0], model.getDimensions()[1]);
    for (int i = 0; i < model.getDimensions()[0]; i++) {
      for (int j = 0; j < model.getDimensions()[1]; j++) {
        int val = Math.max(model.getPixel(i, j).getRGB()[0],
                Math.max(model.getPixel(i, j).getRGB()[1], model.getPixel(i, j).getRGB()[2]));
        build.assignPixels(i, j, val, val, val);
      }
    }
    return build;
  }
}
