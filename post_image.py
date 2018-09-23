import RPi.GPIO as GPIO
import time
import sys
import requests
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

#Start code
print "Initialsing..."
counter = 10
while counter <= 0:
	print counter,"..."
	time.sleep(1)
	counter -= 1

while True:
	i=GPIO.input(17)
        if i==0:                        		#When output from motion sensor is LOW
                print "No motion",i
                GPIO.output(27, GPIO.LOW)       #Turn OFF LED
                time.sleep(0.5)
        elif i==1:                      		#When output from motion sensor is HIGH
                print "Motion detected",i
                GPIO.output(27, GPIO.HIGH)      #Turn ON LED
                #Get current time
                currentTime = datetime.now()
                #Create file name
                picTime = currentTime.strftime("%Y.%m.%d-%H%M%S")
                picTimeRT = currentTime.strftime("%Y%m%d%H%M%S")
                #print picTime
                #print picTimeRT
                picName = picTimeRT + '.jpg'
                picFilePath = filePath + picName
                #Start Camera
                camera.start_preview()
                time.sleep(2)
                camera.capture(picFilePath)
                print "Picture Taken!"
                camera.stop_preview()
                #Start Upload to Firebase Storage
                r = requests.post('http://35.229.42.247:5000', files = {"file": open(picFilePath, "rb")})
                print "Upload to Storage Successful!"
                print "You took a picture of a ", r.content
                time.sleep(10)
