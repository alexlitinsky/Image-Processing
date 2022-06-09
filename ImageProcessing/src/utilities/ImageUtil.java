package utilities;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.ImageModel;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * This method reads a ppm file, checking that the file exists and is of the right type, and
   * returns an ImageModel representation of the ppm file.
   *
   * @param filename the name and path of the file to be read
   * @return the image model representation of the ppm file
   * @throws FileNotFoundException    if the file is of the correct format but cannot be found
   * @throws IllegalArgumentException if the file is of the incorrect format or is an invalid file
   */
  public static ImageModel readPPM(String filename) throws FileNotFoundException,
          IllegalArgumentException {

    String fileType = "";

    for (int i = 0; i < filename.length(); i++) {
      if (filename.charAt(i) == '.') {
        fileType = filename.substring(i + 1);
      }
    }
    if (fileType.equals("")) {
      throw new IllegalArgumentException("Invalid file.");
    }

    Scanner sc;
    switch (fileType) {
      case "ppm":
        try {
          sc = new Scanner(new FileInputStream(filename));
        } catch (FileNotFoundException e) {
          throw new FileNotFoundException("Filename " + filename + " not found!");
        }
        StringBuilder builder = new StringBuilder();
        //read the file line by line, and populate a string. This will throw away any comment lines
        while (sc.hasNextLine()) {
          String s = sc.nextLine();
          if (s.charAt(0) != '#') {
            builder.append(s + System.lineSeparator());
          }
        }

        //now set up the scanner to read from the string we just built
        sc = new Scanner(builder.toString());

        String token;

        token = sc.next();
        if (!token.equals("P3")) {
          System.out.println("Invalid PPM file: plain RAW file should begin with P3");
        }
        int width = sc.nextInt();
        System.out.println("Width of image: " + width);
        int height = sc.nextInt();
        System.out.println("Height of image: " + height);
        int maxValue = sc.nextInt();
        System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

        ImageModel image = new ImageModel(width, height);

        for (int i = 0; i < height; i++) {
          for (int j = 0; j < width; j++) {
            int r = sc.nextInt();
            int g = sc.nextInt();
            int b = sc.nextInt();
            image.assignPixels(j, i, r, g, b);
          }
        }

        return image;
      case "png":
        try {
          BufferedImage img = ImageIO.read(new File(filename));
          ImageModel pngImage = new ImageModel(img.getWidth(), img.getHeight());
          for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
              Color col = new Color(img.getRGB(i, j));
              pngImage.assignPixels(i, j, col.getRed(), col.getGreen(), col.getBlue());
            }
          }
          return pngImage;
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      default:
        throw new IllegalArgumentException("Wrong file type");
    }
  }
}
