package ch.hevs.isi.core;

import ch.hevs.isi.db.DatabaseConnector;
import ch.hevs.isi.field.FieldConnector;
import ch.hevs.isi.web.WebConnector;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class datapoint, useful to create the bases of the subclasses
 * with a specific type.
 *
 * @author Arnaud Bonvin
 * @version v1.0
 * */

public abstract class DataPoint {
    // Name of the datapoint
    protected String label;

    // State input or input/output
    private boolean isOutput;

    // Map that linked all
    private static Map<String, DataPoint> dataPointMap = new HashMap<>();

    /**
     * <b>Constructor</b><br>
     * Datapoint contain a label that is used to identifie it
     * and value contain in the datapoint.
     *
     * @param label
     * The name of the datapoint
     * @param isOutput
     * Is activ if we can write and read onto
     * */
    public DataPoint(String label, boolean isOutput) {
        this.label = label;
        this.isOutput = isOutput;
        dataPointMap.put(label, this);
    }

    /**
     * Used to link a datapoint to the database
     * */
    protected void toConnector(){
        DatabaseConnector dbc = DatabaseConnector.getInstance();
        dbc.onNewValue(this);

        WebConnector wc = WebConnector.getInstance();
        wc.onNewValue(this);

        if(this.isOutput() == true){
            FieldConnector fc = FieldConnector.getInstance();
            fc.onNewValue(this);
        }
    }

    /**
     * Use to have the label of a datapoint
     * @return label
     * Name of the datapoint
     * */
    public String getLabel() {
        return label;
    }

    /**
     * Use to have the affectation of an datapoint (input or input/ouput)
     * @return isOutput
     * If the datapoint is an output
     * */
    public boolean isOutput() {
        return isOutput;
    }

    /**
     * Return the value of the data point as a string
     * @return value
     * the value of the datapoint
     * */
    public String getValueString(){
        return new String();
    }

    /**
     * Use to have all the information of the datapoint as a string
     * @return
     * the value contained in the datapoint
     */
    @Override
    public String toString() {
        return new String("Label: " + this.getLabel() + " & value: " + this.getValueString());
    }

    /**
     * Use to the find in all the datapoint, the one with the label in parameter
     * @param label
     * @return
     * the datapoint with the label in parameter
     */
    public static DataPoint getDataPointFromLabel(String label){
        return dataPointMap.get(label);
    }

    /**
     * Use for the test of the datapoint class
     * @param args
     */
    public static void main(String[] args) {


        BooleanDataPoint bdp_1 = new BooleanDataPoint("test_1", true);
        BooleanDataPoint bdp_2 = new BooleanDataPoint("test_2", false);

        FloatDataPoint fdp_1 = new FloatDataPoint("test_3", true);
        FloatDataPoint fdp_2 = new FloatDataPoint("test_4", false);


        bdp_1.setValue(true);
        System.out.println(bdp_1);

        bdp_2.setValue(true);
        System.out.println(bdp_2);

        fdp_1.setValue(10);
        System.out.println(fdp_1);

    }

}
