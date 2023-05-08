package ch.hevs.isi.utils;

import ch.hevs.isi.field.BooleanRegister;
import ch.hevs.isi.field.FloatRegister;

import java.io.*;


/**
 * Use to read all the modbus register in an external CSV file, and create corresponding
 * register (boolean or float) with the given information (label, modbus adress, etc.).
 *
 * @author Arnaud Bonvin
 * @version v1.0
 */
public abstract class ReadCSV {
    /**
     * Use to read all the modbus register in an external CSV file, and create corresponding
     * register (boolean or float) with the given information (label, modbus adress, etc.).
     *
     * @param pathName
     * The absolute path of the csv file who contain configuration
     */
    public static void readDataRegister (String pathName){
        try{
            File file = new File(pathName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while((line = br.readLine()) != null){                      // Read line by line
                String[] str = line.split(";");

                String label = str[0];                                  // Take the first column
                int register = Integer.valueOf(str[4]);                 // Take the register number
                boolean isOutput = (str[3].equals("Y")) ? true : false; // Test if it is an output

                switch (str[1]){                                        // str contains datatype of value
                    case "F":
                        float range = Float.valueOf(str[5]);
                        float offset = Float.valueOf(str[6]);
                        new FloatRegister(register, label, isOutput, range, offset);
                        break;
                    case "B":
                        new BooleanRegister(register, label, isOutput);
                        break;
                    default:
                        System.out.print("Error with CSV file");
                }
            }
            fr.close();                                                 // Close properly
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
