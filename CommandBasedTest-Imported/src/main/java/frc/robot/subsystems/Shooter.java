// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CanBusConstants;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
  CANSparkMax flywheelMotor;
  TalonSRX conveyorMotor;
  TalonSRX pickupMotor;
  TalonSRX intakeMotor;

  /** Creates a new Shooter. */
  public Shooter() {
    flywheelMotor = new CANSparkMax(CanBusConstants.FLYWHEEL_MOTOR_DEVICE_ID, MotorType.kBrushless);
    conveyorMotor = new TalonSRX(CanBusConstants.CONVEYOR_MOTOR_DEVICE_ID);
    pickupMotor = new TalonSRX(CanBusConstants.PICKUP_MOTOR_DEVICE_ID);
    intakeMotor = new TalonSRX(CanBusConstants.INTAKE_MOTOR_DEVICE_ID);
  }

  public void Shoot(double speed) {
    flywheelMotor.set(speed);
    conveyorMotor.set(ControlMode.PercentOutput, ShooterConstants.CONVEYOR_SPEED);
    pickupMotor.set(ControlMode.PercentOutput, ShooterConstants.PICKUP_SPEED);
  }

  public void Intake() {
    conveyorMotor.set(ControlMode.PercentOutput, ShooterConstants.CONVEYOR_SPEED);
    intakeMotor.set(ControlMode.PercentOutput, ShooterConstants.INTAKE_SPEED);
    pickupMotor.set(ControlMode.PercentOutput, -ShooterConstants.PICKUP_SPEED*0.1);
  }
}
