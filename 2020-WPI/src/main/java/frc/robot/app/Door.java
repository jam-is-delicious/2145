package frc.robot.app;

import frc.robot.Robot; //import base robot code
import edu.wpi.first.wpilibj.Servo; // import servo library
import frc.robot.OI.Controller; // import Xbox 360 Controller inputs

public class Door {

Servo door = new Servo(0); // finds the servo connected to PWM port 0

boolean doorOpen; // boolean to tell if the door is open

public void init() // initializer
{
    doorOpen = false; // door isn't open
    door.setPosition(0.75d); // sets servo to be 75% open
}

public void run() 
{
    toggleDoor();
}

private void toggleDoor()
{
    if(Robot.oi.getButtonDown(Controller.CoPilot, 3)) // if the X button is pressed
    {
        if(!doorOpen) { // if the door isn't open
            door.setPosition(0.5d); // set servo to 50% open
            doorOpen = true; // the door is open
        } else if (doorOpen) { // if the door is open
            door.setPosition(0.75d); // set servo to 75% open. Servor be tweakin.
            doorOpen = false; // the door isn't open
        }
    }
}
}

