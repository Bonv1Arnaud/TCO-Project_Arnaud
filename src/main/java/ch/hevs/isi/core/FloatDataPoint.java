package ch.hevs.isi.core;

import ch.hevs.isi.db.DatabaseConnector;
import ch.hevs.isi.web.WebConnector;

public class FloatDataPoint extends DataPoint{
    private float value;            //Value of the data point

    public FloatDataPoint (String _label, boolean _isOutput) {
        super(_label, _isOutput);
    }
    public void setValue (float _value) {
        _value = value;
        toConnectors();
    }

    public Object getValue() {
        return value;
    }
}
