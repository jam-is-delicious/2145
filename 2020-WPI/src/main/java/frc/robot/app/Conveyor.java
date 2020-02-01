package frc.robot.app;

import com.ctre.phoenix.motorcontrol.can.TalonSRX; // import TalonSRX library
import com.ctre.phoenix.motorcontrol.ControlMode; // import ControlModes for TalonSRXs

import frc.robot.OI.Controller; // import Xbox 360 Controller inputs
import frc.robot.Robot; // import base robot script

public class Conveyor {
    private int[] ids = {4,5}; // motor id
    private TalonSRX[] motors = {null, null}; // TalonSRX object
    private double rTrigger; // right trigger input value
    private double lTrigger; // left trigger input value

    public Conveyor() {
        motors[4] = new TalonSRX(ids[4]);
        motors[5] = new TalonSRX(ids[5]);

        motors[4].follow(motors[5]); // motor 4 is set to follow motor 5
    }

    public void run() { // runs periodically
        set();
    }

    public void set() {
        rTrigger = Robot.oi.getAxis(Controller.CoPilot, 3); // set rTrigger to controller input
        lTrigger = Robot.oi.getAxis(Controller.CoPilot, 2); // set lTrigger to controller input
        motors[4].set(ControlMode.PercentOutput, lTrigger-rTrigger); // set motor speed; left motor from 0 to -1, right motor from 0 to 1
    }
}