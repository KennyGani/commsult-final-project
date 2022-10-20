package id.ac.sgu.heather;

import id.ac.sgu.heather.heatherinterface.IHeather;
import id.ac.sgu.heather.model.HeatherModel;
import id.ac.sgu.heather.enums.HeatherPowerEnum;

public class HeatherController implements IHeather {
    private HeatherModel heatherModel;

    public HeatherController(HeatherModel heatherModel) {
        this.heatherModel = heatherModel;
    }

    @Override
    public void setHeatherStatus(boolean status, HeatherPowerEnum power) {
        heatherModel.setHeatherStatus(status, power);
    }

    @Override
    public boolean getHeatherStatus() {
        return heatherModel.getHeatherStatus();
    }

    @Override
    public HeatherPowerEnum getHeatherPowerStatus() {
        return heatherModel.getHeatherPowerStatus();
    }

}
