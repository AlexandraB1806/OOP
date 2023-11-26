package lab9.main;

import lab9.dataprocessing.StepCountStrategy;
import lab9.dataprocessing.StepCountStrategyFactory;
import lab9.storage.*;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        System.out.println("Choose a strategy: type 'basic or 'filtered");
        Scanner scanner = new Scanner(System.in);
        String strategyType = scanner.next();
        scanner.close();

        DataRepository dataRepository = new DataRepository();

        StepCountStrategyFactory factory = new StepCountStrategyFactory();
        StepCountStrategy strategy = factory.createStrategy(strategyType, dataRepository);

        ConsoleLogger consoleLogger = new ConsoleLogger();
        ServerCommunicationController server = new ServerCommunicationController();
        DataAggregator dataAggregator = new DataAggregator(strategy);
        dataRepository.addObserver(consoleLogger);
        dataRepository.addObserver(server);
        dataRepository.addObserver(dataAggregator);

        long baseTimestamp = System.currentTimeMillis();

        dataRepository.addData(new SensorData(10, baseTimestamp + 1));
        System.out.println();

        dataRepository.addData(new SensorData(20, baseTimestamp + 2));
        System.out.println();

        dataRepository.addData(new SensorData(30, baseTimestamp + 3));
        System.out.println();

        dataRepository.addData(new SensorData(4000, baseTimestamp + 4));
        System.out.println();

        dataRepository.addData(new SensorData(50, baseTimestamp + 5));
        System.out.println();

        dataRepository.addData(new SensorData(-100, baseTimestamp + 6));
        System.out.println();

        dataRepository.addData(new SensorData(500, baseTimestamp + 600));
        System.out.println();
    }
}
