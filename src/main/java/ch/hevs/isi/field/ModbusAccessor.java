package ch.hevs.isi.field;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.ip.IpParameters;

public class ModbusAccessor {
    private int regAddress;
    private float newValue;
    private boolean _newValue;
    private static ModbusAccessor instance = null;

    private ModbusAccessor() {
    }

    public static ModbusAccessor getInstance() {
        if (instance == null) {
            instance = new ModbusAccessor();
        }
        return instance;
    }

    public void readFloat(int regAddress) {
        this.regAddress = regAddress;
    }

    public void writeFloat(int regAddress, float newValue) {
        this.regAddress = regAddress;
        this.newValue = newValue;
    }

    public void readBoolean(int regAddress) {
        this.regAddress = regAddress;
    }

    public void writeBoolean(int regAddress, boolean _newValue) {
        this.regAddress = regAddress;
        this._newValue = _newValue;
    }



    public void connect(String ipAddress, int port) {
        ModbusFactory modbusFactory = new ModbusFactory();
        IpParameters ip = new IpParameters();
        ip.setHost(ipAddress);
        ip.setPort(port);
        ModbusMaster tcpMaster = modbusFactory.createTcpMaster(ip, true);
        while (true) {
            try {
                tcpMaster.init();
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
}
