import RPi.GPIO as GPIO
import time
import sys
import requests
import firebase_admin
from firebase_admin import credentials
from firebase_admin import storage
from firebase import firebase
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

#Firebase setup
firebase = firebase.FirebaseApplication('https://crittercam-baa64.firebaseio.com/', None)
cred = credentials.Certificate('/home/pi/Hackathon/crittercam-baa64-firebase-adminsdk-1dqny-82f60b08f8.json')
firebase_admin.initialize_app(cred, {
    'storageBucket': 'crittercam-baa64.appspot.com'
})
bucket = storage.bucket()


#Start code
print "Initialsing..."
counter = 10
while counter <= 0
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
                #Start Camera
                camera.start_preview()
		time.sleep(2)
		camera.capture(picFilePath)
		print "Picture Taken!"
		camera.stop_preview()
                #Start Upload to Firebase Storage
                blob = bucket.blob('photos/' + picName)
                with open(picFilePath, "rb") as fp:
                        blob.upload_from_file(fp)
                print "Upload to Storage Successful!"
                #Start upload to Firebase Real-Time
                varDate = currentTime.strftime("%Y.%m.%d")
                varTime = currentTime.strftime("%H%M%S")
                data = {"date": varDate, "time": varTime}
                firebase.post('/datetime/', data)
                print "Upload to Real-Time Successful!"
                url = blob.public_url
                time.sleep(10)