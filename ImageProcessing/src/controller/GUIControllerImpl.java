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
import model.modifiers.BlueCompModifier;
import model.modifiers.BlurModifier;
import model.modifiers.BrightnessModifier;
import model.modifiers.FlipModifier;
import model.modifiers.GreenCompModifier;
import model.modifiers.GreyscaleModifier;
import model.modifiers.IntensityCompModifier;
import model.modifiers.LumaCompModifier;
import model.modifiers.RedCompModifier;
import model.modifiers.SepiaModifier;
import model.modifiers.SharpenModifier;
import model.modifiers.ValueCompModifier;
import utilities.ImageUtil;
import view.GraphicalView;

/**
 * Implementation of a GUI-controlled controller.
 */
public class GUIControllerImpl implements ActionListener, ImageController {

  private final GraphicalView view;
  private Image model;

  /**
   * Constructs a GraphicalController object with a view and model.
   *
   * @param view  IGraphicalView view object
   * @param model image model obj
   */
  public GUIControllerImpl(GraphicalView view, Image model) {
    this.view = view;
    this.model = model;
  }

  /**
   * Method to start the imageProcessing program.
   */
  @Override
  public void playGame() {
    this.view.setListener(this);
    this.view.display();
  }

  /**
   * Method to handle the case where the user clicks the load button.
   */
  private void loadHandler() {
    List<String> res = this.view.dialogHandler("load");

    if (res == null) {
      return;
    }

    if (res.size() == 2) {
      if (res.contains("Image")) {
        try {
          this.model = ImageUtil.readFile(res.get(1));
          this.view.updateModel(this.model);
          this.view.alert("Image loaded successfully!");
        } catch (IOException ioException) {
          this.view.alert("There was an error loading that image");
          return;
        }
      } else {
        throw new IllegalArgumentException("Couldn't load image.");
      }
    }
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

  /**
   * Method to handle the case where the user chooses to save the current image.
   */
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

  /**
   * Method to abstract the updating of the view model to the current controller model.
   */
  private void updateViewModel() {
    this.view.updateModel(this.model);
  }

  /**
   * Method which manipulates the model based on which action is performed by the user.
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();

    if (command.equals("Load")) {
      this.loadHandler();
    } else {
      if (this.model != null) {
        switch (command) {
          case "Vertical flip":
            this.view.updateModel(this.model.newModdedImage(new FlipModifier(true)));
            this.view.alert("Vertical flip applied successfully!");
            break;
          case "Horizontal flip":
            this.view.updateModel(this.model.newModdedImage(new FlipModifier(false)));
            this.view.alert("Horizontal flip applied successfully!");
            break;
          case "Red component":
            this.view.updateModel(this.model.newModdedImage(new RedCompModifier()));
            this.view.alert("Red component applied successfully!");
            break;
          case "Green component":
            this.view.updateModel(this.model.newModdedImage(new GreenCompModifier()));
            this.view.alert("Green component applied successfully!");
            break;
          case "Blue component":
            this.view.updateModel(this.model.newModdedImage(new BlueCompModifier()));
            this.view.alert("Blue component applied successfully!");
            break;
          case "Value component":
            this.view.updateModel(this.model.newModdedImage(new ValueCompModifier()));
            this.view.alert("Value component applied successfully!");
            break;
          case "Intensity component":
            this.view.updateModel(this.model.newModdedImage(new IntensityCompModifier()));
            this.view.alert("Intensity component applied successfully!");
            break;
          case "Luma component":
            this.view.updateModel(this.model.newModdedImage(new LumaCompModifier()));
            this.view.alert("Luma component applied successfully!");
            break;
          case "Brighten":
            this.view.updateModel(this.model.newModdedImage(new BrightnessModifier(10)));
            this.view.alert("Brighten applied successfully!");
            break;
          case "Darken":
            this.view.updateModel(this.model.newModdedImage(new BrightnessModifier(-10)));
            this.view.alert("Darken applied successfully!");
            break;
          case "Blur":
            this.view.updateModel(this.model.newModdedImage(new BlurModifier()));
            this.view.alert("Blur applied successfully!");
            break;
          case "Sepia":
            this.view.updateModel(this.model.newModdedImage(new SepiaModifier()));
            this.view.alert("Sepia applied successfully!");
            break;
          case "Sharpen":
            this.view.updateModel(this.model.newModdedImage(new SharpenModifier()));
            this.view.alert("Sharpen applied successfully!");
            break;
          case "Greyscale":
            this.view.updateModel(this.model.newModdedImage(new GreyscaleModifier()));
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

  /**
   * Method to retrieve the versions of this controller. For a GUI controller, the only model
   * version is the one currently being manipulated by the user.
   * @return null because there is no map of versions to return because there is only one version
   */
  @Override
  public Map<String, Image> getVersions() {
    return null;
  }
}
