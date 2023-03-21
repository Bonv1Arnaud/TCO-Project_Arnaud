package ch.hevs.isi.field;

public class ModbusAccessor {
    private int regAddress;
    private float newValue;
    private boolean _newValue;
     private static ModbusAccessor instance = null;

    private ModbusAccessor() {
    }

public static ModbusAccessor getInstance()
    {if( instance == null)
    {
       instance = new ModbusAccessor();
    }
    return instance; }

    public void readFloat(int regAddress) {
        this.regAddress = regAddress;
    }

    public void writeFloat(int regAddress, float newValue) {
        this.regAddress = regAddress;
        this.newValue = newValue;
    }

    public void readBoolean(int regAddress) {
        this.regAddress = regAddress;
    }

    public void writeBoolean(int regAddress, boolean _newValue) {
        this.regAddress = regAddress;
        this._newValue = _newValue;
    }


}
