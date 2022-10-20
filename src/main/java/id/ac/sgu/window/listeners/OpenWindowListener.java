package id.ac.sgu.window.listeners;

import id.ac.sgu.window.windowinterface.IWindow;

public class OpenWindowListener implements WindowEventListener {

    boolean windowStatus;

    @Override
    public void update(String eventType, IWindow Window) {

        windowStatus = true;

        Window.setWindowStatus(windowStatus);
    }
}