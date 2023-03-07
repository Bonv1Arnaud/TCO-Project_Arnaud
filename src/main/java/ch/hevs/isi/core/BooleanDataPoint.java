package ch.hevs.isi.core;

import ch.hevs.isi.db.DatabaseConnector;

public class BooleanDataPoint extends DataPoint {
    boolean value;          //Value of the datapoint

    @Override
    public Object getValue() {
        return value;
    }

    public BooleanDataPoint (String _label, boolean _isOutput) {
        super(_label, _isOutput);
    }

    public void setValue (boolean _value){
        DatabaseConnector dbc = new DatabaseConnector();

        dbc.onNewValue(this);


        value = _value;
        System.out.println("Value is updated with " + _value);
    }
}
