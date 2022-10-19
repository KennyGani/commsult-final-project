package id.ac.sgu.airconditioner;

import id.ac.sgu.airconditioner.enums.AirConditionerPowerEnum;

public class AirConditioner {
    private boolean status;
    private AirConditionerPowerEnum airConditionerPower;

    public void setAirConditionerStatus(boolean status, AirConditionerPowerEnum speed) {
        this.status = status;
        this.airConditionerPower = speed;

    }

    public boolean getAirConditionerStatus() {
        return this.status;
    }

    public AirConditionerPowerEnum getAirConditionerPowerStatus() {
        return this.airConditionerPower;
    }
}
