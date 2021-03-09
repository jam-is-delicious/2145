package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.Vector2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;

import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.MathConstants;

public class Drivetrain extends SubsystemBase {

    private CANSparkMax f_right;
    private CANSparkMax f_left;
    private CANSparkMax r_right;
    private CANSparkMax r_left;

    //private CANCoder l_r_drag_encoder;
    //private CANCoder f_b_drag_encoder;

    private ADXRS450_Gyro gyro;

    private Vector2d position;
    private Vector2d lastPosition;
    private Vector2d relativePosition;

    public Drivetrain() 
    {
        f_right = new CANSparkMax(DriveConstants.F_RIGHT_DEVICE_ID, MotorType.kBrushless);
        f_left = new CANSparkMax(DriveConstants.F_LEFT_DEVICE_ID, MotorType.kBrushless);
        r_right = new CANSparkMax(DriveConstants.R_RIGHT_DEVICE_ID, MotorType.kBrushless);
        r_left = new CANSparkMax(DriveConstants.R_LEFT_DEVICE_ID, MotorType.kBrushless);

        //l_r_drag_encoder = new CANCoder(DriveConstants.L_R_ENCODER_DEVICE_ID);
        //f_b_drag_encoder = new CANCoder(DriveConstants.F_B_ENCODER_DEVICE_ID);

        gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
        gyro.calibrate();

        position = new Vector2d();
        relativePosition = new Vector2d();

        f_left.setInverted(true);
        r_left.setInverted(true);
        f_right.setInverted(false);
        r_right.setInverted(false);

        position = new Vector2d();
    }

    @Override
    public void periodic() 
    {
        updatePositionVector();
    }

    /**
     * Sets the speeds of each drivetrain motor individually
     * @param frSpeed Percent value sent to the front-right motor
     * @param flSpeed Percent value sent to the front-left motor
     * @param rrSpeed Percent value sent to the rear-right motor
     * @param rlSpeed Percent value sent to the rear-left motor
     */
    public void setAllCartesian(double frSpeed, double flSpeed, double rrSpeed, double rlSpeed) 
    {
        f_right.set(frSpeed);
        f_left.set(flSpeed);
        r_right.set(rrSpeed);
        r_left.set(rlSpeed);
    }

    /**
     * Sets the speed of all drivetrain motors simultaneously
     * @param speed Percent value given to the motor controllers.
     */
    public void setAll(double speed) 
    {
        f_right.set(speed);
        f_left.set(speed);
        r_right.set(speed);
        r_left.set(speed);
    }

    /** 
     * Sets the speed of the drivetrain motors to turn
     * @param speed Percent value given to the motor controllers. Positive values turn the robot clockwise, negative values turn the robot counter-clockwise.
    */
    public void setTurn(double speed) 
    {
        f_right.set(-speed);
        f_left.set(speed);
        r_right.set(-speed);
        r_left.set(speed);
    }

    /**
     * Sets the speed of the drivetrain motors to strafe
     * @param speed Percent value given to the motor controllers. 
     */
    public void setStrafe(double speed) 
    {
        f_left.set(speed);
        r_right.set(speed);
        f_right.set(-speed);
        r_left.set(-speed);
    }

    /**
     * Sets the speed of the motors to move in the direction of a vector
     */
    public void setWithVector(Vector2d vector) 
    {
        if(vector.magnitude() > 1)
            vector = new Vector2d(vector.x / vector.magnitude(), vector.y / vector.magnitude());

        f_right.set(-vector.x + vector.y);
        f_left.set(vector.x + vector.y);
        r_right.set(vector.x + vector.y);
        r_left.set(-vector.x + vector.y);
    }

    /**
     * Updates the robot's position vector relative to its starting point. Converts encoder pulses to inches.
     */
    void updatePositionVector()
    {
        lastPosition = position;
        //position = new Vector2d(l_r_drag_encoder.getPosition() * MathConstants.ENCODER_PULSES_TO_INCHES, f_b_drag_encoder.getPosition() * MathConstants.ENCODER_PULSES_TO_INCHES);
        //relativePosition = new Vector2d(relativePosition.x + getDrivetrainPositionDelta().x, relativePosition.y + getDrivetrainPositionDelta().y);
    }

    /**
     * Resets encoder positions to 0
     */
    public void resetRelativePosition() 
    {
        relativePosition = new Vector2d(0, 0);
    }

    /**
     * @return Returns the position of the robot, in inches, in the form of a 2-dimensional vector
     */
    public Vector2d getDrivetrainPosition() 
    {
        return position;
    }

    /**
     * @return Returns the change in position of the robot from the last scheduler run
     */
    public Vector2d getDrivetrainPositionDelta() 
    {
        return new Vector2d(position.x - lastPosition.x, position.y - lastPosition.y);
    }

    public Vector2d getDrivetrainRelativePosition() {
        return relativePosition;
    }

    /**
     * @return Returns data from the gyro in an array with a length of 3; [0] = Yaw/Y-axis, [1] = Pitch/X-axis, [2] = Roll/Z-axis
     */
    public double getGyroAngle() 
    {
        return gyro.getAngle();
    }
}