package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import frc.robot.Constants.DriveConstants;

public class Drivetrain extends SubsystemBase {

    private TalonSRX f_right;
    private TalonSRX f_left;
    private TalonSRX r_right;
    private TalonSRX r_left;

    private PigeonIMU gyro = new PigeonIMU(DriveConstants.PIGEON_DEVICE_ID);

    public Drivetrain() {

        f_right = new TalonSRX(DriveConstants.F_RIGHT_DEVICE_ID);
        f_left = new TalonSRX(DriveConstants.F_LEFT_DEVICE_ID);
        r_right = new TalonSRX(DriveConstants.R_RIGHT_DEVICE_ID);
        r_left = new TalonSRX(DriveConstants.R_LEFT_DEVICE_ID);

        f_left.setInverted(true);
        r_left.setInverted(true);
        f_right.setInverted(false);
        r_right.setInverted(false);
    }

    public void setAllCartesian(double frSpeed, double flSpeed, double rrSpeed, double rlSpeed) {
        f_right.set(ControlMode.PercentOutput, frSpeed);
        f_left.set(ControlMode.PercentOutput, flSpeed);
        r_right.set(ControlMode.PercentOutput, rrSpeed);
        r_left.set(ControlMode.PercentOutput, rlSpeed);
    }

    public void setAll(double speed) {
        f_right.set(ControlMode.PercentOutput, speed);
        f_left.set(ControlMode.PercentOutput, speed);
        r_right.set(ControlMode.PercentOutput, speed);
        r_left.set(ControlMode.PercentOutput, speed);
    }
    
    public void setTurn(double speed) {
        f_right.set(ControlMode.PercentOutput, speed);
        f_left.set(ControlMode.PercentOutput, -speed);
        r_right.set(ControlMode.PercentOutput, speed);
        r_left.set(ControlMode.PercentOutput, -speed);
    }

    public void strafeRight(double speed) {
        f_left.set(ControlMode.PercentOutput, speed);
        r_right.set(ControlMode.PercentOutput, speed);
        f_right.set(ControlMode.PercentOutput, -speed);
        r_left.set(ControlMode.PercentOutput, -speed);
    }

    public void strafeLeft(double speed) {
        f_left.set(ControlMode.PercentOutput, -speed);
        r_right.set(ControlMode.PercentOutput, -speed);
        f_right.set(ControlMode.PercentOutput, speed);
        r_left.set(ControlMode.PercentOutput, speed);
    }

    public double[] getGyroData() {
        double[] temp = {0, 0, 0};
        gyro.getYawPitchRoll(temp);
        return temp;
    }
}