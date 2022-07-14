package view.dialogs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import view.Dialog;

/**
 * Represents the loading state of a GUI.
 */
public class LoadState extends JDialog implements Dialog {
  private final List<String> res = new ArrayList<>();
  private JPanel contentPane;

  /**
   * A Constructor for a LoadState object and initializes the file chooser of a txt or img file.
   */
  public LoadState() {
    JFileChooser fileChooser = new JFileChooser
            (FileSystemView.getFileSystemView().getHomeDirectory());
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image",
            "jpeg", "jpg", "png", "bmp", "gif");
    fileChooser.setFileFilter(filter);
    int fileOpen = fileChooser.showOpenDialog(LoadState.this);
    if (fileOpen == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
    }
    this.contentPane = new JPanel();
    fileChooser.setPreferredSize(contentPane.getPreferredSize());
    setContentPane(contentPane);
    setModal(true);
    setResizable(false);

    contentPane.add(fileChooser);
    fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
    fileChooser.setAcceptAllFileFilterUsed(false);
    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image",
            "jpg", "png", "ppm",
            "jpeg"));
    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("State",
            "txt"));
    res.add(fileChooser.getFileFilter().getDescription());
    res.add(String.valueOf(fileChooser.getSelectedFile()));
  }

  /**
   * Retrieves the results of the current load state.
   *
   * @return an array list representing the results
   */
  @Override
  public List<String> getState() {
    for (String s : res) {
      System.out.println(s);
    }
    return this.res;
  }
}
