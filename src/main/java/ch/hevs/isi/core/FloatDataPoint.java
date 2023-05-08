package ch.hevs.isi.core;

/**
 * FloatDataPoint, useful to create a datapoint with a value (float), a input/output state and a label
 *
 * @author Arnaud Bonvin
 * @author v1.0
 */
public class FloatDataPoint extends DataPoint{
    //Value of the data point
    private float value;

    /**
     * <b>Constructor</b><br>
     * Float datapoint contain a float value, a input/output state
     * and a label (string), with also a couple of
     * useful method.
     *
     * @param _label
     * The name of the datapoint
     * @param _isOutput
     * Is activ if we can write and read onto
     */
    public FloatDataPoint (String _label, boolean _isOutput) {
        super(_label, _isOutput);       // Use global constructor
        this.setValue(0);               // Init value
    }

    /**
     * Use to change the value in the datapoint
     * @param _value
     * The new value to put in datapoint
     */
    public void setValue (float _value) {
        value = _value;
        this.toConnector();
    }

    /**
     * Return the value of the data point as a float
     * @return value
     * the value of the datapoint
     * */
    public float getValue() {
        return value;
    }

    /**
     * Return the value of the data point as a string
     * @return value
     * the value of the datapoint
     * */
    public String getValueString(){
        return String.valueOf(value);
    }

    /**
     * Use to have all the information of the datapoint as a string
     * @return
     * the value contained in the datapoint
     */
    public String toString(){
        return new String("Label: " + label + " & value: " + value);
    }
}
