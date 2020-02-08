package frc.robot.app;

import com.ctre.phoenix.motorcontrol.can.TalonSRX; // import TalonSRX library
import com.ctre.phoenix.motorcontrol.ControlMode; // import ControlModes for TalonSRXs

import frc.robot.OI.Controller; // import Xbox 360 Controller inputs
import frc.robot.Robot; // import base robot script

public class Conveyor {
    
    private TalonSRX[] motors = {null, null, null}; // TalonSRX object
    private double rTrigger; // right trigger input value
    private double lTrigger; // left trigger input value

    public Conveyor() {
        motors[0] = new TalonSRX(0);
        motors[1] = new TalonSRX(1);
        motors[2] = new TalonSRX(2);

        motors[0].follow(motors[1]); // motor 4 is set to follow motor 5
        motors[2].follow(motors[1]);
    }

    public void run() { // runs periodically
        set();
    }

    public void set() {
        rTrigger = Robot.oi.getAxis(Controller.CoPilot, 3); // set rTrigger to controller input
        lTrigger = Robot.oi.getAxis(Controller.CoPilot, 2); // set lTrigger to controller input
        motors[1].set(ControlMode.PercentOutput, lTrigger-rTrigger); // set motor speed; left motor from 0 to -1, right motor from 0 to 1
    }
}