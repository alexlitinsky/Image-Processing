package view;

import java.io.IOException;
import model.ImageModel;

/**
 * Abstract class to represent text views for our image class.
 */
public abstract class ImageTextView implements TextView{
  protected ImageModel model;
  protected Appendable destination;

  /**
   * Constructor for image text views. Requires a model to view and a destination to send the view.
   * @param model the model being viewed
   * @param destination location where the view is sent
   */
  ImageTextView(ImageModel model, Appendable destination) {
    this.model = model;
    this.destination = destination;
  }
}
