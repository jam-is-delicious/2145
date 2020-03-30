package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

class OI extends SubsystemBase {
    public enum Controller {
        Pilot, CoPilot;
    }

    Joystick pilot;
    Joystick coPilot;

    public boolean getButtonDown(Controller cont, int button) {
        switch(cont) {
            case Pilot:
                return pilot.getRawButtonPressed(button);
            case CoPilot:
                return coPilot.getRawButtonPressed(button);
            default:
                return false;
        }
    }

    public double getJoystickAxis(Controller cont, int axis) {
        switch(cont) {
            case Pilot:
                return pilot.getRawAxis(axis);
            case CoPilot:
                return coPilot.getRawAxis(axis);
            default:
                return 0;
        }
    }
}