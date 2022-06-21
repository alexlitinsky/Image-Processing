package controller.commands;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Image;
import model.Pixel;

public class BetterSave implements Command {
  private final String path;
  private final String fileType;
  private final Image model;

  public BetterSave(String path, String fileType, Image model) {
    this.path = path;
    this.fileType = fileType;
    this.model = model;
  }

  @Override
  public void commandApply() {
    switch (fileType) {
      case "ppm":
        try {
          File ppm = new File(path + fileType);
          FileWriter ppmWriter = new FileWriter(path + fileType);
          StringBuilder writtenFile = new StringBuilder("P3\n" + this.model.getDimensions()[0]
                  + "\n" + this.model.getDimensions()[1] + "\n255\n");
          for (int i = 0; i < this.model.getDimensions()[1]; i++) {
            for (int j = 0; j < this.model.getDimensions()[0]; j++) {
              Pixel cur = this.model.getPixel(j, i);
              writtenFile.append(cur.getRGB()[0]).append(" ")
                      .append(cur.getRGB()[1]).append(" ").append(cur.getRGB()[2]).append(" ");
            }
            writtenFile.append("\n");
          }
          ppmWriter.write(writtenFile.toString());
          ppmWriter.close();
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        break;
      case "png":
      case "bmp":
      case "jpg":
      case "jpeg":
        BufferedImage img = new BufferedImage(model.getDimensions()[0],
                model.getDimensions()[1], BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < img.getWidth(); i++) {
          for (int j = 0; j < img.getHeight(); j++) {
            int[] p = model.getPixel(i, j).getRGB();
            Color c = new Color(p[0], p[1], p[2]);
            img.setRGB(i, j, c.getRGB());
          }
        }
        try {
          ImageIO.write(img, fileType, new File(path + fileType));
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        break;
      default:
        throw new IllegalArgumentException("Wrong file type.");
    }
  }
}
