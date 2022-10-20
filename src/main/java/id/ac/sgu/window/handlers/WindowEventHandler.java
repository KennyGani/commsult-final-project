package id.ac.sgu.window.handlers;

import id.ac.sgu.window.windowinterface.IWindow;
import id.ac.sgu.window.publisher.WindowEventManager;

public class WindowEventHandler {
    public WindowEventManager events;

    public WindowEventHandler() {
        this.events = new WindowEventManager("closeWindowEvent", "openWindowEvent");
    }

    public void openWindow(IWindow airConditioner) {
        events.notify("openWindowEvent", airConditioner);
    }

    public void closeWindow(IWindow airConditioner) {
        events.notify("closeWindowEvent", airConditioner);
    }
}