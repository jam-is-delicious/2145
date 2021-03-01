package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.Constants.OIConstants;

public class OI {

    public enum Controller {
        Pilot, CoPilot;
    }

    private Joystick pilot = new Joystick(OIConstants.PILOT_CONTROLLER_PORT);
    private Joystick coPilot = new Joystick(OIConstants.COPILOT_CONTROLLER_PORT);

    Button xButton = new JoystickButton(pilot, OIConstants.X_BUTTON_ID);
    Button aButton = new JoystickButton(pilot, OIConstants.A_BUTTON_ID);

    public OI() {
        
    }

    public double getAxisRaw(Controller cont, int axisID) {
        switch(cont) {
            case Pilot:
                return pilot.getRawAxis(axisID);
            case CoPilot:
                return coPilot.getRawAxis(axisID);
            default:
                return 0;
        }
    }
}