package ch.hevs.isi.field;

import ch.hevs.isi.core.BooleanDataPoint;
import ch.hevs.isi.core.DataPoint;
import ch.hevs.isi.core.DataPointListener;

public class FieldConnector implements DataPointListener {
    private static FieldConnector instance = null;

    private FieldConnector() {

    }

    public FieldConnector getInstance() {
        if (instance == null){
            instance = new FieldConnector();
        }
        return instance;
    }

    private void pushToWeb (String _label, String _value){
        System.out.println("new value of " + _label + " push to field connector :" + _value);
    }
    @Override
    public void onNewValue(DataPoint dp){
        System.out.println("new value of " + dp.getLabel() + " push to field connector :" + dp.getValue());

    /*        BooleanDataPoint bdp;
        // if it's boolean
        if ( ) {
        bdp=    ModbusAccessor.getInstance().readBoolean();
        }

        // if it's float
        if () {
          bdp =  ModbusAccessor.getInstance().readFloat()

        } */

        ModbusRegister mr =BooleanRegister.getRegisterFromDataPoint(dp);
        if ( mr != null)
        mr.write();}
    }


