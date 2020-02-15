/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.drive.Drivetrain;
import frc.robot.auto.PID;

public class AutoLine extends CommandBase {
  private final Drivetrain driveSubsystem;
  private final PID pid;

  boolean completed;
  
  /**
   * Creates a new AutoLine.
   */
  public AutoLine(Drivetrain dSub, PID pidSub, double target) {
    driveSubsystem = dSub;
    pid = pidSub;
    pid.target = target;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveSubsystem);
    addRequirements(pid);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    pid.calculate();

    driveSubsystem.set(pid.calculate(), pid.calculate());

    if(pid.error < 0.01)
      completed = true;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveSubsystem.set(0d, 0d);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return completed;
  }
}
