package ch.hevs.isi.field;

import ch.hevs.isi.core.DataPoint;

public class FloatRegister {

    private int adress;
    private DataPoint dp;

    public FloatRegister(int _adress, DataPoint _dp){
        adress = _adress;
        dp = _dp;
    }

    public float read(){
        return ModbusAccessor.getInstance().readFloat(adress);

    }

    public void write(){

    }


    //methode read and write and getRegisterFromDataPoint
}
