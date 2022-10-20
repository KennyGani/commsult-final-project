package id.ac.sgu.clock;

import java.time.LocalTime;

public class Clock {
    public LocalTime getCurrentTime() {

        LocalTime time = LocalTime.now();

        return time;
    }
}
