package ch.hevs.isi.field;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.ip.tcp.TcpMaster;
import com.serotonin.modbus4j.locator.BaseLocator;


/**
 * Modbus accessor is used to communicate with the modbus (on minecraft),
 * and implement all methods needed to the communication.
 *
 * @author Arnaud Bonvin
 * @version v1.0
 */
public class ModbusAccessor {
    // Register address on the modbus
    private int regAddress;

    // Instance of ModBusAccessor
    private static ModbusAccessor instance = null;

    // Instance of Tcpmaster
    private TcpMaster master;

    /**
     * <b>Consstrutor</b><br>
     * Use to create an instance of the modbus accessor. A useful class
     * that estoablish the connection with the modbus and all request
     * that are needed to communicate.
     */
    private ModbusAccessor() { }

    /**
     * Use to get the instance of the singleton, and if it isn't
     * already created, create the singleton.
     * @return
     * The instance of the singleton
     */
    public static ModbusAccessor getInstance() {
        if (instance == null) {
            instance = new ModbusAccessor();
        }
        return instance;
    }

    /**
     * Use to read a float on the modbus with a modbus address given in parameter.
     * @param regAddress
     * Modbus address of the point that we wanted to read the value
     * @return
     * Value (float) of the modbus point
     */
    public float readFloat(int regAddress) {
        try {                                       // Returns a value from the modbus network according to the given locator information
            return (float) master.getValue(BaseLocator.inputRegister(1, regAddress, DataType.FOUR_BYTE_FLOAT)); //BaseLocator is used for the adress
        } catch(ModbusTransportException e) {       // If there was an IO error or other technical failure while sending the message
            throw new RuntimeException(e);
        } catch(ErrorResponseException e) {         //  If the response returned from the slave was an exception.
            throw new RuntimeException( e);
        }
    }

    /**
     * Use to write a float on the modbus with a modbus address given in parameter.
     * @param regAddress
     * Modbus address of the point that we wanted to read the value
     * @param newValue
     * Value (float) of the modbus point
     */
    public void writeFloat(int regAddress, float newValue) {
        try {
            master.setValue(BaseLocator.holdingRegister(1, regAddress, DataType.FOUR_BYTE_FLOAT), newValue);
        } catch(ModbusTransportException e) {       // If there was an IO error or other technical failure while sending the message
            throw new RuntimeException(e);
        } catch(ErrorResponseException e) {         //  If the response returned from the slave was an exception.
            throw new RuntimeException(e);
        }
    }

    /**
     * Use to read a boolean on the modbus with a modbus address given in parameter.
     * @param _regAddress
     * Modbus address of the point that we wanted to read the value
     * @return
     * Value (boolean) of the modbus point
     */
    public boolean readBoolean(int _regAddress) {
        try {                                       // Returns a value from the modbus network according to the given locator information
            return master.getValue(BaseLocator.coilStatus(1, _regAddress));
        } catch(ModbusTransportException e) {       // If there was an IO error or other technical failure while sending the message
            throw new RuntimeException(e);
        } catch(ErrorResponseException e) {         //  If the response returned from the slave was an exception.
            throw new RuntimeException(e);
        }
    }

    /**
     * Use to write a boolean on the modbus with a modbus address given in parameter.
     * @param _regAddress
     * Modbus address of the point that we wanted to read the value
     * @param _newValue
     * Value (boolean) of the modbus point
     */
    public void writeBoolean(int _regAddress, boolean _newValue) {
        try {
            master.setValue(BaseLocator.coilStatus(1, _regAddress), _newValue);
        } catch(ModbusTransportException e) {       // If there was an IO error or other technical failure while sending the message
            throw new RuntimeException(e);
        } catch(ErrorResponseException e) {         //  If the response returned from the slave was an exception.
            throw new RuntimeException(e);
        }
    }

    /**
     * Use to connect the program to the modbus by TCP
     * @param ipAddress
     * Ip address of the modbus slave
     * @param port
     * Port of the modbus slave
     * @throws ModbusInitException
     */
    public void connect(String ipAddress, int port) throws ModbusInitException {
        ModbusFactory modbusFactory = new ModbusFactory();
        IpParameters ip = new IpParameters();
        ip.setHost(ipAddress);
        ip.setPort(port);

        master = (TcpMaster) new ModbusFactory().createTcpMaster(ip, true);
        master.init();
    }
}
