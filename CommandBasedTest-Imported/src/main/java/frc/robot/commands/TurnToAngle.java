// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.PIDConstants;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TurnToAngle extends PIDCommand {
  /** Creates a new TurnToAngle. */
  public TurnToAngle(double targetAngleInDegrees, double currentAngle, Drivetrain drivetrainSubsystem) {
    super(
        // The controller that the command will use
        new PIDController(PIDConstants.kP, PIDConstants.kI, PIDConstants.kD),
        // This should return the measurement
        () -> drivetrainSubsystem.getGyroData()[0] - currentAngle,
        // This should return the setpoint (can also be a constant)
        () -> targetAngleInDegrees,
        // This uses the output
        output -> {
          drivetrainSubsystem.setTurn(output);
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    addRequirements(drivetrainSubsystem);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
