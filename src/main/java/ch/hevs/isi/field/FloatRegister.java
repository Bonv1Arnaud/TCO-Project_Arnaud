package ch.hevs.isi.field;

import ch.hevs.isi.core.FloatDataPoint;

/**
 * Float register is used to have a link between a float datapoint and a modbus
 * address. The class is the extension of ModbusRegister.
 *
 * @author Arnaud Bonvin
 * @version v1.0
 */
public class FloatRegister extends ModbusRegister{
    // Range of value possible
    private float range;

    // Offset of value
    private float offset;

    // Datapoint of the register
    private FloatDataPoint dp;

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
     * @param range
     * Range of value possible
     * @param offset
     * Gap between zero and the zero of the value
     */
    public FloatRegister(int _adress, String _label, boolean _isOutput, float range, float offset) {
        dp = new FloatDataPoint(_label, _isOutput);                             // Create a boolean datapoint
        setAddress(_adress);                                                    // Link the modbus adress to datapoint
        setRegister(dp);                                                        // Add the datapoint to the register
        ma = ModbusAccessor.getInstance();                                      // Get (and create if needed) a modbus accessor

        this.range = range;
        this.offset = offset;
    }

    /**
     * Use to read a value in the modbus map
     */
    public void read() {
        dp.setValue((float)offset + range * ma.readFloat(getAddress()));        // Get the value by the adress + linearization
        System.out.println(dp);                                                 // Display value and label of DP
    }

    /**
     * Use to write a value in the modbus map
     */
    public void write() {
        ma.writeFloat(getAddress(), (dp.getValue() - offset) / range);  // Write the value in a register
    }

}
