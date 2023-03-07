package ch.hevs.isi.core;
public class BooleanDataPoint extends DataPoint {
    boolean value;          //Value of the datapoint

    public void BooleanDataPoint (String _label, boolean _isOutput){
        label = _label;
        isOuput = _isOutput;
    }

    public void setValue (boolean _value){
        value = _value;
        System.out.println("Value is updated with " + _value);
    }
}
