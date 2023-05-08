package ch.hevs.isi.db;

import ch.hevs.isi.core.DataPoint;
import ch.hevs.isi.core.DataPointListener;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Database conector is used to communicate with the database, and
 * implement all methods needed to the communication.
 *
 * @author Arnaud Bonvin
 * @author v1.0
 */
public class DatabaseConnector implements DataPointListener {

    // implementation variables
    private static DatabaseConnector instance = null;
    private static final String token = "Ose2wlb3lDFA7mkYtUe-5nOqKG96Q1tR1qb-CYY3hv46GRRbnavCMxVXJhmXUqTFsRes8lRiFl8iV7b_z9RX8Q==";
    private static final int BACK_DAYS = 6;
    TimeManager _timeManager = new TimeManager(BACK_DAYS);

    /**
     * <b>Constructor</b><br>
     * Use to create an instance of the database connector. A useful
     * class that establish the connection with the database and all
     * request that are needed to communicate.
     */
    public DatabaseConnector() { }

    /**
     * Use to get the instance of the singleton, and if it isn't
     * already created, create the singleton.
     * @return
     * The instance of the singleton
     */
    public static DatabaseConnector getInstance() {
        if (instance == null) {
            // create a new instance if none existed
            instance = new DatabaseConnector();
        }
        return instance;
    }

    /**
     * Use to push data to the database. Notice that
     * @param label
     * Name of the value that is pushed
     * @param value
     * Value to push
     * @param _timestamp
     * Timestampe when the value is measured
     */
    public void pushToDatabase(String label,String value, long _timestamp) {
        try {
            //Establish connection
            String urlDB = "https://influx.sdi.hevs.ch/api/v2/write?org=SIn01&bucket=SIn01";
            URL url = new URL(urlDB);                   //Try to connnect to the database with URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //Configuration HTTP Header
            connection.setRequestProperty("Authorization", "Token " + token);
            connection.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
            connection.setRequestProperty("Accept", "application/json");

            connection.setRequestMethod("POST");        //Define type of methode
            connection.setDoOutput(true);               //Indicate that there is a message body

            //Fetch OutputStreamWriter for the connection
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());

            String uri = "ProjetTCO " + label + "=" + value + " " + _timestamp;
            System.out.println(uri);
            writer.write(uri);
            writer.flush();                             //Clear writer

            int responseCode = connection.getResponseCode();

            if(responseCode != 204) System.out.println("Connection code: " + responseCode);

            //Close connection
            writer.close();
            connection.disconnect();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Use to push a data point to database
     * @param dp
     * The new datapoint to change
     */
    @Override
    public void onNewValue(DataPoint dp) {
        long _timestamp = 0;

        if(dp.getLabel().equals("CLOCK_FLOAT")){
            _timeManager.setTimestamp(dp.getValueString());
            _timestamp = _timeManager.getNanosForDB();
        }

        if(_timestamp != 0){
            //Check if the timestamp is right
            pushToDatabase(dp.getLabel(), dp.getValueString(), _timestamp);
        }
    }
}
