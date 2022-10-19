package id.ac.sgu;
import java.util.Map;
import java.util.HashMap;

import java.time.LocalDateTime;

public class RandomSensorNumber {

    public Map<String, Object> getSensorRandomizeNumber() {
        Map<String, Object> map = new HashMap<>();

        Clock clock = new Clock();
        Thermometer thermometer = new Thermometer();
        Anemometer anemometer = new Anemometer();

        map.put("time", clock.getCurrentTime());
        map.put("thermoNumber", thermometer.getRandomThermometerNumber());
        map.put("anemoNumber", anemometer.getRandomAnemometerNumber());

        return map;
    }
}
