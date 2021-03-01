package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.sensors.PigeonIMU;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants.DriveConstants;

public class Drivetrain extends SubsystemBase {

    private CANSparkMax f_right;
    private CANSparkMax f_left;
    private CANSparkMax r_right;
    private CANSparkMax r_left;

    private PigeonIMU gyro = new PigeonIMU(DriveConstants.PIGEON_DEVICE_ID);

    public Drivetrain() {

        f_right = new CANSparkMax(DriveConstants.F_RIGHT_DEVICE_ID, MotorType.kBrushless);
        f_left = new CANSparkMax(DriveConstants.F_LEFT_DEVICE_ID, MotorType.kBrushless);
        r_right = new CANSparkMax(DriveConstants.R_RIGHT_DEVICE_ID, MotorType.kBrushless);
        r_left = new CANSparkMax(DriveConstants.R_LEFT_DEVICE_ID, MotorType.kBrushless);

        f_left.setInverted(true);
        r_left.setInverted(true);
        f_right.setInverted(false);
        r_right.setInverted(false);
    }

    /**
     * Sets the speeds of each drivetrain motor individually
     * @param frSpeed Percent value sent to the front-right motor
     * @param flSpeed Percent value sent to the front-left motor
     * @param rrSpeed Percent value sent to the rear-right motor
     * @param rlSpeed Percent value sent to the rear-left motor
     */
    public void setAllCartesian(double frSpeed, double flSpeed, double rrSpeed, double rlSpeed) {
        f_right.set(frSpeed);
        f_left.set(flSpeed);
        r_right.set(rrSpeed);
        r_left.set(rlSpeed);
    }

    /**
     * Sets the speed of all drivetrain motors simultaneously
     * @param speed Percent value given to the motor controllers.
     */
    public void setAll(double speed) {
        f_right.set(speed);
        f_left.set(speed);
        r_right.set(speed);
        r_left.set(speed);
    }

    /** 
     * Sets the speed of the drivetrain motors to turn
     * @param speed Percent value given to the motor controllers. Positive values turn the robot counter-clockwise, negative values turn the robot clockwise.
    */
    public void setTurn(double speed) {
        f_right.set(speed);
        f_left.set(-speed);
        r_right.set(speed);
        r_left.set(-speed);
    }

    /**
     * Sets the speed of the drivetrain motors to strafe
     * @param speed Percent value given to the motor controllers. 
     */
    public void setStrafe(double speed) {
        f_left.set(speed);
        r_right.set(speed);
        f_right.set(-speed);
        r_left.set(-speed);
    }

    /**
     * Sets the speed of the motors to move in the direction of the vector
     * @param x X value of the vector
     * @param y Y value of the vector
     */
    public void setWithVector(double x, double y) {
        f_right.set(-x + y);
        f_left.set(x + y);
        r_right.set(x + y);
        r_left.set(-x + y);
    }

    /**
     * @return Returns data from the gyro in an array with a length of 3; [0] = Yaw/Y-axis, [1] = Pitch/X-axis, [2] = Roll/Z-axis
     */
    public double[] getGyroData() {
        double[] temp = {0, 0, 0};
        gyro.getYawPitchRoll(temp);
        return temp;
    }
}