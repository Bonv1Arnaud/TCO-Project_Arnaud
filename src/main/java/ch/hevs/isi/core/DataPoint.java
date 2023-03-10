package ch.hevs.isi.core;

import ch.hevs.isi.db.DatabaseConnector;
import ch.hevs.isi.web.WebConnector;
import com.sun.javafx.binding.StringFormatter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class DataPoint {
    // implementation variables
    private String label;
    private boolean isOutput;

    // implementation method
    // method dataPointMap
    private static Map<String, DataPoint> dataPointMap = new HashMap<>();

    // methode getLabel
    public String getLabel() {
        return label;
    }

    // method isOutput
    public boolean isOutput() {
        return isOutput;
    }
public abstract Object getValue();
    // implementation builder
    public DataPoint(String label, boolean isOutput) {
        this.label = label;
        this.isOutput = isOutput;
        dataPointMap.put(label, this);
    }

    protected void toConnectors() {
        DatabaseConnector.getInstance().onNewValue(this);
        WebConnector.getInstance().onNewValue(this);
    }

    // main

    public static void main(String[] args) {

        BooleanDataPoint b1;
        b1 = new BooleanDataPoint("current", true);
        b1.setValue(true);
    }

}
