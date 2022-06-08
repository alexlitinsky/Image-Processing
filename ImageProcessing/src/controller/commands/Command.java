package controller.commands;

/**
 * An interface to represent a command and methods that a command must have.
 * Each command must have the go() method to actually run the command.
 * In most cases, go() just instantiates a modifier object of the right type
 * and applys the ImageModel the modifier creates to the controller's list of versions.
 */
public interface Command {
  void go();
}
