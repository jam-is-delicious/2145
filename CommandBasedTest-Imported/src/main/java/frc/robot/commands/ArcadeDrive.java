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

public class ArcadeDrive extends CommandBase {
  
  Drivetrain m_drive;
  OI m_oi;

  double x, y;

  public ArcadeDrive(Drivetrain drive, OI oi) {
    addRequirements(drive, oi);

    m_drive = drive;
    m_oi = oi;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    x = m_oi.getAxisRaw(Controller.Pilot, OIConstants.R_STICK_X);
    y = m_oi.getAxisRaw(Controller.Pilot, OIConstants.L_STICK_Y);

    double left =  y + x;
    double right = y - x;

    double max = Math.abs(left);

    if(max < Math.abs(right))
      max = Math.abs(right);

    if(max > 1) {
      m_drive.setAllCartesian(right/max, left/max, right/max, left/max);
    } else {
      m_drive.setAllCartesian(right, left, right, left);
    }
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
