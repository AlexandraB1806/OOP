package lab13.task4;

import java.util.EnumSet;

public class ConsoleLogger extends LoggerBase{
    ConsoleLogger(EnumSet<LogLevel> logLevel) {
        super(logLevel);
    }

    @Override
    protected void writeMessage(String message) {
        System.out.println( "[Console] " + message);
    }
}
