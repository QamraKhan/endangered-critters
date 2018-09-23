import RPi.GPIO as GPIO
import time
import sys
import requests
import Adafruit_DHT
from picamera import PiCamera
from time import sleep
from datetime import datetime

GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)
GPIO.setup(17, GPIO.IN)                 #Read output from PIR motion sensor
GPIO.setup(27, GPIO.OUT)                 #LED output pin

#Camera Setup
camera = PiCamera()
filePath = "/home/pi/Hackathon/Photos/"

#DHT11 Setup
sensor=Adafruit_DHT.DHT11
gpio=22

#Start code
print "Initialsing..."
counter = 10
while counter <= 0:
	print counter,"..."
	time.sleep(1)
	counter += 1

while True:
	i=GPIO.input(17)
        if i==0:                        		#When output from motion sensor is LOW
                print "No motion"
                GPIO.output(27, GPIO.LOW)       #Turn OFF LED
                time.sleep(0.5)
        elif i==1:                      		#When output from motion sensor is HIGH
                print "Motion detected"
                GPIO.output(27, GPIO.HIGH)      #Turn ON LED
                #Get current time
                currentTime = datetime.now()
                #Create file name
                picTime = currentTime.strftime("%Y.%m.%d-%H%M%S")
                picTimeRT = currentTime.strftime("%Y%m%d%H%M%S")
                picName = picTimeRT + '.jpg'
                picFilePath = filePath + picName
                #Start Camera
                camera.start_preview()
                time.sleep(2)
                camera.capture(picFilePath)
                print "Picture Taken!"
                camera.stop_preview()
                #taking temperature
                humidity, temperature = Adafruit_DHT.read_retry(sensor, gpio)
                if humidity is not None and temperature is not None:
                    print 'Temp={0:0.1f}*C  Humidity={1:0.1f}%'.format(temperature, humidity)
                else:
                    print 'Failed to get a temperature reading'
                #Start Upload to Firebase Storage
                r = requests.post('http://35.229.42.247:5000', files = {"file": open(picFilePath, "rb")}, headers = {"temperature": str(temperature), "humidity": str(humidity)})
                print "Upload to Storage Successful!"
                print "You have found a",r.content
                time.sleep(10)
