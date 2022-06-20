package view;

import java.awt.event.ActionListener;
import java.util.List;

import histomodel.ImageHistogram;

public interface GraphicalView {

  void display();

  void refresh();

  void alert(String msg);

  void setHistogram(ImageHistogram histo);

  List<String> dialogHandler(String dialog);

  void setListener(ActionListener listener);
}
