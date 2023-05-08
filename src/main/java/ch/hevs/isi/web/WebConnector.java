package ch.hevs.isi.web;

import ch.hevs.isi.core.BooleanDataPoint;
import ch.hevs.isi.core.DataPoint;
import ch.hevs.isi.core.DataPointListener;
import ch.hevs.isi.core.FloatDataPoint;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import java.net.InetSocketAddress;


/**
 * Use to connect the program to the interface web page. When can activate/ deactivate
 * some producer/consumer and have a global monitoring.
 *
 * @author Arnaud Bonvin
 * @version v1.0
 */
public class WebConnector implements DataPointListener {
    // Instance of the web connector
    private static WebConnector instance = null;

    // Instance of the web socket
    private static WebSocketServer ws = null;

    // Port number of the web page
    private static final int PORT_NUMBER = 8888;

    /**
     * Use to connect to the web page and manage do detect event (click, open page, close
     * page, etc.)
     */
    private WebConnector(){
        ws = new WebSocketServer(new InetSocketAddress(PORT_NUMBER)) {
            @Override
            public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
               // Trigger when a new client is connected to web server
                webSocket.send("WelCome to minecraft");
                System.out.println("WEB_SERVER : new client connected");
            }

            @Override
            public void onClose(WebSocket webSocket, int i, String s, boolean b) {
                // Trigger whe a client close web server
                System.out.println("WEB_SERVER : client left");
            }

            @Override
            public void onMessage(WebSocket webSocket, String s) {
                // Trigger when client send message
                String[] message = s.split("=");
                DataPoint dp = DataPoint.getDataPointFromLabel(message[0]);

                if(dp instanceof BooleanDataPoint){
                    ((BooleanDataPoint) dp).setValue(Boolean.valueOf(message[1]));
                }
                else if(dp instanceof FloatDataPoint){
                    ((FloatDataPoint) dp).setValue(Float.valueOf(message[1]));
                }
            }

            @Override
            public void onError(WebSocket webSocket, Exception e) {
                System.out.println("WEB_SERVER : error -> " + e);
            }

            @Override
            public void onStart() {

            }
        };
        ws.start();                 // Start web server
    }

    /**
     * Use to get the instance of the singleton, and if it isn't
     * already created, create the singleton.
     * @return
     * The instance of the singleton
     */
    public static WebConnector getInstance(){
        if (instance == null){
            instance = new WebConnector();
        }
        return instance;
    }

    /**
     * Use to push data to the web page
     * @param _label
     * Label of the value pushed
     * @param _value
     * Value pushed to the web page
     */
    private void pushToWeb(String _label, String _value){
        String message = new String(_label + "=" + _value);

        // Pooling web server page
        for(WebSocket ws : ws.getConnections()){
            ws.send(message);
        }
    }

    /**
     * Use to push a data point to database
     * @param dp
     * The new datapoint to change
     */
    @Override
    public void onNewValue(DataPoint dp) {
        pushToWeb(dp.getLabel(), dp.getValueString());
    }
}
