package view.dialogs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import view.Dialog;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JFileChooser;

import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Represents the loading state of a GUI.
 */
public class LoadState extends JDialog implements Dialog {
  private JPanel contentPane;
  private final List<String> res = new ArrayList<>();

  /**
   * A Constructor for a LoadState object and initializes the file chooser of a txt or img file.
   */
  public LoadState() {
    JFileChooser fileChooser = new JFileChooser();

    fileChooser.setPreferredSize(contentPane.getPreferredSize());
    setContentPane(contentPane);
    setModal(true);
    setResizable(false);

    contentPane.add(fileChooser);
    fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
    fileChooser.setAcceptAllFileFilterUsed(false);
    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image", "jpg", "png", "ppm",
            "jpeg"));
    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("State", "txt"));
    res.add(fileChooser.getFileFilter().getDescription());

    int result = fileChooser.showOpenDialog(this.contentPane);

    if (result == JFileChooser.APPROVE_OPTION) {
      System.out.println("File loaded...");
      res.add(fileChooser.getSelectedFile().getAbsolutePath());
    } else if (result == JFileChooser.CANCEL_OPTION) {
      System.out.println("No file loaded!");
    }
  }

  @Override
  public List<String> getState() {
    return new ArrayList<>(this.res);
  }

  private void createUIComponents() {
    this.contentPane = new JPanel();
  }
}
