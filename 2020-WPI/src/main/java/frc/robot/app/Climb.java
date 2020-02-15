package frc.robot.app;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.Robot;
import frc.robot.OI.Controller;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class Climb {
    TalonSRX motor = new TalonSRX(8);

    public void run() {

        motor.set(ControlMode.PercentOutput, Robot.oi.getAxis(Controller.Pilot, 3)-Robot.oi.getAxis(Controller.Pilot, 2));
        // TODO: Winch controls

    }
}