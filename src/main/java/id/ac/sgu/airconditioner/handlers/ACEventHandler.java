package id.ac.sgu.airconditioner.handlers;

import id.ac.sgu.airconditioner.acinterface.IAirConditioner;
import id.ac.sgu.airconditioner.publisher.ACEventManager;

public class ACEventHandler {
    public ACEventManager events;
    private double temperature;

    public ACEventHandler() {
        this.events = new ACEventManager("lowTemperatureDetected", "highTemperatureDetected",
                "normalTemperatureDetected");
    }

    public void highTemperatureDetected(double temperature, IAirConditioner airConditioner) {
        this.temperature = temperature;
        events.notify("highTemperatureDetected", this.temperature, airConditioner);
    }

    public void lowTemperatureDetected(double temperature, IAirConditioner airConditioner) {
        this.temperature = temperature;
        events.notify("lowTemperatureDetected", this.temperature, airConditioner);
    }

    public void normalTemperatureDetected(double temperature, IAirConditioner airConditioner) {
        this.temperature = temperature;
        events.notify("normalTemperatureDetected", this.temperature, airConditioner);
    }
}