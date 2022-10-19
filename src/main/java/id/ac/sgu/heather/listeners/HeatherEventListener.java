package id.ac.sgu.heather.listeners;

import id.ac.sgu.heather.heatherinterface.IHeather;

public interface HeatherEventListener {
    void update(String eventType, IHeather airConditioner);
}
