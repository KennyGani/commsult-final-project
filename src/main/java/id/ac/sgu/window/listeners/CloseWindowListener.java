package id.ac.sgu.window.listeners;

import id.ac.sgu.window.windowinterface.IWindow;

public class CloseWindowListener implements WindowEventListener {

    boolean windowStatus;

    @Override
    public void update(String eventType, IWindow Window) {

        windowStatus = false;

        Window.setWindowStatus(windowStatus);
    }
}