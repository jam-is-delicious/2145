package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.RobotMap;
import frc.robot.OI.Controller;

class Drivetrain extends SubsystemBase {
    TalonSRX front_right = new TalonSRX(RobotMap.FR_DEVICE_ID);
    TalonSRX front_left = new TalonSRX(RobotMap.FL_DEVICE_ID);
    TalonSRX rear_right = new TalonSRX(RobotMap.RR_DEVICE_ID);
    TalonSRX rear_left = new TalonSRX(RobotMap.RL_DEVICE_ID);

    PigeonIMU gyro = new PigeonIMU(RobotMap.PIGEON_DEVICE_ID);

    double driveValue;
    double strafeValue;
    double turnValue;
    double[] gyroYawPitchRoll = {0, 0, 0}; // [0] = Yaw, [1] = Pitch, [2] = Roll

    public void run() {
        GyroMechanumDrive();
    }

    void GyroMechanumDrive() {
        // Jostick values
        driveValue = -Robot.oi.getJoystickAxis(Controller.Pilot, RobotMap.L_JOYSTICK_Y_ID);
        strafeValue = Robot.oi.getJoystickAxis(Controller.Pilot, RobotMap.L_JOYSTICK_X_ID);
        turnValue = Robot.oi.getJoystickAxis(Controller.Pilot, RobotMap.R_JOYSTICK_X_ID);

        // puts gyro values in double array "gyroWayPitchRoll"
        gyro.getYawPitchRoll(gyroYawPitchRoll);

        // manipulates joystick values to be field-centric, not robot-centric
        double temp = driveValue*Math.cos(gyroYawPitchRoll[0]) + strafeValue*Math.sin(gyroYawPitchRoll[0]);
        strafeValue = -driveValue*Math.sin(gyroYawPitchRoll[0]) + strafeValue*Math.cos(gyroYawPitchRoll[0]);
        driveValue = temp;

        // uses inverse kinematic transformation to convert joystick controls to individual wheel commands
        // drives like arcade drive with LStick y-axis and RStick x-axis, strafes with LStick x-axis
        set(front_left, driveValue+turnValue+strafeValue);
        set(front_right, driveValue-turnValue-strafeValue);
        set(rear_left, driveValue+turnValue-strafeValue);
        set(rear_right, driveValue-turnValue+strafeValue);

        // Normalizing values to ensure that motor values never exceed 100%
        double max = Math.abs(front_left.getMotorOutputPercent());

        if(Math.abs(front_right.getMotorOutputPercent()) > max)
            max = Math.abs(front_right.getMotorOutputPercent());

        if(Math.abs(rear_left.getMotorOutputPercent()) > max)
            max = Math.abs(rear_left.getMotorOutputPercent());

        if(Math.abs(rear_right.getMotorOutputPercent()) > max)
            max = Math.abs(rear_right.getMotorOutputPercent());

        if(max > 1) {
            set(front_left, front_left.getMotorOutputPercent()/max);
            set(front_right, front_right.getMotorOutputPercent()/max);
            set(rear_left, rear_left.getMotorOutputPercent()/max);
            set(rear_right, rear_right.getMotorOutputPercent()/max);
        }
    }

    public void set(TalonSRX motor, double value) {
        motor.set(ControlMode.PercentOutput, value);
    }
}