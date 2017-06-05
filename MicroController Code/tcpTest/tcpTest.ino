
#include <ESP8266WiFi.h>

const char* ssid     = "";                 //Router name
const char* password = "";                    //Wifi password
const char* IPV4 = "";                   //IPV4 address
boolean requestingConnection = false;

void setup() {
  Serial.begin(9600);                             //Initialize Serial
  delay(10);
  //Connecting to the Wifi
  Serial.print("Connecting to ");
  Serial.println(ssid);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {         //Loading Bar
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.println("WiFi connected");
  Serial.print("IP address: ");
  Serial.println(WiFi.localIP());
  makeConnection();
}

void loop() {
  while (true) {
    Serial.print("Main programm");
  }
  Serial.println();
  Serial.println("closing connection");
  return;
}

void makeConnection() {
  //Connecting to Server
  Serial.print("connecting to server ");
  // Use WiFiClient class to create TCP connections
  WiFiClient client;
  const int tcpPort = 6789;
  if (!client.connect("192.168.43.27", tcpPort)) {          //If connection Failed
    Serial.println("connection failed");
    return;
  }
  while (!requestingConnection) {
    client.write("NodeMCU requesting connection\n");
    Serial.println("Request has been send");
    String inFromServer = String(client.read());
    Serial.print(inFromServer);
    if (inFromServer.equals("65")) {
      requestingConnection = true;
      Serial.println("Request accepted");
    }
    delay(3000);
  }
}
