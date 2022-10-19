package id.ac.sgu.airconditioner.handlers;

import id.ac.sgu.airconditioner.AirConditioner;
import id.ac.sgu.airconditioner.publisher.EventManager;

public class EventHandler {
    public EventManager events;
    private double temperature;

    public EventHandler() {
        this.events = new EventManager("lowTemperatureDetected", "highTemperatureDetected",
                "normalTemperatureDetected");
    }

    public void highTemperatureDetected(double temperature, AirConditioner airConditioner) {
        this.temperature = temperature;
        events.notify("highTemperatureDetected", this.temperature, airConditioner);
    }

    public void lowTemperatureDetected(double temperature, AirConditioner airConditioner) {
        this.temperature = temperature;
        events.notify("lowTemperatureDetected", this.temperature, airConditioner);
    }

    public void normalTemperatureDetected(double temperature, AirConditioner airConditioner) {
        this.temperature = temperature;
        events.notify("normalTemperatureDetected", this.temperature, airConditioner);
    }
}