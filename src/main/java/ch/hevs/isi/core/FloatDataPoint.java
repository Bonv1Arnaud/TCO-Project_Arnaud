package ch.hevs.isi.core;

public class FloatDataPoint extends DataPoint{
    float value;            //Value of the data point

    public FloatDataPoint (String _label, boolean _isOutput) {
        super(_label, _isOutput);
    }
    public void setValue (float _value) {
        value = _value;
        System.out.println("Value is updated with " + _value);
    }

    public Object getValue() {
        return value;
    }
}
