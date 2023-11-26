package lab9.dataprocessing;

import lab9.main.Utils;
import lab9.storage.DataRepository;
import lab9.storage.SensorData;

public class BasicStepCountStrategy implements StepCountStrategy {
	private DataRepository dataRepository;

	public BasicStepCountStrategy(DataRepository dataRepository) {
		this.dataRepository = dataRepository;
	}

	@Override
	public int getTotalSteps() {
		int steps = 0;
		for (SensorData sensor : dataRepository.getList()) {
			steps += sensor.getStepsCount();
		}
		return steps;
	}

	@Override
	public String getStrategyDescription() {
		return Utils.BASIC_STRATEGY;
	}
}
