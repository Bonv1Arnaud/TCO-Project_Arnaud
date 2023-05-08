package ch.hevs.isi.field;

import ch.hevs.isi.core.DataPoint;

/**
 * Field connector is used to make the update of value from modbus to the program and
 * from the program to minecraft
 *
 * @author Arnaud Bonvin
 * @version v1.0
 */
public class FieldConnector {
    // Object of the field connector
    private static FieldConnector instance = null;

    /**
     * <b>Constructor</b><br>
     * Use to create an instance of the field connector.
     */
    private FieldConnector() {}

    /**
     * Use to get the instance of the singleton, and if it isn't
     * already created, create the singleton.
     * @return
     * The instance of the singleton
     */
    public static FieldConnector getInstance() {
        if (instance == null){
            instance = new FieldConnector();
        }
        return instance;
    }

    /**
     * Use to push a data point to modbus
     * @param dp
     * The new datapoint to change
     */
    public static void onNewValue(DataPoint dp){
        ModbusRegister mr = ModbusRegister.getRegisterFromDataPoint(dp);
        if(mr != null){
            mr.write();
        }
    }

    /**
     * Use only for testing the functionnality of the class.
     * @param args
     */
    public static void main(String[] args){
        new BooleanRegister(401, "REMOTE_SOLAR_SW", true);
        new BooleanRegister(609, "SOLAR_CONNECT_ST", false);
        new FloatRegister(57, "BATT_P_FLOAT", false,6000,-3000);
    }
}


