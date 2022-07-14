package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import controller.commands.BetterSave;
import controller.commands.Command;
import controller.commands.Save;
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

  private ImageHistogram histo;

  /**
   * Constructs a GraphicalController object with a view and model.
   *
   * @param view  IGraphicalView view object
   * @param model image model obj
   */
  public GUIControllerImpl(GraphicalView view, Image model, ImageHistogram histo) {
    this.view = view;
    this.model = model;
    this.histo = histo;
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
          this.histo = this.histo.update(this.model);
          this.view.updateModel(this.model);
          this.view.updateHistogram(histo);
          //this.view.alert("Image loaded successfully!");
        } catch (IOException ioException) {
          this.view.alert("There was an error loading that image");
          return;
        }
      } else {
        throw new IllegalArgumentException("Couldn't load image.");
      }
    }
  }


  /**
   * Method to handle the case where the user chooses to save the current image.
   */
  private void saveHandler() {
    List<String> res = this.view.dialogHandler("save");

    if (res == null) {
      return;
    }

    String filePath = res.get(0);
    String fileExt = res.get(0).substring(res.get(0).length() - 3);
    try {
      Command saver = new BetterSave(filePath, fileExt, this.model);
      saver.commandApply();
      //this.view.alert("Image saved successfully!");
    } catch (IOException e) {
      this.view.alert("Error saving image!");
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
            this.model = this.model.newModdedImage(new FlipModifier(true));
            this.histo = this.histo.update(this.model);
            this.view.updateModel(this.model);
            this.view.updateHistogram(this.histo);
            //this.view.alert("Vertical flip applied successfully!");
            break;
          case "Horizontal flip":
            this.model = this.model.newModdedImage(new FlipModifier(false));
            this.histo = this.histo.update(this.model);
            this.view.updateModel(this.model);
            this.view.updateHistogram(this.histo);
            //this.view.alert("Horizontal flip applied successfully!");
            break;
          case "Red component":
            this.model = this.model.newModdedImage(new RedCompModifier());
            this.histo = this.histo.update(this.model);
            this.view.updateModel(this.model);
            this.view.updateHistogram(this.histo);
            //this.view.alert("Red component applied successfully!");
            break;
          case "Green component":
            this.model = this.model.newModdedImage(new GreenCompModifier());
            this.histo = this.histo.update(this.model);
            this.view.updateModel(this.model);
            this.view.updateHistogram(this.histo);
            //this.view.alert("Green component applied successfully!");
            break;
          case "Blue component":
            this.model = this.model.newModdedImage(new BlueCompModifier());
            this.histo = this.histo.update(this.model);
            this.view.updateModel(this.model);
            this.view.updateHistogram(this.histo);
            //this.view.alert("Blue component applied successfully!");
            break;
          case "Value component":
            this.model = this.model.newModdedImage(new ValueCompModifier());
            this.histo = this.histo.update(this.model);
            this.view.updateModel(this.model);
            this.view.updateHistogram(this.histo);
            //this.view.alert("Value component applied successfully!");
            break;
          case "Intensity component":
            this.model = this.model.newModdedImage(new IntensityCompModifier());
            this.histo = this.histo.update(this.model);
            this.view.updateModel(this.model);
            this.view.updateHistogram(this.histo);
            // this.view.alert("Intensity component applied successfully!");
            break;
          case "Luma component":
            this.model = this.model.newModdedImage(new LumaCompModifier());
            this.histo = this.histo.update(this.model);
            this.view.updateModel(this.model);
            this.view.updateHistogram(this.histo);
            //this.view.alert("Luma component applied successfully!");
            break;
          case "Brighten":
            this.model = this.model.newModdedImage(new BrightnessModifier(10));
            this.histo = this.histo.update(this.model);
            this.view.updateModel(this.model);
            this.view.updateHistogram(this.histo);
            //this.view.alert("Brighten applied successfully!");
            break;
          case "Darken":
            this.model = this.model.newModdedImage(new BrightnessModifier(-10));
            this.histo = this.histo.update(this.model);
            this.view.updateModel(this.model);
            this.view.updateHistogram(this.histo);
            //this.view.alert("Darken applied successfully!");
            break;
          case "Blur":
            this.model = this.model.newModdedImage(new BlurModifier());
            this.histo = this.histo.update(this.model);
            this.view.updateModel(this.model);
            this.view.updateHistogram(this.histo);
            //this.view.alert("Blur applied successfully!");
            break;
          case "Sepia":
            this.model = this.model.newModdedImage(new SepiaModifier());
            this.histo = this.histo.update(this.model);
            this.view.updateModel(this.model);
            this.view.updateHistogram(this.histo);
            this.view.refresh();
            //this.view.alert("Sepia applied successfully!");
            break;
          case "Sharpen":
            this.model = this.model.newModdedImage(new SharpenModifier());
            this.histo = this.histo.update(this.model);
            this.view.updateModel(this.model);
            this.view.updateHistogram(this.histo);
            //this.view.alert("Sharpen applied successfully!");
            break;
          case "Greyscale":
            this.model = this.model.newModdedImage(new GreyscaleModifier());
            this.histo = this.histo.update(this.model);
            this.view.updateModel(this.model);
            this.view.updateHistogram(this.histo);
            //this.view.alert("Greyscale applied successfully!");
            break;
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
