// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.drive.Vector2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.MathConstants;
import frc.robot.subsystems.Drivetrain;

public class StrafeInCircle extends CommandBase {

  public enum CircleDirection { 
    Clock, CounterClock
  }

  boolean finished = false;

  double speed;
  double degreesToDrive;
  double degreesDriven;
  double radius;
  double approachAngle;
  
  Vector2d botPos;
  Vector2d lastPos;

  CircleDirection direction;

  Drivetrain drive;

  /** Creates a new DriveInCircle. */
  public StrafeInCircle(Drivetrain _drive, double _speed, double _approachAngle, double _degreesToDrive, double _radius, CircleDirection _direction) {
    drive = _drive;
    speed = _speed;
    degreesToDrive = _degreesToDrive;
    radius = _radius;
    direction = _direction;
    approachAngle = _approachAngle;
    degreesDriven = 0;

    addRequirements(_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    botPos = new Vector2d(Math.cos(approachAngle * MathConstants.DEG_TO_RAD) * radius, Math.sin(approachAngle * MathConstants.DEG_TO_RAD) * radius);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    if(degreesDriven < degreesToDrive) 
    {
      Vector2d movementVector;

      lastPos = botPos;
      botPos = new Vector2d(botPos.x + drive.getDrivetrainPositionDelta().x, botPos.y + drive.getDrivetrainPositionDelta().y);
      degreesDriven += Math.toDegrees(Math.atan2(botPos.y, botPos.x) - Math.atan2(lastPos.y, lastPos.x));
      movementVector = botPos;

      switch(direction) {
        case Clock:
          movementVector.rotate(-90);
          break;
        case CounterClock:
          movementVector.rotate(90);
          break;
      }

      drive.setWithVector(movementVector);
    } 
    else 
    {
      finished = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
