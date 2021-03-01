/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Drivetrain;

public class GyroMechanumDrive extends CommandBase {

  Drivetrain m_drive;

  double kDrive, kStrafe, kTurn;
  double[] gyroData = new double[3];

  public GyroMechanumDrive(Drivetrain drive, double leftY, double leftX, double rightX) {
    addRequirements(drive);

    kDrive = leftY;
    kStrafe = leftX;
    kTurn = rightX;

    m_drive = drive;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    gyroData[0] = m_drive.getGyroData()[0];
    gyroData[1] = m_drive.getGyroData()[1];
    gyroData[2] = m_drive.getGyroData()[2];

    // this takes the gyro values and uses them to manipulate the controller input so the robot stays field-centric
    double temp = kDrive * Math.cos(gyroData[0]) + kStrafe * Math.sin(gyroData[0]);
    kStrafe = -kDrive * Math.sin(gyroData[0]) + kStrafe * Math.cos(gyroData[0]);
    kDrive = temp;

    // assigns each wheel the values it needs to drive, turn, and strafe correctly
    double fr = kDrive - kTurn - kStrafe;
    double fl = kDrive + kTurn + kStrafe;
    double rr = kDrive - kTurn + kStrafe;
    double rl = kDrive + kTurn - kStrafe;

    double max = Math.abs(fl);

    if(Math.abs(fr) > max) 
        max = Math.abs(fr);

    if(Math.abs(rl) > max) 
        max = Math.abs(rl); 

    if(Math.abs(rr) > max) 
        max = Math.abs(rr);

    if(max > 1) 
    {
      m_drive.setAllCartesian(fr/max, fl/max, rr/max, rl/max);
    } 
    else 
    {
      m_drive.setAllCartesian(fr, fl, rr, rl);
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.setAll(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
