package view.dialogs;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import view.Dialog;

public class DownscaleState extends JDialog implements Dialog {
  private JPanel contentPane;
  private JButton buttonOK;
  private JButton buttonCancel;
  private JTextArea textAreaHeight;
  private JTextField widthField;
  private JTextArea textAreaWidth;
  private JTextField heightField;
  private List<String> result;

  public DownscaleState() {
    setContentPane(contentPane);
    setModal(true);
    setResizable(false);
    getRootPane().setDefaultButton(buttonOK);
    result = new ArrayList<>();

    buttonOK.addActionListener(e -> inputOK());
  }

  @Override
  public List<String> getState() {
    if (this.result != null) {
      return this.result;
    }
    return null;
  }

  private void inputOK() {
    this.result.add(this.widthField.getText());
    this.result.add(this.heightField.getText());
    dispose();
  }

  private void inputCancel() {
    this.result = null;
    dispose();
  }
}
