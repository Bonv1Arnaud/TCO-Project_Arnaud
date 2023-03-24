package ch.hevs.isi.field;

import java.util.TimerTask;

public class PollTask extends TimerTask {
    @Override
    public void run() {
        ModbusRegister.poll();
    }
}
