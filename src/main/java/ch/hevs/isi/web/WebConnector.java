package ch.hevs.isi.web;

import ch.hevs.isi.core.DataPoint;
import ch.hevs.isi.core.DataPointListener;

public class WebConnector implements DataPointListener {
    private static WebConnector instance = null;

    private WebConnector(){

    }

    public WebConnector getInstance(){
        if (instance == null){
            instance = new WebConnector();
        }
        return instance;
    }

    private void pushToWeb(String _label, String _value){
        //ToBe complete...
    }

    @Override
    public void onNewValue(DataPoint dp) {
        System.out.println("the new value"+ dp.getValue() +"the data point " + dp.getLabel() );
    }
}
