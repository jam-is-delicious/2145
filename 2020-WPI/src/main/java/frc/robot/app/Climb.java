package frc.robot.app;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.OI.Controller;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class Climb extends SubsystemBase {
    TalonSRX hookMotor = new TalonSRX(8);
    TalonSRX winchMotor = new TalonSRX(9);

    public void run() {

        hookMotor.set(ControlMode.PercentOutput, Robot.oi.getAxis(Controller.Pilot, 3)-Robot.oi.getAxis(Controller.Pilot, 2));
        // TODO: Winch controls

    }
}