import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.fazecast.jSerialComm.SerialPort;
public class ArduinoConnection {
    public static void main(String[] args) throws IOException, InterruptedException {
        SerialPort sp = SerialPort.getCommPort("COM3"); // Nome della porta seriale

        sp.setComPortParameters(9600, 8, 1, 0); // Parametri porta
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

        if (sp.openPort()){ // Apertura porta
            System.out.println("Port is open");
        } else {
            System.out.println("Port is closed");
            return;
        }

        while(true) {
            byte[] b = new byte[4];
            Thread.sleep(1000);

            while(!(sp.getInputStream().available() > 0)) {}
            int dim = sp.getInputStream().read(b); // Lettura dalla porta seriale
            if(dim == 4) {
                float f = ByteBuffer.wrap(b).order(ByteOrder.LITTLE_ENDIAN).getFloat(); // conversione in float
                System.out.println(dim + " | " + f);
            }
        }
    }
}
