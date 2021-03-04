// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.Vector2d;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.MathConstants;
import frc.robot.Constants.PIDConstants;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class DriveForDistancePID extends PIDCommand {
  /** Creates a new DriveForDistance. */
  public DriveForDistancePID(Drivetrain drive, double speed, double angle, double distance) {
    super(
        // The controller that the command will use
        new PIDController(PIDConstants.kP, PIDConstants.kI, PIDConstants.kD),
        // This should return the measurement
        () -> drive.getDrivetrainRelativePosition().magnitude() * MathConstants.INCHES_TO_FEET,
        // This should return the setpoint (can also be a constant)
        () -> distance * MathConstants.INCHES_TO_FEET,
        // This uses the output
        output -> {
          Vector2d movementVector = MathConstants.AngleToVector(angle);
          movementVector = new Vector2d(movementVector.x * output * speed, movementVector.y * output * speed);
          drive.setWithVector(movementVector);
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.

    addRequirements(drive);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}