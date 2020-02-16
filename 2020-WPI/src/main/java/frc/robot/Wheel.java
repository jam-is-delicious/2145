package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.OI.Controller;

public class Wheel extends SubsystemBase {

    TalonSRX motor = new TalonSRX(6);
    boolean deployed;

    public void run() {
        controlWheel();
    }

    void controlWheel() {
        if(deployed = false) {
            return;
        }

        if(Robot.oi.getAxis(Controller.CoPilot, 2) > 0.1d){
            
            motor.set(ControlMode.PercentOutput, 1d);

        }else if(Robot.oi.getAxis(Controller.CoPilot, 2) < - 0.1d){

            motor.set(ControlMode.PercentOutput, -1d);

        }
    }

}