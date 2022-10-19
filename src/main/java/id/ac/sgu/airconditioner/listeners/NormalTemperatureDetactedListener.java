package id.ac.sgu.airconditioner.listeners;

import id.ac.sgu.airconditioner.acinterface.IAirConditioner;
import id.ac.sgu.airconditioner.enums.AirConditionerPowerEnum;

public class NormalTemperatureDetactedListener implements ACEventListener {

    AirConditionerPowerEnum airConditionerPower;
    boolean airConditionerStatus;

    @Override
    public void update(String eventType, double temperature, IAirConditioner airConditioner) {
        if (temperature < 27 && temperature >= 23) {
            airConditionerPower = AirConditionerPowerEnum.LOW;
            airConditionerStatus = true;
        } else {
            airConditionerPower = AirConditionerPowerEnum.OFF;
            airConditionerStatus = false;
        }

        airConditioner.setAirConditionerStatus(airConditionerStatus,
                airConditionerPower);
    }

}
