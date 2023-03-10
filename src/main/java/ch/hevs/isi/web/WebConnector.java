package ch.hevs.isi.web;

import ch.hevs.isi.core.DataPoint;
import ch.hevs.isi.core.DataPointListener;

public class WebConnector implements DataPointListener {
    private static WebConnector instance = null;

    private WebConnector(){

    }

    public static WebConnector getInstance(){
        if (instance == null){
            instance = new WebConnector();
        }
        return instance;
    }

    private void pushToWeb(String _label, String _value){
        System.out.println("new value of " + _label + " push to web :" + _value);
    }

    @Override
    public void onNewValue(DataPoint dp) {
        pushToWeb(dp.getLabel(), dp.getValue().toString());
    }
}
