package id.ac.sgu.airconditioner;

import id.ac.sgu.airconditioner.acinterface.IAirConditioner;
import id.ac.sgu.airconditioner.enums.AirConditionerPowerEnum;
import id.ac.sgu.airconditioner.model.AirConditionerModel;

public class AirConditionerController implements IAirConditioner {
    private AirConditionerModel airConditionerModel;

    public AirConditionerController(AirConditionerModel airConditionerModel) {
        this.airConditionerModel = airConditionerModel;
    }

    @Override
    public void setAirConditionerStatus(boolean status, AirConditionerPowerEnum power) {
        airConditionerModel.setAirConditionerStatus(status, power);
    }

    @Override
    public boolean getAirConditionerStatus() {
        return airConditionerModel.getAirConditionerStatus();
    }

    @Override
    public AirConditionerPowerEnum getAirConditionerPowerStatus() {
        return airConditionerModel.getAirConditionerPowerStatus();
    }
}
