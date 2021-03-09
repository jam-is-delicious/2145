/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.OI;
import frc.robot.subsystems.OI.Controller;

public class GyroMechanumDrive extends CommandBase {

  Drivetrain m_drive;
  OI m_oi;

  double kDrive, kStrafe, kTurn;
  double gyroYaw;
  double speed;

  public GyroMechanumDrive(Drivetrain drive, OI oi, double k_speed) {
    addRequirements(drive, oi);
    m_drive = drive;
    m_oi = oi;
    speed = k_speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    kDrive = -m_oi.getAxisRaw(Controller.Pilot, OIConstants.L_STICK_Y);
    kStrafe = m_oi.getAxisRaw(Controller.Pilot, OIConstants.L_STICK_X);
    kTurn = m_oi.getAxisRaw(Controller.Pilot, OIConstants.R_STICK_X);

    gyroYaw = m_drive.getGyroAngle();

    // this takes the gyro values and uses them to manipulate the controller input so the robot stays field-centric
    /*double temp = kDrive * Math.cos(gyroYaw) + kStrafe * Math.sin(gyroYaw);
    kStrafe = -kDrive * Math.sin(gyroYaw) + kStrafe * Math.cos(gyroYaw);
    kDrive = temp;*/

    // assigns each wheel the values it needs to drive, turn, and strafe correctly
    double fr = kDrive - kTurn + kStrafe;
    double fl = kDrive + kTurn - kStrafe;
    double rr = kDrive - kTurn - kStrafe;
    double rl = kDrive + kTurn + kStrafe;

    double max = Math.abs(fl);

    if(Math.abs(fr) > max) 
        max = Math.abs(fr);

    if(Math.abs(rl) > max) 
        max = Math.abs(rl); 

    if(Math.abs(rr) > max) 
        max = Math.abs(rr);
    
    System.out.println("FR: " + fr/max);
    System.out.println("FL: " + fl/max);
    System.out.println("RR: " + rr/max);
    System.out.println("RL: " + rl/max);

    if(max > 1) 
    {
      m_drive.setAllCartesian((fr/max)*speed, (fl/max)*speed, (rr/max)*speed, (rl/max)*speed);
    } 
    else 
    {
      m_drive.setAllCartesian(fr*speed, fl*speed, rr*speed, rl*speed);
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
