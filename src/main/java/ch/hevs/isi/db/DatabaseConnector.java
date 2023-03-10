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
    public static DatabaseConnector getInstance() {
        if (instance == null) {
            instance = new DatabaseConnector();
        }
        return instance;
    }
    // push to DB
    public void pushToDatabase(String label,String value)
    {
        System.out.println("new value of "+ label + " equal to "+ value + " is pushed to the DB");
    }
    // print information of the value and data point
    @Override
    public void onNewValue(DataPoint dp) {
        String _label = dp.getLabel();
        Object _value = dp.getValue();

        pushToDatabase(_label, _value.toString());
    }

}
