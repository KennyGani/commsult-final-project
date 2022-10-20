package id.ac.sgu.window;

import id.ac.sgu.window.dtooutput.WindowDto;

public class WindowController {
    private WindowDto windowDto;

    private void setWindowDto(boolean isRaining, boolean isHeavyWind, boolean isNight) {
        this.windowDto = new WindowDto(isRaining, isHeavyWind, isNight);
    }

    public void setWindowController(boolean isRaining, boolean isHeavyWind, boolean isNight) {
        setWindowDto(isRaining, isHeavyWind, isNight);
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

}
