package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import histomodel.ImageHistogram;
import model.Image;
import view.dialogs.AlertState;
import view.dialogs.LoadState;
import view.dialogs.SaveState;

/**
 * Class to represent an implementation of a graphical view.
 */
public class GraphicalViewImpl implements GraphicalView {

  private final JButton darkenBtn;
  private final JButton lumaCompBtn;
  private final JButton flipVBtn;
  private final JButton flipHBtn;
  private final JButton redCompBtn;
  private final JButton blueCompBtn;
  private final JButton greenCompBtn;
  private final JButton valueCompBtn;
  private final JButton intensityCompBtn;
  private final JButton brightenBtn;
  private final JButton blurBtn;
  private final JButton sharpenBtn;
  private final JButton sepiaBtn;
  private final JButton greyscaleBtn;
  private final JButton loadBtn;
  private final JButton saveBtn;
  private final JButton toggleVisBtn;
  private JTextField toolsField;
  private JPanel imageModArea;
  private JScrollPane imageDisplay;
  private JFrame frame;
  private JPanel imageHistoArea;
  private ActionListener listener;

  private ImageHistogram histo;

  private Image model;
  private JLabel imageAreaLabel;
  private JPanel buttons;

  //constructor and
  public GraphicalViewImpl(String text, ImageHistogram histo, Image model) {
    //this.create();
    this.histo = histo;
    this.model = model;
    this.imageDisplay = new JScrollPane();
    this.flipVBtn = new JButton("Vertical flip");
    flipVBtn.setActionCommand("Vertical flip");
    flipHBtn = new JButton("Horizontal flip");
    flipHBtn.setActionCommand("Horizontal flip");
    redCompBtn = new JButton("Red Component");
    redCompBtn.setActionCommand("Red component");
    blueCompBtn = new JButton("Blue component");
    blueCompBtn.setActionCommand("Blue component");
    greenCompBtn = new JButton("Green component");
    greenCompBtn.setActionCommand("Green component");
    valueCompBtn = new JButton("Value component");
    valueCompBtn.setActionCommand("Value component");
    intensityCompBtn = new JButton("Intensity component");
    intensityCompBtn.setActionCommand("Intensity component");
    lumaCompBtn = new JButton("Luma component");
    lumaCompBtn.setActionCommand("Luma component");
    brightenBtn = new JButton("Brightness + 10");
    brightenBtn.setActionCommand("Brighten");
    darkenBtn = new JButton("Brightness - 10");
    darkenBtn.setActionCommand("Darken");
    blurBtn = new JButton("Blur");
    blurBtn.setActionCommand("Blur");
    sharpenBtn = new JButton("Sharpen");
    sharpenBtn.setActionCommand("Sharpen");
    sepiaBtn = new JButton("Sepia");
    sepiaBtn.setActionCommand("Sepia");
    greyscaleBtn = new JButton("Greyscale");
    greyscaleBtn.setActionCommand("Greyscale");
    loadBtn = new JButton("Load");
    loadBtn.setActionCommand("Load");
    saveBtn = new JButton("Save");
    saveBtn.setActionCommand("Save");
    toggleVisBtn = new JButton("Toggle visibility");
    toggleVisBtn.setActionCommand("Toggle visibility");
    toolsField = new JTextField();

    //main modificaitons made start here
    frame = new JFrame("Image Processing Application");
    frame.setVisible(true);
    frame.setPreferredSize(new Dimension(1200, 800));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.pack();
    frame.setLocationRelativeTo(null);

    // add image mod area
    imageModArea = new JPanel();
    imageModArea.setBorder(BorderFactory.createTitledBorder("Image Modify Area"));
    imageModArea.setPreferredSize(new Dimension(950, 600));
    frame.add(imageModArea, BorderLayout.WEST);

    // add histogram area
    imageHistoArea = new JPanel();
    imageHistoArea.setBorder(BorderFactory.createTitledBorder("Image Histogram Area"));
    imageHistoArea.setPreferredSize(new Dimension(200, 600));
    frame.add(imageHistoArea, BorderLayout.EAST);

    // add buttons
    buttons = new JPanel();
    buttons.setPreferredSize(new Dimension(200, 200));
    buttons.add(loadBtn);
    buttons.add(saveBtn);
    buttons.add(flipVBtn);
    buttons.add(flipHBtn);
    buttons.add(redCompBtn);
    buttons.add(greenCompBtn);
    buttons.add(blueCompBtn);
    buttons.add(valueCompBtn);
    buttons.add(intensityCompBtn);
    buttons.add(lumaCompBtn);
    buttons.add(brightenBtn);
    buttons.add(darkenBtn);
    buttons.add(blurBtn);
    buttons.add(sharpenBtn);
    buttons.add(sepiaBtn);
    buttons.add(greyscaleBtn);
    frame.add(buttons, BorderLayout.SOUTH);

  }

  @Override
  public void display() {
    this.frame.setVisible(true);
  }

  /**
   * Updates the model shown to be the current model, affected by whatever the last modification
   * was.
   */
  @Override
  public void refresh() {
    Image currentModel = this.model;
    ImageHistogram currentHisto = this.histo;
    ImageIcon picture = new ImageIcon(currentModel.createBufferedImage());
    JLabel label = new JLabel(picture);
    if (currentModel != null) {
      this.imageModArea.add(label);
      this.imageModArea.updateUI();
      if (this.imageModArea.getComponentCount() >= 2) {
        imageModArea.remove(0);
      }
    } else {
      this.imageDisplay.setViewportView(new JLabel(new ImageIcon()));
    }
    // have to accompany histogram in view using different JLable, somewhere else on the screen
    if (currentHisto != null) {
      this.imageHistoArea.add(new JLabel(new ImageIcon(currentHisto.createHistogram())));
      this.imageHistoArea.updateUI();
      if (this.imageHistoArea.getComponentCount() >= 2) {
        imageHistoArea.remove(0);
      }
      //this.imageDisplay.setViewportView(new JLabel(new ImageIcon(currentHisto.createHistogram())));
    } else {
      //this.imageDisplay.setViewportView(new JLabel(new ImageIcon()));
    }
  }

  /**
   * Publishes an alert.
   *
   * @param msg the message to be printed
   */
  @Override
  public void alert(String msg) {
    new AlertState(msg);

  }

  /**
   * Updates the histogram in the view.
   *
   * @param histo the histogram to be displayed
   */
  @Override
  public void setHistogram(ImageHistogram histo) {
    this.histo = histo;
  }

  /**
   * The dialog handler for various results and commands.
   *
   * @param dialog the dialog to be monitored (based on which command is executed)
   * @return the result of a dialog interaction
   */
  @Override
  public List<String> dialogHandler(String dialog) {
    switch (dialog) {
      // not sure if we need these
//      case "toggle":
//        return new ToggleState().getState();
//      case "set":
//        return new CurrentState().getState();
      case "load":
        return new LoadState().getState();
      case "save":
        return new SaveState().getState();
      default:
        return null;
    }
  }

  /**
   * Sets the listener for this view.
   *
   * @param listener the action listener
   */
  @Override
  public void setListener(ActionListener listener) {
    this.listener = listener;
    this.setListeners();
  }

  /**
   * Updates the view model to the image model currently being manipulated by the user.
   *
   * @param newModel the new image model
   */
  @Override
  public void updateModel(Image newModel) {
    this.model = newModel;
  }

  private void setListeners() {
    flipVBtn.addActionListener(listener);
    flipHBtn.addActionListener(listener);
    redCompBtn.addActionListener(listener);
    greenCompBtn.addActionListener(listener);
    blueCompBtn.addActionListener(listener);
    valueCompBtn.addActionListener(listener);
    intensityCompBtn.addActionListener(listener);
    lumaCompBtn.addActionListener(listener);
    brightenBtn.addActionListener(listener);
    darkenBtn.addActionListener(listener);
    blurBtn.addActionListener(listener);
    sharpenBtn.addActionListener(listener);
    sepiaBtn.addActionListener(listener);
    greyscaleBtn.addActionListener(listener);
    loadBtn.addActionListener(listener);
    saveBtn.addActionListener(listener);
    toggleVisBtn.addActionListener(listener);
  }
}
