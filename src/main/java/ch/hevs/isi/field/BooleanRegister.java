package ch.hevs.isi.field;

import ch.hevs.isi.core.BooleanDataPoint;

/**
 * Boolean register is used to have a link between a boolean datapoint and a modbus
 * address. The class is the extension of ModbusRegister.
 *
 * @author Arnaud Bonvin
 * @version v1.0
 */
public class BooleanRegister extends ModbusRegister {
    // Datapoint of the register
    private BooleanDataPoint dp;

    /**
     * <b>Constructor</b><br>
     * Use to create an object of float register. Notice that float register is an
     * extended classe of modbus register.
     * @param _adress
     * Address on the modbus
     * @param _label
     * Name of the value
     * @param _isOutput
     * Is activ if we can write and read onto
     */
    public BooleanRegister(int _adress, String _label, boolean _isOutput){
        dp = new BooleanDataPoint(_label, _isOutput);                       // Create a boolean datapoint
        setAddress(_adress);                                                // Link the modbus adress to datapoint
        setRegister(dp);                                                    // Add the datapoint to the register
        ma = ModbusAccessor.getInstance();                                  // Get (and create if needed) a modbus accessor
    }

    /**
     * Use to read a value in the modbus map
     */
    public void read(){
        dp.setValue(ma.readBoolean(getAddress()));                          // Read the value in the register
        System.out.println("READ BOOLEAN - " + dp.getLabel() + "_" + dp.getValue());
    }

    /**
     * Use to write a value in the modbus map
     */
    public void write(){
        ma.writeBoolean(getAddress(), dp.getValue());                       // Write value in the register
    }

}
