// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.CameraConstants;
import frc.robot.Constants.FieldConstants;
import frc.robot.Constants.MathConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.Shooter;

public class ShootToGoal extends CommandBase {
  Camera cam;
  Shooter shooter;

  double distance;

  /** Creates a new ShootToGoal. */
  public ShootToGoal(Camera _cam, Shooter _shooter) {
    cam = _cam;
    shooter = _shooter;
    
    addRequirements(_cam, _shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    distance = (FieldConstants.GOAL_HEIGHT - CameraConstants.CAMERA_HEIGHT_IN_INCHES) / Math.tan((CameraConstants.CAMERA_ANGLE_IN_DEGREES * MathConstants.DEG_TO_RAD) + cam.getY());
    shooter.Shoot((distance + FieldConstants.DISTANCE_BETWEEN_OUTER_AND_INNER) * ShooterConstants.DISTANCE_TO_SPEED_CONVERSION);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
