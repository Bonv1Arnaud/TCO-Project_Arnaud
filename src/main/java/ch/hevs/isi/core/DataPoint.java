package ch.hevs.isi.core;

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

}
