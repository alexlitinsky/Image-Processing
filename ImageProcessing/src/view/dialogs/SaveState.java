package view.dialogs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JFileChooser;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import view.Dialog;

public class SaveState extends JDialog implements Dialog {

  private JPanel contentPane;
  private final List<String> res;

  /**
   * Creates a SaveState object which will render the file chooser for the user to folder to save
   * the image in as well as enter a file name.
   */
  public SaveState() {
    JFileChooser fileChooser = new JFileChooser
            (FileSystemView.getFileSystemView().getHomeDirectory());
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image",
            "jpeg", "jpg", "png", "bmp", "gif");
    fileChooser.setFileFilter(filter);
    setContentPane(contentPane);
    setModal(true);
    setResizable(false);
    res = new ArrayList<>();

    contentPane.add(fileChooser);

    int result = fileChooser.showSaveDialog(this.contentPane);

    if (result == JFileChooser.APPROVE_OPTION) {
      System.out.println("File saved...");
      res.add(fileChooser.getSelectedFile().getAbsolutePath());
    } else if (result == JFileChooser.CANCEL_OPTION) {
      System.out.println("State not saved!");
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
