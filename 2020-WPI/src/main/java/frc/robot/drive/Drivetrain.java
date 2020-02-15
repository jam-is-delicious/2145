package frc.robot.drive; // where to find code

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.OI.Controller; // import Xbox 360 controller inputs
import frc.robot.Robot; // import base robot script

public class Drivetrain extends SubsystemBase {
    private final int[][] motorID = { { 4, 1 }, { 2, 3 } }; // motor ids for the motor controllers
    private CANSparkMax[][] motors = { { null, null }, { null, null } }; // actual motor controllers, not defined yet
    private double wheelSpeed = 0.5; // default driving speed for the robot (set to slowest)
    public int reverseMultiplier;

    public Drivetrain() {                                   // creating a new instance of Drivetrain to assign ids to the motor controllers
        for (int a = 0; a < 2; a++) {                       // nested for loop (a "2D" loop, essentially) that assignes a variable to each motor based on position on the robot (a = left & right, b = front & back)
            for (int b = 0; b < 2; b++) {                   // nothing to see here uwu
                motors[a][b] = new CANSparkMax(motorID[a][b], MotorType.kBrushless); // sets the ids for each motor depending on its position
                System.out.println(motorID[a][b]);
            }
        }

        motors[0][1].follow(motors[0][0]); // makes the rear-left motor (the left "slave") follow the front-left motor (the left "master")
        motors[1][1].follow(motors[1][0]); // same as above for the right side
    }

    public void run() {                     // called every "frame"
        controlSpeed(); // run the speed controls
        tankDrive();    // run the direction controls
    }

    private void controlSpeed() {

        if(Robot.oi.getButtonDown(Controller.Pilot, 6)) { // if right bumper is pressed down
            wheelSpeed += 0.25; // increase the speed by 0.25 ONCE
        } else if(Robot.oi.getButtonDown(Controller.Pilot, 5))  { // if left bumper is pressed down
            wheelSpeed -= 0.25; // decrease the speed by 0.25 ONCE
        }

        if(wheelSpeed >= 1) { // clamp that limits the min and max values of the speed to 0.5 and 1 respectively
            wheelSpeed = 1;
        } else if (wheelSpeed <= 0.5) {
            wheelSpeed = 0.5;
        }
    }

    private void tankDrive() {
        double x = Robot.oi.getAxis(Controller.Pilot, 0); // value for where the joystick is on the x-axis
        double y = Robot.oi.getAxis(Controller.Pilot, 1); // same as above for the y-axis
        double left = (y*reverseMultiplier) - x / 2;
        double right = (y*reverseMultiplier) + x / 2;
        set(left*wheelSpeed, right*wheelSpeed);
        //set(((x*reverseMultiplier) + Robot.oi.getAxis(Controller.Pilot, 3) - Robot.oi.getAxis(Controller.Pilot, 2))*reverseMultiplier, ((x*reverseMultiplier) - Robot.oi.getAxis(Controller.Pilot, 3) + Robot.oi.getAxis(Controller.Pilot, 2))*reverseMultiplier);
    }

    public void set(double lSpeed, double rSpeed) {
        motors[0][0].set(-lSpeed);
        motors[1][0].set(rSpeed);
    }
}
