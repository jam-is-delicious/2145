package frc.robot.app;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import frc.robot.OI.Controller;
import frc.robot.Robot;

public class Lift {
    DoubleSolenoid lift;
    Compressor comp;
    boolean toggled;

    public Lift() {
        lift = new DoubleSolenoid(0, 1);
        comp = new Compressor();
    }

    public void init() {
        lift.set(Value.kForward);
        comp.setClosedLoopControl(true);
    }

    public void stop () {
        comp.setClosedLoopControl(false);
    }

    public void run() {
        if(Robot.oi.getButtonDown(Controller.CoPilot, 6)) {
            if(!toggled) {
                lift.set(Value.kReverse);
                toggled = true;
            } else if (toggled) {
                lift.set(Value.kForward);
                toggled = false;
            }
        }
    }

}
