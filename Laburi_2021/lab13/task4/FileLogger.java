package lab13.task4;

import java.util.EnumSet;

public class FileLogger extends LoggerBase{
    FileLogger(EnumSet<LogLevel> logLevel) {
        super(logLevel);
    }

    @Override
    protected void writeMessage(String message) {
        System.out.println("[File] " + message);
    }
}
