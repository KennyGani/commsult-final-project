package id.ac.sgu.window.model;

import id.ac.sgu.window.windowinterface.IWindow;

public class WindowModel implements IWindow {
    private boolean isWindowOpen;

    public void setWindowStatus(boolean status) {
        this.isWindowOpen = status;
    }

    public boolean getWindowStatus() {
        return this.isWindowOpen;
    }
}
