// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.drive.Vector2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.Vector;
import frc.robot.subsystems.Drivetrain;

<<<<<<< Updated upstream
enum CircleDirection { 
  Clock, CounterClock
}

public class DriveInCircle extends CommandBase {
  double degreesToDrive;
  double degreesDriven;
  double radius;
  double startingAngle;
  
  Vector2d originPos;
  Vector2d botPos;
  Vector2d lastPos;

  CircleDirection direction;

  Drivetrain drivetrain;

  /** Creates a new DriveInCircle. */
  public DriveInCircle(Drivetrain _drivetrain, double _startingAngle, double _degreesToDrive, double _radius, CircleDirection _direction) {
    degreesToDrive = _degreesToDrive;
    radius = _radius;
    direction = _direction;
    startingAngle = _startingAngle;

    degreesDriven = 0;

    // Use addRequirements() here to declare subsystem dependencies.
=======
public class DriveInCircle extends CommandBase {

  Drivetrain drivetrain;

  double degreesToDrive;
  double degreesDriven;
  double radius;

  Vector2d lastPosition;
  Vector2d currentPosition;

  boolean finished = false;

  /** Creates a new DriveInCircle. */
  public DriveInCircle(Drivetrain _drivetrain, double _degreesToDrive, double _radius) {

    degreesToDrive = _degreesToDrive;
    radius = _radius;
    drivetrain = _drivetrain;

    degreesDriven = 0;

    lastPosition = new Vector2d();
    currentPosition = new Vector2d();

>>>>>>> Stashed changes
    addRequirements(_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
<<<<<<< Updated upstream
    originPos = new Vector2d(drivetrain.getDrivetrainPosition(), );
    lastPos = drivetrain.getDrivetrainPosition();
    botPos = new Vector2d(drivetrain.getDrivetrainPosition().x, drivetrain.getDrivetrainPosition().y);
=======

>>>>>>> Stashed changes
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    if(degreesDriven < degreesToDrive) 
    {
<<<<<<< Updated upstream
      double delta;
      Vector2d movementVector = botPos;

      delta = Math.atan2(botPos.y, botPos.x) - Math.atan2(lastPos.y, lastPos.x);
      degreesDriven += Math.toDegrees(delta);

      switch(direction){
        case Clock:
          movementVector.rotate(-90);
          break;
        case CounterClock:
          movementVector.rotate(90);
          break;
      }

      drivetrain.setWithVector(movementVector.x, movementVector.y);

      lastPos = botPos;
      botPos = drivetrain.getDrivetrainPosition();
=======
      double delta = Math.atan2(currentPosition.y, currentPosition.x) - Math.atan2(lastPosition.y, lastPosition.x);
      degreesDriven += Math.toDegrees(delta);
      
      lastPosition = currentPosition;
      currentPosition = drivetrain.getPosition();
>>>>>>> Stashed changes
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
<<<<<<< Updated upstream
    return false;
=======
    return finished;
>>>>>>> Stashed changes
  }
}
