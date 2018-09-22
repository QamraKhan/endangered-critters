import RPi.GPIO as GPIO
import time
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)
GPIO.setup(17, GPIO.IN)                 #Read output from PIR motion sensor
GPIO.setup(27, GPIO.OUT)                 #LED output pin

from picamera import PiCamera
from time import sleep
from datetime import datetime


camera = PiCamera()
filePath = "/home/pi/Hackathon/Photos/"

print "Initialsing..."
counter = 1
while counter <= 10
	print counter,"..."
	time.sleep(1)
	counter += 1

while True:
		i=GPIO.input(17)
        if i==0:                        		#When output from motion sensor is LOW
                print "No intruders",i
                GPIO.output(27, GPIO.LOW)       #Turn OFF LED
                time.sleep(0.5)
        elif i==1:                      		#When output from motion sensor is HIGH
                print "Intruder detected",i
                GPIO.output(27, GPIO.HIGH)      #Turn ON LED
                #Get current time
                currentTime = datetime.now()
                #Create file name
                picTime = currentTime.strftime("%Y.%m.%d-%H%M%S")
                picName = picTime + '.jpg'
                picFilePath = filePath + picName
                camera.start_preview()
				time.sleep(2)
				camera.capture(picFilePath)
				print "Picture Taken!"
				camera.stop_preview()
                time.sleep(10)