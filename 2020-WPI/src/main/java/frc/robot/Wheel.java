package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.OI.Controller;

public class Wheel {

    TalonSRX motor = new TalonSRX(3);

    public void run() {

        if(Robot.oi.getAxis(Controller.CoPilot, 2) > 0.1d){
            
            motor.set(ControlMode.PercentOutput, 1d);

        }else if(Robot.oi.getAxis(Controller.CoPilot, 2) < - 0.1d){

            motor.set(ControlMode.PercentOutput, -1d);

        }

    }

}