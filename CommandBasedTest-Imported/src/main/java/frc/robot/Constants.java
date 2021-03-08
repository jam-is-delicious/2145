/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.drive.Vector2d;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final class PIDConstants {
        public static final double kP = 0.4;
        public static final double kI = 0.02;
        public static final double kD = 0.005;
        public static final double kFF = 0.1;
    }

    public static final class DriveConstants {
        public static final int F_RIGHT_DEVICE_ID = 1;
        public static final int F_LEFT_DEVICE_ID = 2;
        public static final int R_RIGHT_DEVICE_ID = 3;
        public static final int R_LEFT_DEVICE_ID = 4;

        public static final int L_R_ENCODER_DEVICE_ID = 5;
        public static final int F_B_ENCODER_DEVICE_ID = 6;
    }

    public static final class MathConstants {
        public static final double ENCODER_PULSES_TO_INCHES = 0.0436332312999;
        public static final double ENCODER_PULSES_TO_FEET = ENCODER_PULSES_TO_INCHES / 12;
        public static final double INCHES_TO_FEET = 1/12;
        public static final double FEET_TO_INCHES = 12;
        public static final double RAD_TO_DEG = 180 / Math.PI;
        public static final double DEG_TO_RAD = Math.PI / 180;

        public static final double VectorToAngle(Vector2d vec) {
            return Math.atan2(vec.y, vec.x);
        }

        public static final Vector2d AngleToVector(double angDeg) {
            return new Vector2d(Math.cos(angDeg * DEG_TO_RAD), Math.sin(angDeg * DEG_TO_RAD));
        }
    }

    public static final class OIConstants {
        public static final int PILOT_CONTROLLER_PORT = 0;
        public static final int COPILOT_CONTROLLER_PORT = 1;

        public static final int A_BUTTON_ID = 2;
        public static final int B_BUTTON_ID = 3;
        public static final int X_BUTTON_ID = 1;
        public static final int Y_BUTTON_ID = 4;

        public static final int L_STICK_Y = 1;
        public static final int L_STICK_X = 0;
        public static final int R_STICK_X = 4;
    }

}
