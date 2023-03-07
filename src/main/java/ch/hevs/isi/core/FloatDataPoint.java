package ch.hevs.isi.core;

public class FloatDataPoint extends DataPoint{
    float value;            //Value of the data point

    public FloatDataPoint (String _label, boolean _isOutput) {
        label = _label;
        isOuput = _isOutput;
    }
    public void setValue (float _value) {
        value = _value;
        System.out.println("Value is updated with " + _value);
    }
}
