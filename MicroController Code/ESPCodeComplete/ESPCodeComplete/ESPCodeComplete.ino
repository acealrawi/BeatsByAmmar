/*
Name:		ESPCodeComplete.ino
Created:	6/12/2017 10:20:55 AM
Author:	paulr
*/

#include <ESP8266WiFi.h>
#include <Wire.h>
#include "SSD1306.h"
#include <Average.h>

#define sleepDelay 60 //Defines time in seconds after last heartbeat before esp goes to sleep

const char* ssid = "esp8266";		//network name
const char* password = "123456789";	//network password
const char* host = "145.76.117.154";//ip of tcp server
#define port 8088

#define threshold 600
#define debounce 300
int lastSignal, signal;
unsigned long lastPulseTime;

#define totalMeasurements 6
Average<int> bpm(totalMeasurements);//Reserve space for totalMeasurements entries in the average bucket

#define RSTPIN 16
//SDA -> 12
//SCL -> 14
//Initialize the OLED display useing Wire library 
SSD1306 display(0x3D, 12, 14);	

void setup() {
	pinMode(RSTPIN, OUTPUT);	
	digitalWrite(RSTPIN, LOW);	//Reset pin of OLED display pulled to gnd to startup
	WiFi.mode(WIFI_STA);
	WiFi.begin(ssid, password);	//Start wifi connection
	while (WiFi.status() != WL_CONNECTED) delay(500);
	delay(500);
	digitalWrite(RSTPIN, HIGH);
	display.init();
	display.setFont(testL);
}

// the loop function runs over and over again until power down or reset
void loop() {
	WiFiClient client;
	client.connect(host, port);		//ESP connects to tcp server
	client.print(analogRead(A0));	//ESP sends analogValue of the sensor to tcp server
	display.clear();				//display gets cleared
	display.drawString(0, 0, String(int(bpm.mean())));	//display draws number of 
	display.display();
	//if the esp hasn't detected a hearbeat in more than sleepDelay seconds the esp will go in deepSleep
	if ((millis() - lastPulseTime) > (sleepDelay*1000)) {
		ESP.deepSleep(0);//Esp will go in deep sleep without wakeup time
	}
}

void getHeartBeat(){
	signal = analogRead(A0);
	//If the signal is lower then last signal and the debounce delay has passed to filter out noise
	if (signal < lastSignal && signal > threshold && (millis() - lastPulseTime) > debounce) {
		bpm.push(60000 / (millis() - lastPulseTime));	//Add current bpm to average bucket
		lastPulseTime = millis();
	}
	lastSignal = signal;
}