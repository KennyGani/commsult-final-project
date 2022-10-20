package id.ac.sgu.airconditioner.model;

import id.ac.sgu.airconditioner.enums.AirConditionerPowerEnum;

public class AirConditionerModel {
    private boolean status;
    private AirConditionerPowerEnum airConditionerPower;

    public void setAirConditionerStatus(boolean status, AirConditionerPowerEnum power) {
        this.status = status;
        this.airConditionerPower = power;
    }

    public boolean getAirConditionerStatus() {
        return this.status;
    }

    public AirConditionerPowerEnum getAirConditionerPowerStatus() {
        return this.airConditionerPower;
    }
}
