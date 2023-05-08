package ch.hevs.isi.core;

/**
 * DataPointListener is global interface implemented by the different class who
 * communicate and need to have a "onNewValue" method.
 *
 * @author Arnaud Bonvin
 * @author v1.0
 */
public interface DataPointListener {

    /**
     * Use to set a new value for a data point given in parameter
     * @param dp
     * The new datapoint to change
     */
    public void onNewValue(DataPoint dp);
}
