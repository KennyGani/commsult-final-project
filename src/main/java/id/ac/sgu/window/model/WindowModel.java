package id.ac.sgu.window.model;

public class WindowModel {
    private boolean isWindowOpen;

    public void setWindowStatus(boolean status) {
        this.isWindowOpen = status;
    }

    public boolean getWindowStatus() {
        return this.isWindowOpen;
    }
}
