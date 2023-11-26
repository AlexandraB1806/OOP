package lab10.main;

import lab10.commands.*;
import lab10.diagram.DiagramCanvas;

/**
 * Receives commands in clear text from the user and transforms them in DrawCommand objects. It uses the Invoker to
 * execute the given commands.
 */
public class Client {

    private Invoker invoker;
    private DiagramCanvas diagramCanvas;

    Client() {
        invoker = new Invoker();
        diagramCanvas = new DiagramCanvas();
    }

    public void showDiagram() {
        diagramCanvas.show();
    }

    public void newDiagram() {
        diagramCanvas = new DiagramCanvas();
        invoker.restart();
    }

    public void executeAction(String commandName, String ...args) {
        DrawCommand command;
        try {
            CommandType commandType = CommandType.fromString(commandName);
            command = getCommand(commandType, args);
            if (command == null) {
                throw new IllegalArgumentException();
            }

        } catch(IllegalArgumentException ex) {
            System.out.println("Invalid command: " + commandName);
            System.out.println("Available commands:");
            for (CommandType type : CommandType.values()) {
                System.out.println("\t- " + type.text);
            }
            return;
        }

        // Execute the action
        invoker.execute(command);
    }

    private DrawCommand getCommand(CommandType type, String ...args) throws IllegalArgumentException {
        DrawCommand command;

        switch (type) {
            case DRAW_RECTANGLE -> {
                try {
                    command = new DrawRectangle(diagramCanvas);
                } catch (Exception NumberFormatException) {
                    throw new IllegalArgumentException();
                }
            }
            case RESIZE -> {
                try {
                    command = new Resize(diagramCanvas,
                            Integer.parseInt(args[0]),
                            Integer.parseInt(args[1]));
                } catch (Exception NumberFormatException) {
                    throw new IllegalArgumentException();
                }
            }
            case CHANGE_COLOR -> {
                try {
                    command = new ChangeColor(diagramCanvas,
                            Integer.parseInt(args[0]), args[1]);
                } catch (Exception NumberFormatException) {
                    throw new IllegalArgumentException();
                }
            }
            case CHANGE_TEXT -> {
                try {
                    command = new ChangeText(diagramCanvas,
                            Integer.parseInt(args[0]), args[1]);
                } catch (Exception NumberFormatException) {
                    throw new IllegalArgumentException();
                }
            }
            case CONNECT -> {
                try {
                    command = new ConnectComponents(diagramCanvas,
                            Integer.parseInt(args[0]),
                            Integer.parseInt(args[1]));
                } catch (Exception NumberFormatException) {
                    throw new IllegalArgumentException();
                }
            }
            default -> {
                return null;
            }
        }
        return command;
    }

    public void undo(){
        invoker.undo();
    }

    public void redo() {
        invoker.redo();
    }
}
