package id.ac.sgu.airconditioner.listeners;

import id.ac.sgu.airconditioner.AirConditioner;
import id.ac.sgu.airconditioner.enums.AirConditionerPowerEnum;

public class LowTemperatureDetactedListener implements EventListener {

    AirConditionerPowerEnum airConditionerPower;

    @Override
    public void update(String eventType, double temperature, AirConditioner airConditioner) {

        airConditionerPower = AirConditionerPowerEnum.OFF;

        airConditioner.setAirConditionerStatus(false, airConditionerPower);
    }
}