package id.ac.sgu.airconditioner.listeners;

import id.ac.sgu.airconditioner.acinterface.IAirConditioner;
import id.ac.sgu.airconditioner.enums.AirConditionerPowerEnum;

public class LowTemperatureDetactedListener implements ACEventListener {

    AirConditionerPowerEnum airConditionerPower;

    @Override
    public void update(String eventType, double temperature, IAirConditioner airConditioner) {

        airConditionerPower = AirConditionerPowerEnum.OFF;

        airConditioner.setAirConditionerStatus(false, airConditionerPower);
    }
}