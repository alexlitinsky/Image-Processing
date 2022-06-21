package view;

import java.awt.event.ActionListener;
import java.util.List;


import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;










import histomodel.ImageHistogram;
import model.Image;
import view.dialogs.AlertState;
import view.dialogs.LoadState;
import view.dialogs.SaveState;

public class GraphicalViewImpl implements GraphicalView {

  private JPanel panel;
  private JButton flipVBtn;
  private JButton flipHBtn;
  private JButton redCompBtn;
  private JButton blueCompBtn;
  private JButton greenCompBtn;
  private JButton valueCompBtn;
  private JButton intensityCompBtn;
  private JButton brightenBtn;
  private JButton blurBtn;
  private JButton sharpenBtn;
  private JButton sepiaBtn;
  private JButton greyscaleBtn;
  private JButton loadBtn;
  private JButton saveBtn;
  private JButton setCurBtn;
  private JButton toggleVisBtn;
  private JTextField toolsField;
  private JTextArea imageModArea;
  private JScrollPane imageDisplay;
  private JFrame frame;
  private ActionListener listener;

  private ImageHistogram histo;

  private Image model;

  public GraphicalViewImpl(String text, ImageHistogram histo, Image model) {
    this.create(text);
    this.histo = histo;
    this.model = model;

  }

  public void create(String text) {
    frame = new JFrame(text);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.pack();
    frame.setLocationRelativeTo(null);
  }

  @Override
  public void display() {this.frame.setVisible(true); }

  @Override
  public void refresh() {
    Image currentModel = this.model;
    ImageHistogram currentHisto = this.histo;
    if (currentModel != null) {
      this.imageDisplay.setViewportView(new JLabel(new ImageIcon(currentModel.createImage())));
    } else {
      this.imageDisplay.setViewportView(new JLabel(new ImageIcon()));
    }
    // have to accompany histogram in view using different JLable, somewhere else on the screen
    if (currentHisto != null) {
      this.imageDisplay.setViewportView(new JLabel(new ImageIcon(currentHisto.createHistogram())));
    } else {
      this.imageDisplay.setViewportView(new JLabel(new ImageIcon()));
    }
  }

  @Override
  public void alert(String msg) {
    new AlertState(msg);

  }

  @Override
  public void setHistogram(ImageHistogram histo) {
    this.histo = histo;
  }

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
    }  }

  @Override
  public void setListener(ActionListener listener) {
    this.listener = listener;
    this.setListeners();
  }

  private void setListeners() {
    blurBtn.addActionListener(listener);
    sharpenBtn.addActionListener(listener);
    sepiaBtn.addActionListener(listener);
    greyscaleBtn.addActionListener(listener);
    loadBtn.addActionListener(listener);
    saveBtn.addActionListener(listener);
    setCurBtn.addActionListener(listener);
    toggleVisBtn.addActionListener(listener);
  }

  private void createUIComponents() {
    this.imageDisplay = new JScrollPane();
  }
}
