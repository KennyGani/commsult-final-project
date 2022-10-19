package id.ac.sgu.airconditioner.listeners;

import id.ac.sgu.airconditioner.AirConditioner;

public interface EventListener {
    void update(String eventType, double temperature, AirConditioner airConditioner);
}
