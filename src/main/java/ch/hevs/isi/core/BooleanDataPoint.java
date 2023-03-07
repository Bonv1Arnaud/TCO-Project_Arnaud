package ch.hevs.isi.core;

public class BooleanDataPoint extends DataPoint {
    boolean value;          //Value of the datapoint

    public BooleanDataPoint (String _label, boolean _isOutput) {
        super(_label, _isOutput);
    }

    public void setValue (boolean _value){
        value = _value;
        System.out.println("Value is updated with " + _value);
    }
}
