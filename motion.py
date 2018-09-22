import RPi.GPIO as GPIO
import time
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)
GPIO.setup(17, GPIO.IN)                 #Read output from PIR motion sensor
GPIO.setup(4, GPIO.OUT)                 #LED output pin

while True:
        i=GPIO.input(17)
        if i==0:                        #When output from motion sensor is LOW
                print "No intruders",i
                GPIO.output(4, GPIO.LOW)        #Turn OFF LED
                time.sleep(0.5)
        elif i==1:                      #When output from motion sensor is HIGH
                print "Intruder detected",i
                GPIO.output(4, GPIO.HIGH)       #Turn ON LED
                time.sleep(0.5)
