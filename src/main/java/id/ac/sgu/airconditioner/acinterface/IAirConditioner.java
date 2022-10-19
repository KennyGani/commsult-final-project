package id.ac.sgu.airconditioner.acinterface;

import id.ac.sgu.airconditioner.enums.AirConditionerPowerEnum;

public interface IAirConditioner {
    public void setAirConditionerStatus(boolean status, AirConditionerPowerEnum speed);

    public boolean getAirConditionerStatus();

    public AirConditionerPowerEnum getAirConditionerPowerStatus();
}
