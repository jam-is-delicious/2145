/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OIConstants;
import frc.robot.OI.Controller;
import frc.robot.commands.*;
import frc.robot.subsystems.*;


public class RobotContainer {

  private final Drivetrain drive;
  private final Turret turret;
  private final OI oi;

  public RobotContainer() {

    drive = new Drivetrain();
    turret = new Turret();
    oi = new OI();

    drive.setDefaultCommand(new GyroMechanumDrive(drive, oi.getAxisRaw(Controller.Pilot, OIConstants.L_STICK_Y), oi.getAxisRaw(Controller.Pilot, OIConstants.L_STICK_X), oi.getAxisRaw(Controller.Pilot, OIConstants.R_STICK_X)));

    configureButtonBindings();
  }

  private void configureButtonBindings() {

    oi.aButton.and(oi.xButton).whenActive(new DriveSequence(drive, 1, 2, 3, 0.5));
    oi.xButton.whenPressed(new TurretTurnToFront(drive, turret));


  }

  public Command getAutonomousCommand() {
    return new DriveForSeconds(drive, 2, 0.25);
  }
}
