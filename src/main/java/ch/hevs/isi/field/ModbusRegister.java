package ch.hevs.isi.field;

import ch.hevs.isi.core.DataPoint;
import ch.hevs.isi.core.DataPointListener;

import java.util.HashMap;
import java.util.Map;

/**
 * ModBusRegister is used to have a link between a datapoint and a modbus
 * adress. The class is extended in two other class (booleanRegister and
 * flatRegister).
 *
 * @autor Arnaud Bonvin
 * @version v1.0
 */
public abstract class ModbusRegister {
    // Register address on the modbus
    private int regAddress;

    // Singleton of the ModbusAccessor
    protected ModbusAccessor ma;

    // Map containing the datapoint linked to a modbus register
    protected static Map<DataPoint, ModbusRegister> map = new HashMap<>();

    /**
     * Use to find a register in the map of modbus register with a given datapoint
     * @param dp
     * Specific datapoint wich is used to find the modbus register
     * @return
     * Return a modbusregister with the given datapoint
     */
    public static ModbusRegister getRegisterFromDataPoint(DataPoint dp){
        return map.get(dp);
    }

    /**
     * Use to pool all the value in the modbus map
     */
    public static void poll() {
        for(ModbusRegister mr : map.values()){
            mr.read();
        }
    }

    /**
     * Use to return the address of the datapoint (internal getter)
     * @return
     * Register address on the modbus
     */
    protected int getAddress(){
        return regAddress;
    }

    /**
     * Use to set the address of the datapoint (internal setter)
     * @param address
     * Register address on the modbus
     */
    protected void setAddress(int address){
        regAddress = address;
    }

    /**
     * Use to set a datapoint in the modbus map -> used when we created a datapoint
     * @param dp
     * Datapoint that is set in the modbus map
     */
    protected void setRegister (DataPoint dp){
        map.put(dp, this);                          // Set datapoint in the map
    }

    /**
     * Use to read a value in the modbus map
     */
    public abstract void read();

    /**
     * Use to write a value in the modbus map
     */
    public abstract void write();

}
