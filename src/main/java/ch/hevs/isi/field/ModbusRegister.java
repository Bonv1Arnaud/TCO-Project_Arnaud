package ch.hevs.isi.field;

import ch.hevs.isi.core.DataPoint;

import java.util.HashMap;
import java.util.Map;

public abstract class ModbusRegister {
    private int regAddress;
    private static Map<DataPoint, ModbusRegister> map = new HashMap<>();

    public static ModbusRegister getRegisterFromDataPoint(DataPoint dp){
        return map.get(dp);
    }
    protected int getAddress(){
        return regAddress;
    }
    protected void setAddress(int address){
        regAddress = address;
    }
    protected void setRegister (DataPoint dp){
        map.put(dp, this);                          // Set datapoint in the map
    }

    public abstract void read();
    public abstract void write();

}
