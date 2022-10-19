package id.ac.sgu.airconditioner;

import id.ac.sgu.airconditioner.acinterface.IAirConditioner;
import id.ac.sgu.airconditioner.enums.AirConditionerPowerEnum;

public class AirConditioner implements IAirConditioner {
    private boolean status;
    private AirConditionerPowerEnum airConditionerPower;

    @Override
    public void setAirConditionerStatus(boolean status, AirConditionerPowerEnum power) {
        this.status = status;
        this.airConditionerPower = power;
    }

    @Override
    public boolean getAirConditionerStatus() {
        return this.status;
    }

    @Override
    public AirConditionerPowerEnum getAirConditionerPowerStatus() {
        return this.airConditionerPower;
    }
}
