/**
   The MIT License (MIT)

   Copyright (c) 2016 by Daniel Eichhorn

   Permission is hereby granted, free of charge, to any person obtaining a copy
   of this software and associated documentation files (the "Software"), to deal
   in the Software without restriction, including without limitation the rights
   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
   copies of the Software, and to permit persons to whom the Software is
   furnished to do so, subject to the following conditions:

   The above copyright notice and this permission notice shall be included in all
   copies or substantial portions of the Software.

   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
   SOFTWARE.

*/
#define threshold 600
#define debounce 300
#define avgMeasure 6

// Include the correct display library
// For a connection via I2C using Wire include
#include <Wire.h>  // Only needed for Arduino 1.6.5 and earlier
#include "SSD1306.h" // alias for `#include "SSD1306Wire.h"`
#include <Average.h>
#include <pb_encode.h>

// or #include "SH1106.h" alis for `#include "SH1106Wire.h"`
// For a connection via I2C using brzo_i2c (must be installed) include
// #include <brzo_i2c.h> // Only needed for Arduino 1.6.5 and earlier
// #include "SSD1306Brzo.h"
// #include "SH1106Brzo.h"
// For a connection via SPI include
// #include <SPI.h> // Only needed for Arduino 1.6.5 and earlier
// #include "SSD1306Spi.h"
// #include "SH1106SPi.h"



// Initialize the OLED display using SPI
// D5 -> CLK
// D7 -> MOSI (DOUT)
// D0 -> RES
// D2 -> DC
// D8 -> CS
// SSD1306Spi        display(D0, D2, D8);
// or
// SH1106Spi         display(D0, D2);

// Initialize the OLED display using brzo_i2c
// D3 -> SDA
// D5 -> SCL
#define RSTPIN D7
// SSD1306Brzo display(0x3c, D3, D5);
// or
// SH1106Brzo  display(0x3c, D3, D5);

// Initialize the OLED display using Wire library
SSD1306  display(0x3D, D3, D5);
// SH1106 display(0x3c, D3, D5);

// Created by http://oleddisplay.squix.ch/ Consider a donation
// In case of problems make sure that you are using the font file with the correct version!
// Created by http://oleddisplay.squix.ch/ Consider a donation
// In case of problems make sure that you are using the font file with the correct version!

Average<int> bpm(avgMeasure);//Reserve space for avgMeasure entries in the average bucket

void setup() {
  Serial.begin(115200);
  Serial.println();
  Serial.println(); 
  pinMode(RSTPIN, OUTPUT);
  digitalWrite(RSTPIN, LOW);
  delay(500);
  digitalWrite(RSTPIN, HIGH);
  // Initialising the UI will init the display too.
  display.init();
  display.setFont(testL);
}

int lastSignal;
int Signal;
long int lastTime;
void loop() {
  display.clear();
  display.drawString(0,0,String(int(bpm.mean())));
  display.display();
  Signal = analogRead(A0);

  if (Signal < lastSignal && Signal > threshold && (millis() - lastTime) > debounce) {
    bpm.push(60000 / (millis() - lastTime));
    lastTime = millis();
  }
  //Serial.println();
  Serial.println(int(bpm.mean()));
  lastSignal = Signal;
  delay(10);
}
