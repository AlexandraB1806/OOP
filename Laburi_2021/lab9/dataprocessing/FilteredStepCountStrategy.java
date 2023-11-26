package lab9.dataprocessing;

import lab9.main.Utils;
import lab9.storage.DataRepository;
import lab9.storage.SensorData;

import java.util.concurrent.TimeUnit;

public class FilteredStepCountStrategy implements StepCountStrategy {
	private static final int MAX_DIFF_STEPS_CONSECUTIVE_RECORDS = 1000;
	private static final long TIME_FOR_MAX_DIFF = TimeUnit.SECONDS.toMillis(60);

	private DataRepository dataRepository;

	public FilteredStepCountStrategy(DataRepository dataRepository) {
		this.dataRepository = dataRepository;
	}

	@Override
	public int getTotalSteps() {
		int steps = 0;
		for (SensorData sensor : dataRepository.getList()) {
			if (sensor.getStepsCount() > 0) {
				if (sensor.getTimestamp() < TIME_FOR_MAX_DIFF) {
					if (sensor.getStepsCount() < MAX_DIFF_STEPS_CONSECUTIVE_RECORDS) {
						steps += sensor.getStepsCount();
					}
				} else {
					steps += sensor.getStepsCount();
				}
			}
		}
		return steps;
	}

	@Override
	public String getStrategyDescription() {
		return Utils.FILTERED_STRATEGY;
	}
}
