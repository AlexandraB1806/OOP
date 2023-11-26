package lab13.task4;

import java.util.EnumSet;

public abstract class LoggerBase {
    private EnumSet<LogLevel> logLevel;
    private LoggerBase next;

    LoggerBase(EnumSet<LogLevel> logLevel) {
        this.logLevel = logLevel;
    }

    public void setNext(LoggerBase next) {
        this.next = next;
    }

    protected abstract void writeMessage (String message);

    public void message (String message, LogLevel severity) {
        if (this.logLevel.contains(severity)) {
            writeMessage(message);
        }
        if (next != null) {
            next.message(message, severity);
        }
    }
}
