package ch.hevs.isi.core;

import ch.hevs.isi.db.DatabaseConnector;
import ch.hevs.isi.web.WebConnector;

public class BooleanDataPoint extends DataPoint {
    private boolean value;          //Value of the datapoint

    @Override
    public Object getValue() {
        return value;
    }

    public BooleanDataPoint (String _label, boolean _isOutput) {
        super(_label, _isOutput);
    }

    public void setValue (boolean _value){
        value = _value;
        toConnectors();
    }
}
