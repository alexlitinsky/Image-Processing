package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import controller.commands.BetterSave;
import controller.commands.Command;
import histomodel.ImageHistogram;
import model.Image;
import model.modifiers.BlurModifier;
import model.modifiers.GreyscaleModifier;
import model.modifiers.SepiaModifier;
import model.modifiers.SharpenModifier;
import utilities.ImageUtil;
import view.GraphicalView;

public class GUIControllerImpl implements ActionListener, ImageController {

  private final GraphicalView view;
  private Image model;

  /**
   * Constructs a GraphicalController object with a view and model.
   *
   * @param view  IGraphicalView view object
   * @param model ILayer model obj
   */
  public GUIControllerImpl(GraphicalView view, Image model) {
    this.view = view;
    this.model = model;
  }

  @Override
  public void playGame() {
    this.view.setListener(this);
    this.view.display();
  }

  private void loadHandler() {
    List<String> res = this.view.dialogHandler("load");

    if (res == null) {
      return;
    }

    if (res.size() == 2) {
      if (res.get(0).equals("Image")) {
        try {
          this.model = ImageUtil.readFile(res.get(0) + res.get(1));
          //this.model.addLayer(this.fileController.readImage(res.get(1)));
        } catch (IOException ioException) {
          this.view.alert("There was an error loading that image");
          return;
        }
        this.view.alert("Image loaded successfully!");
      } else {
        throw new IllegalArgumentException("Couldn't load image.");
      }
    }
  }

  private boolean isNumeric(String str) {
    try {
      Double.parseDouble(str);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }



//  private void toggleVisHandler() {
//    List<String> res = this.view.dialogHandler("toggle");
//
//    if (res == null) {
//      return;
//    }
//
//    if (!res.get(0).equals("")) {
//      String idx = res.get(0);
//      if (this.isNumeric(idx)) {
//        int pIdx = Integer.parseInt(idx);
//        if (this.model.getProps().get(0) < pIdx || pIdx <= 0) {
//          this.view.alert("That index is out of bounds!");
//        } else {
//          this.model.toggleVisibility(pIdx);
//          this.view.alert("Visibility was set");
//        }
//      } else {
//        this.view.alert("The index must be a number");
//      }
//    } else {
//      this.view.alert("Invalid layer index");
//    }
//  }

//  private void setCurrentHandler() {
//    List<String> res = this.view.dialogHandler("set");
//
//    if (res == null) {
//      return;
//    }
//
//    if (!res.get(0).equals("")) {
//      String idx = res.get(0);
//      if (this.isNumeric(idx)) {
//        int pIdx = Integer.parseInt(idx);
//        if (this.model.getProps().get(0) < pIdx || pIdx <= 0) {
//          this.view.alert("That index is out of bounds!");
//        } else {
//          this.model.setCurrent(pIdx);
//          this.view.alert("Layer " + idx + " is now current");
//        }
//      } else {
//        this.view.alert("The index must be a number");
//      }
//    } else {
//      this.view.alert("Invalid layer index");
//    }
//  }

//  private void saveHandler() {
//    List<String> res = this.view.dialogHandler("save");
//
//    if (res == null) {
//      return;
//    }
//
//    String filePath = res.get(0);
//    try {
//      this.fileController.writeTextOrPPM(filePath, "txt", this.model.toString());
//      this.view.alert("State saved successfully!");
//    } catch (IOException e) {
//      this.view.alert("Could not save state!");
//    }
//  }

  private void saveHandler() {
    List<String> res = this.view.dialogHandler("export");

    if (res == null) {
      return;
    }

    String filePath = res.get(0);
    String fileExt = res.get(1);
    try {
      Command saver = new BetterSave(filePath, fileExt, this.model);
      saver.commandApply();
      this.view.alert("Image exported successfully!");
    } catch (IOException e) {
      this.view.alert("Error exporting image!");
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();

    if (command.equals("Load")) {
      this.loadHandler();
    } else {
      if (this.model != null) {
        switch (command) {
          case "Blur":
            this.model.newModdedImage(new BlurModifier());
            this.view.alert("Blur applied successfully!");
            break;
          case "Sepia":
            this.model.newModdedImage(new SepiaModifier());
            this.view.alert("Sepia applied successfully!");
            break;
          case "Sharpen":
            this.model.newModdedImage(new SharpenModifier());
            this.view.alert("Sharpen applied successfully!");
            break;
          case "Greyscale":
            this.model.newModdedImage(new GreyscaleModifier());
            this.view.alert("Greyscale applied successfully!");
            break;
            // don't think I need these
//          case "Toggle Visibility":
//            this.toggleVisHandler();
//            break;
//          case "Set Current":
//            this.setCurrentHandler();
//            break;
          case "Save":
            this.saveHandler();
            break;
          default:
            throw new IllegalStateException("Reached a point which should absolutely not have "
                    + "been reached");
        }
      } else {
        this.view.alert("There is no image to modify! Load an image first!");
      }
    }

    if (this.model != null) {
      this.view.refresh();
    }
  }

  @Override
  public Map<String, Image> getVersions() {
    return null;
  }
}
