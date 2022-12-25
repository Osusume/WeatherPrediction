package ArduinoConnection;

import java.util.ArrayList;

public class DHT11 {
    private ArrayList<Float> humidityList;
    private ArrayList<Float> temperatureList;

    public DHT11(){
        humidityList = new ArrayList<>();
        temperatureList = new ArrayList<>();
    }

    public void AddMeasures(float temperature, float humidity) {
        humidityList.add(humidity);
        temperatureList.add(temperature);
    }

    public ArrayList<Float> getHumidityList() {
        return humidityList;
    }

    public ArrayList<Float> getTemperatureList() {
        return temperatureList;
    }
}
