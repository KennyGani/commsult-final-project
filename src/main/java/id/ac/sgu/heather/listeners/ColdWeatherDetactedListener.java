package id.ac.sgu.heather.listeners;

import id.ac.sgu.heather.heatherinterface.IHeather;
import id.ac.sgu.heather.enums.HeatherPowerEnum;

public class ColdWeatherDetactedListener implements HeatherEventListener {

    HeatherPowerEnum heatherPower;
    boolean heatherStatus;

    @Override
    public void update(String eventType, IHeather Heather) {

        heatherPower = HeatherPowerEnum.ON;
        heatherStatus = false;

        Heather.setHeatherStatus(heatherStatus, heatherPower);
    }
}