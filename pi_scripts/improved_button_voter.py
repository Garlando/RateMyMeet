#!/usr/bin/env python

from time import sleep
import RPi.GPIO as GPIO
import subprocess

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
    subprocess.call(["~/ratemymeet/call_lambda.sh " + str(button - 21)], shell=True)
    print("vote recorded")


# Wait for Buttons to be pressed, run the function in "callback" when it does,
# also software debounce for 300 ms to avoid triggering it multiple times a second
GPIO.add_event_detect(BUTTON_1, GPIO.BOTH, callback=button_pressed(BUTTON_1), bouncetime=300)
GPIO.add_event_detect(BUTTON_2, GPIO.BOTH, callback=button_pressed(BUTTON_2), bouncetime=300)
GPIO.add_event_detect(BUTTON_3, GPIO.BOTH, callback=button_pressed(BUTTON_3), bouncetime=300)
GPIO.add_event_detect(BUTTON_4, GPIO.BOTH, callback=button_pressed(BUTTON_4), bouncetime=300)

# Start a loop that never ends
while True:
    # Put anything you want to loop normally in here
    sleep(60);  # Sleep for a full minute, any interrupt will break this so we are just saving cpu cycles.
