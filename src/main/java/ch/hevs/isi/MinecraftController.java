package ch.hevs.isi;

import ch.hevs.isi.field.*;
import ch.hevs.isi.utils.ReadCSV;
import ch.hevs.isi.utils.Utility;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Use to connect minecraft to an influxDB database, a specified web controller and also to regulate the electrical network.
 * an intelligent control is in place allowing an efficient management of the network and the different actors.
 *
 * @author Arnaud Bonvin & TCO teacher
 * @version v1.0
 */
public class MinecraftController {

    public static boolean ERASE_PREVIOUS_DATA_INB_DB        = false;

    public static void usage() {
        System.out.println();
        System.out.println("You're reading this message because no parameter (or not the needed ones) has been passed to the application.");
        System.out.println();
        System.out.println("In development mode, just add to your running configuration the needed parameters (see usage below).");
        System.out.println("In running mode, the application's usage is the following:");
        System.out.println("java MinecraftController <InfluxDB Server> <DB Name> <DB Measurement> <DB Username> <ModbusTCP Server> <ModbusTCP port> [-modbus4j] [-keepAlive]");
        System.out.println("where:");
        System.out.println("- <InfluxDB Server>:  The complete URL of the InfluxDB server, including the protocol (http or https)...");
        System.out.println("                      Example: https://influx.sdi.hevs.ch");
        System.out.println("- <DB Name>:          The name of the Influx DB to use. For this project, this name is the name of the group you've been affected to. (SInXX)");
        System.out.println("- <DB Username:       The user's name to use to access the DB. It's also your group's name. (SInXX)");
        System.out.println("- <ModbusTCP Server>: The IP address of the Minecraft ModbusTCP server (default value: localhost)");
        System.out.println("- <ModbusTCP port>:   The port number of the Minecraft ModbusTCP server (default value: 1502)");
        System.out.println("- [-eraseDB]:         Optional parameter! If set, the application will erase the previous data in InfluxDB...");
        System.out.println();
        System.exit(1);
    }

    @SuppressWarnings("all")
    public static void main(String[] args) {

        // ------------------------------------- DO NOT CHANGE THE FOLLOWING LINES -------------------------------------
        String dbProtocol       = "http";
        String dbHostName       = "localhost";
        String dbName           = "labo";
        String dbUserName       = "root";
        String dbPassword       = "root";

        String modbusTcpHost    = "localhost";
        int modbusTcpPort       = 1502;

        // Check the number of arguments and show usage message if the number does not match.
        String[] parameters = null;

        // If there is only one number given as parameter, construct the parameters according the group number.
        if (args.length >= 5) {
            parameters = args;

            // Decode parameters for influxDB
            String[] dbParams = parameters[0].split("://");
            if (dbParams.length != 2) {
                usage();
            }

            dbProtocol    = dbParams[0];
            dbHostName    = dbParams[1];
            dbName        = parameters[1];
            dbUserName    = parameters[2];
            dbPassword    = Utility.md5sum(dbUserName);

            // Decode parameters for Modbus TCP
            modbusTcpHost = parameters[3];
            modbusTcpPort = Integer.parseInt(parameters[4]);

            for (int i = 5; i < args.length; i++) {
                if (parameters[i].compareToIgnoreCase("-erasedb") == 0) {
                    ERASE_PREVIOUS_DATA_INB_DB = true;
                }
            }
        } else {
            usage();
        }

        // ------------------------------------ /DO NOT CHANGE THE FOLLOWING LINES -------------------------------------

        // Creation of measurement points
        ReadCSV.readDataRegister("C:\\dev\\GridLab_ArnaudxElise\\src\\main\\java\\ch\\hevs\\isi\\ModbusMap.csv");

        // Run modbus connection
        try {
            ModbusAccessor.getInstance().connect("localhost", 1502);
        } catch (Exception e) {
            System.out.println("MODBUS_ERROR : " + e);
            System.exit(05);
        }

        Timer t = new Timer();

        t.scheduleAtFixedRate(new TimerTask() {
            public void run(){
                ModbusRegister.poll();
            }
        }, 1000, 1000);


    }
}
