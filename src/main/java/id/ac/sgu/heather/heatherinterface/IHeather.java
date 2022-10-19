package id.ac.sgu.heather.heatherinterface;

import id.ac.sgu.heather.enums.HeatherPowerEnum;

public interface IHeather {
    public void setHeatherStatus(boolean status, HeatherPowerEnum speed);

    public boolean getHeatherStatus();

    public HeatherPowerEnum getHeatherPowerStatus();
}
