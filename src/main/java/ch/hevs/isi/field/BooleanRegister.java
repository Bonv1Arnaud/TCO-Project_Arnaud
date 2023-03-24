package ch.hevs.isi.field;

import ch.hevs.isi.core.BooleanDataPoint;
import ch.hevs.isi.core.FloatDataPoint;

public class BooleanRegister extends ModbusRegister {
    private int adress;
    private static ModbusAccessor ma;
    private BooleanDataPoint dp;

    public BooleanRegister(int _adress, BooleanDataPoint _dp){
        adress = _adress;
        dp = _dp;
    }

    public void read(){
        boolean value = ModbusAccessor.getInstance().readBoolean(adress);   // Take the value on the modbus
        dp.setValue(value);                                                 // Put the value in the datapoint
    }

    public void write(){
        boolean value = (boolean) dp.getValue();                            // Take the value in the datapoint
        ModbusAccessor.getInstance().writeBoolean(adress,value);            // Put the value on the modbus
    }

    public static void setModbusAccessor (ModbusAccessor accessor){
        ma = accessor;
    }
}
