
#include <Servo.h> 
#include <SoftwareSerial.h>

Servo myservo1;
int servo_pin = 10;
int input_pin = 2;
int open_box = 0;
//int ldr_pin = 13;

void setup() 
{ 
  pinMode(input_pin, INPUT);
  myservo1.attach(servo_pin);
  myservo1.write(90);
//  Serial.begin(9600);
} 

void loop() 
{
  if (digitalRead(input_pin) == HIGH) {
    if (open_box == 0) {
      myservo1.write(90);
      open_box = 1;
    }
  } else {
    if (open_box == 1) {
      myservo1.write(0);
      open_box = 0;
    }
  }
//  int i = 0;
//    for (i = 0; i<10; i++) {
//      myservo1.write(0);
//      delay(350);
//      myservo1.write(90);
//      open_box = 1;
//      
//      delay(2000);
//      
//      myservo1.write(180);
//      delay(368);
//      myservo1.write(90);
//      open_box = 0;
//
//      delay(2000);
//    }
//  int LDRValue = analogRead(ldr_pin);
//  Serial.println(LDRValue);
} 

