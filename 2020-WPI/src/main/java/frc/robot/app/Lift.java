package frc.robot.app;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import frc.robot.OI.Controller;
import frc.robot.Robot;

public class Lift {
    DoubleSolenoid lift;
    boolean toggled;

    public Lift() {
        lift = new DoubleSolenoid(0, 1);
    }

    public void init() {
        lift.set(Value.kReverse);
    }

    public void run() {
        if(Robot.oi.getButtonDown(Controller.CoPilot, 6)) {
            if(!toggled) {
                lift.set(Value.kForward);
                toggled = true;
            } else if (toggled) {
                lift.set(Value.kReverse);
                toggled = false;
            }
        }
    }

}
