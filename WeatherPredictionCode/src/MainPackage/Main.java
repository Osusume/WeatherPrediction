package MainPackage;

import ArduinoConnection.ArduinoConnector;
import ArduinoConnection.DHT11;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception, IOException {
        DHT11 sensor = new DHT11();
        ArduinoConnector connector = new ArduinoConnector("COM3", 9600, sensor);
        int i = 0;

        connector.openPort();

        for(; i < 5; i++) {
            connector.getMeasures();
        }

        connector.closePort();
        System.out.println(sensor.getTemperatureList());
        System.out.println(sensor.getHumidityList());
    }
}
