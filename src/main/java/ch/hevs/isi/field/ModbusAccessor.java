package ch.hevs.isi.field;

import ch.hevs.isi.utils.Utility;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.locator.BaseLocator;

public class ModbusAccessor {
    private int regAddress;
    private float newValue;
    private boolean _newValue;
    private static ModbusAccessor instance = null;
    private ModbusMaster modbusMaster;

    private ModbusAccessor() {
    }

    public static ModbusAccessor getInstance() {
        if (instance == null) {
            instance = new ModbusAccessor();
        }
        return instance;
    }

    public float readFloat(int regAddress) {
        try {
            // Returns a value from the modbus network according to the given locator information
            return (float) modbusMaster.getValue(BaseLocator.inputRegister(1, regAddress, DataType.FOUR_BYTE_FLOAT)); //BaseLocator is used for the adress
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
            modbusMaster.setValue(BaseLocator.holdingRegister(1, regAddress, DataType.FOUR_BYTE_FLOAT), newValue);
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
            return modbusMaster.getValue(BaseLocator.coilStatus(1, _regAddress));
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
            modbusMaster.setValue(BaseLocator.coilStatus(1, _regAddress), _newValue);
        } catch(ModbusTransportException e) {
            // If there was an IO error or other technical failure while sending the message
            throw new RuntimeException(e);
        } catch(ErrorResponseException e) {
            //  If the response returned from the slave was an exception.
            throw new RuntimeException( e);
        }
    }


    public void connect(String ipAddress, int port) {
        ModbusFactory modbusFactory = new ModbusFactory();
        IpParameters ip = new IpParameters();
        ip.setHost(ipAddress);
        ip.setPort(port);
        modbusMaster = modbusFactory.createTcpMaster(ip, true);
        while (true) {
            try {
                modbusMaster.init();
                break;
            } catch (ModbusInitException e) {
                System.out.println("Unable to connect to the server");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            continue;
        }
    }

    public static void main(String[] args) {
        ModbusAccessor ma = new ModbusAccessor();
        ma.connect("localhost", 1502);

        while (true){
            System.out.println("Grid U = " + ma.readFloat(89));
            System.out.println("Solar SW= " + ma.readBoolean(609));
            System.out.println("Wind SW= " + ma.readBoolean(613));
            ma.writeBoolean(401,true);

            Utility.waitSomeTime(1000);
        }

    }
}
