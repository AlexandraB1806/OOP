package lab9.storage;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Persists sensor data. Observable, its observers are notified when data is added it to.
 */
public class DataRepository extends Observable {
    private ArrayList<SensorData> list = new ArrayList<>();

    public ArrayList<SensorData> getList() {
        return list;
    }

    public void addData(SensorData dataRecord) {
        list.add(dataRecord);
        setChanged();
        notifyObservers(dataRecord);
    }
}
