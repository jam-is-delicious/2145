/*
    Button References:
    A: 1
    B: 2
    X: 3
    Y: 4
    lBumper: 5
    rBumper: 6
    Back: 7
    Start: 8
    lStick: 9
    rStick: 10

    Axis references:
    LXAxis: 0
    LYAxis: 1
    lTrigger: 2
    rTrigger: 3
    RXAxis: 4
    RYAxis: 5
*/
package frc.robot;
import edu.wpi.first.wpilibj.Joystick;

public class OI {
    public static enum Controller {
        Pilot, CoPilot
    }

    private Joystick pilot;
    private Joystick coPilot;

    public OI() {
        pilot = new Joystick(0);
        coPilot = new Joystick(1);
    }

    public boolean getButton(Controller cont, int button) {
        switch (cont) {
        case Pilot:
            return pilot.getRawButton(button);
        case CoPilot:
            return coPilot.getRawButton(button);
        default:
            return false;
        }
    }

    public boolean getButtonDown(Controller cont, int button) {
        switch (cont) {
            case Pilot:
                return pilot.getRawButtonPressed(button);
            case CoPilot:
                return coPilot.getRawButtonPressed(button);
            default:
                return false;
            }
    }

    public double getAxis(Controller cont, int axis) {
        switch (cont) {
        case Pilot:
            return pilot.getRawAxis(axis);
        case CoPilot:
            return coPilot.getRawAxis(axis);
        default:
            return 0;
        }
    }
}