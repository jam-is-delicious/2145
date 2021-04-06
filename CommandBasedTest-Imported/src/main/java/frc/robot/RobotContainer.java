/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.*;
import frc.robot.subsystems.*;


public class RobotContainer {

  private final Drivetrain drive;
  private final OI oi;
  private final Shooter shooter;
  private final Camera cam;

  public RobotContainer() {

    drive = new Drivetrain();
    oi = new OI();
    shooter = new Shooter();
    cam = new Camera();

    drive.setDefaultCommand(new ArcadeDrive(drive, oi));

    configureButtonBindings();
  }

  private void configureButtonBindings() 
  {
    oi.xButton.whenPressed(new SequentialCommandGroup(new TurnToCameraCenter(cam, drive), new ShootToGoal(cam, shooter)));
  }

  public Command getAutonomousCommand() 
  {
    return new DriveForSeconds(drive, 2, 0.25);
  }
}
