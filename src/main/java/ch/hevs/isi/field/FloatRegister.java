package ch.hevs.isi.field;

import ch.hevs.isi.core.DataPoint;
import ch.hevs.isi.core.FloatDataPoint;

public class FloatRegister {

    private int adress;
    private FloatDataPoint dp;

    public FloatRegister(int _adress, FloatDataPoint _dp){
        adress = _adress;
        dp = _dp;
    }

    public void read(){
        float value = ModbusAccessor.getInstance().readFloat(adress);       // Take the value on the modbus
        dp.setValue(value);                                                 // Put the value in the datapoint
    }

    public void write(){
        float value = (float) dp.getValue();                                // Take the value in the datapoint
        ModbusAccessor.getInstance().writeFloat(adress,value);              // Put the value on the modbus
    }

    public FloatRegister getRegisterFromDataPoint(FloatDataPoint fdp){
        FloatRegister fr = null;

        return fr;
    }


    //methode getRegisterFromDataPoint
}
