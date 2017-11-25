import RPi.GPIO as GPIO
import subprocess

# Configure the Pi to use the BCM (Broadcom) pin names, rather than the pin positions
GPIO.setmode(GPIO.BCM)
# Set button pins
button1 = 22
button2 = 23
button3 = 24
button4 = 25
ERROR_VALUE = 999


def check_button(button_to_check, selected_button):
    if not GPIO.input(button_to_check):
        if selected_button == 0:
            selected_button = button_to_check
        else:
            selected_button = ERROR_VALUE
    return selected_button


GPIO.setup(button1, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.setup(button2, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.setup(button3, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.setup(button4, GPIO.IN, pull_up_down=GPIO.PUD_UP)

print("Waiting for input")
while True:
    selectedButton = 0
    selectedButton = check_button(button1, selectedButton)
    selectedButton = check_button(button2, selectedButton)
    selectedButton = check_button(button3, selectedButton)
    selectedButton = check_button(button4, selectedButton)

    if selectedButton == ERROR_VALUE:
        print("error")
    elif selectedButton != 0:
        subprocess.call(["~/ratemymeet/call_lambda.sh " + str(selectedButton - 21)], shell=True)
        print("vote recorded")
        print("Waiting for more input")
