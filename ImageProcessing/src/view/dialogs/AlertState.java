package view.dialogs;

import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 * Represents the alert state of a certain condition.
 */
public class AlertState extends JDialog {
  private JPanel contentPane;
  private JButton buttonCancel;
  private JTextPane textPane1;
  private JTextPane alertTextPane;

  /**
   * A constructor for an alert state object.
   *
   * @param message String error message.
   */
  public AlertState(String message) {
    this.contentPane = new JPanel();
    this.buttonCancel = new JButton();
    this.textPane1 = new JTextPane();
    this.alertTextPane = new JTextPane();
    setContentPane(contentPane);
    setModal(true);
    setResizable(false);
    getRootPane().setDefaultButton(buttonCancel);
    textPane1.setText(message);

    buttonCancel.addActionListener(e -> onCancel());

    // call onCancel() when cross is clicked
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        onCancel();
      }
    });

    // call onCancel() on ESCAPE
    contentPane.registerKeyboardAction(e -> onCancel(),
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
            JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  private void onCancel() {
    dispose();
  }
}
