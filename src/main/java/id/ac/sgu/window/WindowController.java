package id.ac.sgu.window;

import id.ac.sgu.window.dtooutput.WindowDto;
import id.ac.sgu.window.handlers.WindowEventHandler;
import id.ac.sgu.window.listeners.CloseWindowListener;
import id.ac.sgu.window.listeners.OpenWindowListener;
import id.ac.sgu.window.windowinterface.IWindow;

public class WindowController {
    private WindowDto windowDto;
    private WindowEventHandler windowEventHandler = new WindowEventHandler();
    private IWindow windowModel;

    public WindowController(IWindow windowModel) {
        this.windowModel = windowModel;
    }

    private void setWindowDto(boolean isRaining, boolean isHeavyWind, boolean isNight) {
        this.windowDto = new WindowDto(isRaining, isHeavyWind, isNight);
    }

    public void setWindowController(boolean isRaining, boolean isHeavyWind, boolean isNight) {
        setWindowDto(isRaining, isHeavyWind, isNight);
        isWindowMustClose(isRaining, isHeavyWind, isNight);
    }

    public boolean getIsRaining() {
        return this.windowDto.getIsRaining();
    }

    public boolean getIsHeavyWind() {
        return this.windowDto.getIsHeavyWind();
    }

    public boolean getIsNight() {
        return this.windowDto.getIsNight();
    }

    public void isWindowMustClose(boolean isRaining, boolean isHeavyWind, boolean isNight) {
        windowEventHandler.events.subscribe("openWindowEvent", new OpenWindowListener());

        windowEventHandler.events.subscribe("closeWindowEvent", new CloseWindowListener());

        if (isRaining || isHeavyWind || isNight) {
            windowEventHandler.closeWindow(windowModel);
        } else {
            windowEventHandler.openWindow(windowModel);
        }

    }
}
