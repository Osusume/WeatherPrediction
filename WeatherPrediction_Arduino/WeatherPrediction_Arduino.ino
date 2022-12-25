float temperature = 0;
float humidity = 0;

byte measures[8];
byte tempBytes[4];
byte humBytes[4];

void setup() {
  Serial.begin(9600);
}

void loop() {
  temperature = 2.25;
  humidity = 4.21;

  sendMeasures(temperature);
  delay(2000);
}

void sendMeasures(float num1) {
  byte *data1 = (byte *)&num1;
  //byte *data2 = (byte *)&num2;
  byte data[8];
  /*for(int i = 0; i < 4; i++) {
      data[i] = data1[i];
      data[i+4] = data2[i];
    }*/
    
  Serial.write(data1, 4);
}
