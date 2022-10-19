package id.ac.sgu.heather;

import id.ac.sgu.heather.heatherinterface.IHeather;
import id.ac.sgu.heather.enums.HeatherPowerEnum;

public class Heather implements IHeather {
    private boolean status;
    private HeatherPowerEnum heatherPower;

    @Override
    public void setHeatherStatus(boolean status, HeatherPowerEnum power) {
        this.status = status;
        this.heatherPower = power;
    }

    @Override
    public boolean getHeatherStatus() {
        return this.status;
    }

    @Override
    public HeatherPowerEnum getHeatherPowerStatus() {
        return this.heatherPower;
    }
}
