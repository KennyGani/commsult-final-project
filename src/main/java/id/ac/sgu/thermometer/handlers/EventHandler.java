package id.ac.sgu.thermometer.handlers;

import id.ac.sgu.thermometer.publisher.EventManager;

public class EventHandler {
    public EventManager events;
    private double temperature;

    public EventHandler() {
        this.events = new EventManager("lowTemperatureDetected", "highTemperatureDetected",
                "normalTemperatureDetected");
    }

    public void highTemperatureDetected(double temperature) {
        this.temperature = temperature;
        events.notify("highTemperatureDetected", this.temperature);
    }

    public void lowTemperatureDetected(double temperature) {
        this.temperature = temperature;
        events.notify("lowTemperatureDetected", this.temperature);
    }

    public void normalTemperatureDetected(double temperature) {
        this.temperature = temperature;
        events.notify("normalTemperatureDetected", this.temperature);
    }
}