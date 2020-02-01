/*
    Button references found in OI.java
*/

package frc.robot;

import edu.wpi.cscore.*; // import CameraServer essentials
import edu.wpi.first.cameraserver.CameraServer; //CameraServer library

import frc.robot.drive.Drivetrain; // import Drivetrain code
import frc.robot.OI.Controller; // import OI code

public class Camera {
    UsbCamera cam0 = CameraServer.getInstance().startAutomaticCapture(0); // create USBCamera for USB port 0, displays to SmartDashboard
    UsbCamera cam1 = CameraServer.getInstance().startAutomaticCapture(1); // create USBCamera for USB port 1, displays to SmartDashboard
    MjpegServer switchCam = CameraServer.getInstance().addSwitchedCamera("Dual Camera"); // create a Server that can switch between cameras
    public int currCam = 0; // current camera id
    Drivetrain drive = Robot.drive; // locating Drivetrain created in Robot.java

    public void run() { // runs periodically
        switchCam();
    }

    public void init() {
        switchCam.setSource(cam0); // camera 0 displays to Dashboard
        currCam = 0; // cam id is set to 0
        drive.reverseMultiplier = 1; // bot is not reversed
    }

    private void switchCam() {

        if(switchCam.getSource() == null) { // if no camera is detected
            return; // end the function
        }

        if(Robot.oi.getButtonDown(Controller.Pilot, 8)) { // if the Start button is pressed
            if(currCam == 0) { // if the current camera has an id of 0
                switchCam.setSource(cam1); // set camera 1 to display to Dashboard
                currCam = 1; // change id to 1
                drive.reverseMultiplier = -1; // the bot is now reversed
            } else if (currCam == 1) { // if the current camera has an id of 1
                switchCam.setSource(cam0); // set camera 0 to display to Dashboard
                currCam = 0; // change id to 0
                drive.reverseMultiplier = 1; // the bot is not reversed
            }
        }
    }
}