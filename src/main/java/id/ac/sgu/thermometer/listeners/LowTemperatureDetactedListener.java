package id.ac.sgu.thermometer.listeners;

public class LowTemperatureDetactedListener implements EventListener {

    @Override
    public void update(String eventType, double file) {
        System.out.println("temperature high ");
    }
}