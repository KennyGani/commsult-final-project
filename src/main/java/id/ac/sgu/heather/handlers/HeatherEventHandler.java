package id.ac.sgu.heather.handlers;

import id.ac.sgu.heather.heatherinterface.IHeather;
import id.ac.sgu.heather.publisher.HeatherEventManager;

public class HeatherEventHandler {
    public HeatherEventManager events;

    public HeatherEventHandler() {
        this.events = new HeatherEventManager("hotWeatherDetected", "coldWeatherDetected");
    }

    public void hotWeatherDetected(IHeather Heather) {
        events.notify("hotWeatherDetected", Heather);
    }

    public void coldWeatherDetected(IHeather Heather) {
        events.notify("coldWeatherDetected", Heather);
    }

}