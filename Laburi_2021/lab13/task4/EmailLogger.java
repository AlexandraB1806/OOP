package lab13.task4;

import java.util.EnumSet;

public class EmailLogger extends LoggerBase{
    EmailLogger(EnumSet<LogLevel> logLevel) {
        super(logLevel);
    }

    @Override
    protected void writeMessage(String message) {
        System.out.println("[Email] " + message);

    }
}
