// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.drive.Vector2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveInCircle extends CommandBase {

  public enum CircleDirection { 
    Clock, CounterClock
  }

  boolean finished = false;

  double speed;
  double degreesToDrive;
  double degreesDriven;
  double radius;
  double startingAngle;
  
  Vector2d botPos;
  Vector2d lastPos;

  CircleDirection direction;

  Drivetrain drivetrain;

  /** Creates a new DriveInCircle. */
  public DriveInCircle(Drivetrain _drivetrain, double _speed, double _startingAngle, double _degreesToDrive, double _radius, CircleDirection _direction) {
    speed = _speed;
    degreesToDrive = _degreesToDrive;
    radius = _radius;
    direction = _direction;
    startingAngle = _startingAngle;
    degreesDriven = 0;

    addRequirements(_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    botPos = new Vector2d(Math.cos(Math.toRadians(startingAngle)) * radius, Math.sin(Math.toRadians(startingAngle)) * radius);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    if(degreesDriven < degreesToDrive) 
    {
      Vector2d movementVector;

      lastPos = botPos;
      botPos = new Vector2d(botPos.x + drivetrain.getDrivetrainPositionDelta().x, botPos.y + drivetrain.getDrivetrainPositionDelta().y);
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

      drivetrain.setWithVector(movementVector);
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
