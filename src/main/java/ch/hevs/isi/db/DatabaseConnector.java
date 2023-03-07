package ch.hevs.isi.db;

import ch.hevs.isi.core.DataPoint;
import ch.hevs.isi.core.DataPointListener;

public class DatabaseConnector implements DataPointListener {

    // instance database
    private static DatabaseConnector instance = null;


    private DatabaseConnector() {

    }

    // methode
    public DatabaseConnector getInstance() {
        if (instance == null) {
            instance = new DatabaseConnector();
        }
        return instance;

    }

    @Override
    public void onNewValue(DataPoint dp) {
        System.out.println("the new value"+ dp."the data point " + dp.getLabel() +);
    }
}
