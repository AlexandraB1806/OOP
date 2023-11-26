package lab10.main;

import lab10.commands.*;

import java.util.Deque;
import java.util.LinkedList;

/**
 * The layer between the client and the commands that need to be executed on the receivers (DiagramCanvas and DiagramComponent).
 * <br>
 * It is independent of the subtypes of commands, it just receives a command, runs it and implements a redo/undo mechanism.
 */
public class Invoker {
    Deque<DrawCommand> commandsHistory = new LinkedList<>();
    Deque<DrawCommand> undoHistory = new LinkedList<>();

    /**
     * Clear up all the used resources, start fresh :D
     */
    public void restart() {
        commandsHistory.clear();
        undoHistory.clear();
    }

    /**
     * Executes a given command
     * @param command
     */
    public void execute(DrawCommand command) {
        commandsHistory.push(command);
        command.execute();
    }

    /**
     * Undo the latest command
     */
    public void undo() {
        // Check if there were given commands
        if (commandsHistory.isEmpty()) {
            return;
        }

        // The last given command in the history of all commands
        DrawCommand command = commandsHistory.pop();
        // The command will be added in a separated data structure that
        // keeps track of "undo" commands
        undoHistory.push(command);

        command.undo();
    }

    /**
     * Redo command previously undone. Cannot perform a redo after an execute, only after at least one undo.
     */
    public void redo() {
        // Check if there were given "undo" commands
        if (undoHistory.isEmpty()) {
            return;
        }

        // The last given command in the history of "undo" commands
        DrawCommand command = undoHistory.pop();
        // The command will be added in a separated data structure that
        // keeps track of all commands
        commandsHistory.push(command);
        
        command.undo();
        command.execute();
    }
}
