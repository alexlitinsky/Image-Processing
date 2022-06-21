package view.dialogs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JFileChooser;

import javax.swing.filechooser.FileNameExtensionFilter;

import view.Dialog;

public class SaveState extends JDialog implements Dialog {

  private JPanel contentPane;
  private final List<String> res;

  /**
   * Creates a SaveState object which will render the file chooser for the user to folder to save
   * the image in as well as enter a file name.
   */
  public SaveState() {
    JFileChooser fileChooser = new JFileChooser();

    setContentPane(contentPane);
    setModal(true);
    setResizable(false);
    res = new ArrayList<>();

    contentPane.add(fileChooser);
    fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
    fileChooser.setAcceptAllFileFilterUsed(false);
    fileChooser.setFileFilter(new FileNameExtensionFilter("State", "txt"));

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
