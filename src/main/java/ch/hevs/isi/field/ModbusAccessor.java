package ch.hevs.isi.field;

import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.locator.BaseLocator;

public class ModbusAccessor {
    private int regAddress;
    private float newValue;
    private boolean _newValue;
     private static ModbusAccessor instance = null;
     private ModbusMaster topMaster;

    private ModbusAccessor() {
    }

public static ModbusAccessor getInstance()
    {if( instance == null)
    {
       instance = new ModbusAccessor();
    }
    return instance; }

    public float readFloat(int regAddress) {
        try {
            // Returns a value from the modbus network according to the given locator information
            return (float) topMaster.getValue(BaseLocator.inputRegister(1, regAddress, DataType.FOUR_BYTE_FLOAT)); //BaseLocator is used for the adress
        } catch(ModbusTransportException e) {
            // If there was an IO error or other technical failure while sending the message
            throw new RuntimeException(e);
        } catch(ErrorResponseException e) {
            //  If the response returned from the slave was an exception.
            throw new RuntimeException( e);
        }
    }

    public void writeFloat(int regAddress, float newValue) {
        try {
            topMaster.setValue(BaseLocator.holdingRegister(1, regAddress, DataType.FOUR_BYTE_FLOAT), newValue);
        } catch(ModbusTransportException e) {
            // If there was an IO error or other technical failure while sending the message
            throw new RuntimeException(e);
        } catch(ErrorResponseException e) {
            //  If the response returned from the slave was an exception.
            throw new RuntimeException( e);
        }
    }

    public boolean readBoolean(int _regAddress) {
        try {
            // Returns a value from the modbus network according to the given locator information
            return topMaster.getValue(BaseLocator.coilStatus(1, _regAddress));
        } catch(ModbusTransportException e) {
            // If there was an IO error or other technical failure while sending the message
            throw new RuntimeException(e);
        } catch(ErrorResponseException e) {
            //  If the response returned from the slave was an exception.
            throw new RuntimeException( e);
        }
    }

    public void writeBoolean(int _regAddress, boolean _newValue) {
        try {
            topMaster.setValue(BaseLocator.coilStatus(1, _regAddress), _newValue);
        } catch(ModbusTransportException e) {
            // If there was an IO error or other technical failure while sending the message
            throw new RuntimeException(e);
        } catch(ErrorResponseException e) {
            //  If the response returned from the slave was an exception.
            throw new RuntimeException( e);
        }
    }
}
