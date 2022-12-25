package ArduinoConnection;
import com.fazecast.jSerialComm.SerialPort;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ArduinoConnector {
    String portDescriptor;
    int baudRate;
    SerialPort serialPort;
    DHT11 sensor;

    public ArduinoConnector(String portDescriptor, int baudRate, DHT11 sensor) {
        this.baudRate = baudRate;
        this.portDescriptor = portDescriptor;
        serialPort = SerialPort.getCommPort(portDescriptor);
        serialPort.setComPortParameters(baudRate, 8, 1, 0);
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
        this.sensor = sensor;
    }

    public void openPort() throws Exception {
        if (!serialPort.openPort()) {
            throw new Exception("Port not open or not accessible");
        } else {
            System.out.println("Port open");
        }
    }

    public void closePort() throws Exception {
        if (!serialPort.closePort()) {
            throw new Exception("Not possible to close");
        } else {
            System.out.println("Port closed");
        }
    }

    public void getMeasures() throws IOException {
        byte[] receivedBytes = new byte[4];
        byte[] tempBytes = new byte[4];
        byte[] humBytes = new byte[4];

        float temp;
        float hum;
        int dim;

        dim = serialPort.getInputStream().available();
        while(!(dim > 0)) {}

        dim = serialPort.getInputStream().read(receivedBytes);

        if(dim == 4) {
            /*for(int i = 0; i < 4; i++) {
                tempBytes[i] = receivedBytes[i];
                humBytes[i] = receivedBytes[i + 4];
            }*/

            temp = ByteBuffer.wrap(receivedBytes).order(ByteOrder.BIG_ENDIAN).getFloat();
            //hum = ByteBuffer.wrap(humBytes).order(ByteOrder.BIG_ENDIAN).getFloat();

            sensor.AddMeasures(temp, 0.0f);

            System.out.println(temp + " | " + 0.0);
        }
    }
}
