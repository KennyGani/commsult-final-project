package id.ac.sgu.airconditioner.publisher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.ac.sgu.airconditioner.acinterface.IAirConditioner;
import id.ac.sgu.airconditioner.listeners.ACEventListener;

public class ACEventManager {
    Map<String, List<ACEventListener>> listeners = new HashMap<>();

    public ACEventManager(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(String eventType, ACEventListener listener) {
        List<ACEventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    public void notify(String eventType, double temperature, IAirConditioner airConditioner) {
        List<ACEventListener> users = listeners.get(eventType);
        for (ACEventListener listener : users) {
            listener.update(eventType, temperature, airConditioner);
        }
    }
}