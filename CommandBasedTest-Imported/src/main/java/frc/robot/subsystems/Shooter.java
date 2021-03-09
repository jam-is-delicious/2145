// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CanBusConstants;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
  CANSparkMax shooterMotor;
  CANSparkMax conveyorMotor;

  /** Creates a new Shooter. */
  public Shooter(OI oi) {
    shooterMotor = new CANSparkMax(CanBusConstants.FLYWHEEL_MOTOR_DEVICE_ID, MotorType.kBrushless);
    conveyorMotor = new CANSparkMax(CanBusConstants.CONVEYOR_MOTOR_DEVICE_ID, MotorType.kBrushless);
  }

  public void Shoot(float speed) {
    shooterMotor.set(speed);
    conveyorMotor.set(speed * ShooterConstants.SHOOTER_TO_CONVEYOR_SPEED);
  }
}
