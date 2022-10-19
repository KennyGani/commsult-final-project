package id.ac.sgu.heather.publisher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.ac.sgu.heather.heatherinterface.IHeather;
import id.ac.sgu.heather.listeners.HeatherEventListener;

public class HeatherEventManager {
    Map<String, List<HeatherEventListener>> listeners = new HashMap<>();

    public HeatherEventManager(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(String eventType, HeatherEventListener listener) {
        List<HeatherEventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    public void notify(String eventType, IHeather Heather) {
        List<HeatherEventListener> users = listeners.get(eventType);
        for (HeatherEventListener listener : users) {
            listener.update(eventType, Heather);
        }
    }
}