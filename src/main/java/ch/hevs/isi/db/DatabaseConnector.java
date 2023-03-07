package ch.hevs.isi.db;

import ch.hevs.isi.core.DataPoint;
import ch.hevs.isi.core.DataPointListener;

public class DatabaseConnector implements DataPointListener {

    // implementation variables
    private static DatabaseConnector instance = null;

    // constructor
    public DatabaseConnector() {
    }

    // implementation methode
    // create a new instance if none existed
    public DatabaseConnector getInstance() {
        if (instance == null) {
            instance = new DatabaseConnector();
        }
        return instance;
    }
    // push to DB
    public void pushToDatabase(String label,String value)
    {
        System.out.println("new value of"+ label + "push to"+ value);
    }
    // print information of the value and data point
    @Override
    public void onNewValue(DataPoint dp) {
        System.out.println("the new value" + dp.getValue() + "the data point " + dp.getLabel());
    }

}
