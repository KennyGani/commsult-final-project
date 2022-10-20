package id.ac.sgu.window.dtooutput;

public class WindowDto {
    private final boolean isRaining;
    private final boolean isHeavyWind;
    private final boolean isNight;

    public WindowDto(boolean isRaining, boolean isHeavyWind, boolean isNight) {
        this.isRaining = isRaining;
        this.isHeavyWind = isHeavyWind;
        this.isNight = isNight;
    }

    public boolean getIsRaining() {
        return this.isRaining;
    }

    public boolean getIsHeavyWind() {
        return this.isHeavyWind;
    }

    public boolean getIsNight() {
        return this.isNight;
    }
}
