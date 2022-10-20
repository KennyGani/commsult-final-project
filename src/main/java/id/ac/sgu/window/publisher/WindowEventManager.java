package id.ac.sgu.window.publisher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.ac.sgu.window.windowinterface.IWindow;
import id.ac.sgu.window.listeners.WindowEventListener;

public class WindowEventManager {
    Map<String, List<WindowEventListener>> listeners = new HashMap<>();

    public WindowEventManager(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(String eventType, WindowEventListener listener) {
        List<WindowEventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    public void notify(String eventType, IWindow Window) {
        List<WindowEventListener> users = listeners.get(eventType);
        for (WindowEventListener listener : users) {
            listener.update(eventType, Window);
        }
    }
}