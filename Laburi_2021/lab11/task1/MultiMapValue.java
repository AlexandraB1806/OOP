package lab11.task1;

import java.util.*;

public class MultiMapValue<K, V> {

    // The new multimap based on a hashmap
    private HashMap<K, List<V>> hashMap = new HashMap<>();

    public HashMap<K, List<V>> getHashMap() {
        return hashMap;
    }

    // Add a value to a given key which has a list of values associated
    public void add(K key, V value) {
        // The given key was not found in the multimap
        if (!hashMap.containsKey(key)) {
            // Create a list of values associated to the given key
            List<V> values = new ArrayList<>();
            // Add the value to the list of values
            values.add(value);
            // Complete the multimap
            hashMap.put(key, values);
        } else {
            // If the given key was found in the multimap,
            // obtain the list associated with the key
            // and add the value to this list of values
            hashMap.get(key).add(value);
        }
    }

    // Add the entire list of values to the key
    public void addAll(K key, List<V> values) {
        // The given key was not found in the multimap
        if (!hashMap.containsKey(key)) {
            // Use the above method
            hashMap.put(key, new ArrayList<>());
        }
        // If the given key was found in the multimap,
        // traverse the values and add them to the list
        // of values associated to this key
        for (V value : values) {
            hashMap.get(key).add(value);
        }
    }

    public void addAll(MultiMapValue<K, V> map) {
        // Traverse all the keys from the multimap
        for (K key : map.getHashMap().keySet()) {
            // For each key, complete its value list
            List<V> values = new ArrayList<>();
            for (V value : map.getHashMap().get(key)) {
                values.add(value);
            }
            // Update the current multimap
            this.getHashMap().put(key, values);
        }
    }

    public V getFirst(K key) {
        if (hashMap.get(key).isEmpty()) {
            // null if the list of values associated with the key is empty
            return null;
        } else {
            // First value
            return hashMap.get(key).get(0);
        }
    }

    public List<V> getValues(K key) {
        // If the key does not exist, the list of values associated
        // with this key does not exist
        if (!hashMap.containsKey(key)) {
            return null;
        }
        // The list of values associated with the key
        return hashMap.get(key);
    }

    // True if the key exists, or false contrary
    public boolean containsKey(K key) {
        return hashMap.containsKey(key);
    }

    // Checks if the multimap is empty
    public boolean isEmpty() {
        return hashMap.isEmpty();
    }

    // Delete the key and the list of values associated
    // with it
    public List<V> remove(K key) {
        return hashMap.remove(key);
    }

    // Size of multimap
    public int size() {
        return hashMap.size();
    }
}
