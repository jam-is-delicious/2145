/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final class PIDConstants {
        public static final double kP = 0.4;
        public static final double kI = 0.02;
        public static final double kD = 0.005;
        public static final double kFF = 0.1;

        public static final double kTurretP = 0.4;
        public static final double kTurretI = 0.02;
        public static final double kTurretD = 0.005;
        public static final double kTurretFF = 0.1;

		public static final double TURRET_SETPOINT = 0.1;
    }

    public static final class DriveConstants {
        public static final int F_RIGHT_DEVICE_ID = 0;
        public static final int F_LEFT_DEVICE_ID = 1;
        public static final int R_RIGHT_DEVICE_ID = 2;
        public static final int R_LEFT_DEVICE_ID = 3;

        public static final int PIGEON_DEVICE_ID = 4;

<<<<<<< Updated upstream
        public static final int L_R_ENCODER_DEVICE_ID = 5;
        public static final int F_B_ENCODER_DEVICE_ID = 6;
=======
        public static final int F_RIGHT_ENCODER_DEVICE_ID = 5;
        public static final int F_LEFT_ENCODER_DEVICE_ID = 6;
        public static final int R_RIGHT_ENCODER_DEVICE_ID = 7;
        public static final int R_LEFT_ENCODER_DEVICE_ID = 8;

        public static final double ENCODER_CONVERSION_FACTOR = 0.0436332312999;
>>>>>>> Stashed changes
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
        public static final int R_STICK_X = 2;
    }

}
