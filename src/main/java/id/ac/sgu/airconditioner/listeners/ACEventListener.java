package id.ac.sgu.airconditioner.listeners;

import id.ac.sgu.airconditioner.acinterface.IAirConditioner;

public interface ACEventListener {
    void update(String eventType, double temperature, IAirConditioner airConditioner);
}
