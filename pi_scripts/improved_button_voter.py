#!/usr/bin/env python

from time import sleep
import RPi.GPIO as GPIO
import subprocess
from datetime import datetime

GPIO.setmode(GPIO.BCM)
BUTTON_1 = 22
BUTTON_2 = 23
BUTTON_3 = 24
BUTTON_4 = 25
GPIO.setup(BUTTON_1, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.setup(BUTTON_2, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.setup(BUTTON_3, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.setup(BUTTON_4, GPIO.IN, pull_up_down=GPIO.PUD_UP)


# Create functions to run when the buttons are pressed
def button_pressed(button):
    print(str(button) +" "+ str(datetime.now()))
    subprocess.Popen(["~/ratemymeet/call_lambda.sh "+str(button-21)], shell=True)


# Wait for Buttons to be pressed, run the function in "callback" when it does,
# also software debounce for 300 ms to avoid triggering it multiple times a second
GPIO.add_event_detect(BUTTON_1, GPIO.FALLING, callback=button_pressed, bouncetime=300)
GPIO.add_event_detect(BUTTON_2, GPIO.FALLING, callback=button_pressed, bouncetime=300)
GPIO.add_event_detect(BUTTON_3, GPIO.FALLING, callback=button_pressed, bouncetime=300)
GPIO.add_event_detect(BUTTON_4, GPIO.FALLING, callback=button_pressed, bouncetime=300)

# Start a loop that never ends
while True:
    sleep(60);  # Sleep for a full minute, any interrupt will break this so we are just saving cpu cycles.
