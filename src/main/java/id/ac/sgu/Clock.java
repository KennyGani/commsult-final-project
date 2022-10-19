package id.ac.sgu;

import java.time.LocalDateTime;

public class Clock {

    public LocalDateTime getCurrentTime() {

        LocalDateTime time = LocalDateTime.now();

        return time;
    }
}
