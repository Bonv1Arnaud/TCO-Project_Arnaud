package ch.hevs.isi.field;

import ch.hevs.isi.core.BooleanDataPoint;
import ch.hevs.isi.core.DataPoint;
import ch.hevs.isi.core.DataPointListener;
import ch.hevs.isi.utils.Utility;

import java.io.File;

public class FieldConnector implements DataPointListener {
    private static FieldConnector instance = null;

    private FieldConnector() {

    }

    public static FieldConnector getInstance() {
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

        ModbusRegister mr = ModbusRegister.getRegisterFromDataPoint(dp);
        if (mr != null) mr.write();

        System.out.println(mr);
    }


    public static void main(String[] args){

    }
}


