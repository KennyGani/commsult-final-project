package id.ac.sgu.heather.model;

import id.ac.sgu.heather.enums.HeatherPowerEnum;

public class HeatherModel {
    private boolean status;
    private HeatherPowerEnum heatherPower;

    public void setHeatherStatus(boolean status, HeatherPowerEnum power) {
        this.status = status;
        this.heatherPower = power;
    }

    public boolean getHeatherStatus() {
        return this.status;
    }

    public HeatherPowerEnum getHeatherPowerStatus() {
        return this.heatherPower;
    }
}
