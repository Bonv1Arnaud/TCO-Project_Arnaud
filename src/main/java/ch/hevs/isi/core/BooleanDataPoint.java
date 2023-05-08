package ch.hevs.isi.core;

/**
 * BooleanDataPoint, useful to create a datapoint with a value (boolean),a input/output state and a label
 *
 * @author Arnaud Bonvin
 * @author v1.0
 */
public class BooleanDataPoint extends DataPoint {
    //Value of the datapoint
    private boolean value;

    /**
     * <b>Constructor</b><br>
     * Boolean datapoint contain a boolean value, a input/output state
     * and a label (string), with also a couple of
     * useful method.
     *
     * @param _label
     * The name of the datapoint
     * @param _isOutput
     * Is activ if we can write and read onto
     */
    public BooleanDataPoint (String _label, boolean _isOutput) {
        super(_label, _isOutput);               // Use global constructor
        this.setValue(false);                   // Init value
    }

    /**
     * Use to change the value in the datapoint
     * @param _value
     * The new value to put in datapoint
     */
    public void setValue (boolean _value){
        value = _value;
        this.toConnector();
    }

    /**
     * Return the value of the data point as a boolean
     * @return value
     * the value of the datapoint
     * */
    public boolean getValue() {
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
