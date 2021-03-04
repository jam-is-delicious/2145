// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class DriveAndTurn extends ParallelCommandGroup {
  /** Creates a new DriveAndTurn. 
   * @param drive Drivetrain subsystem
   * @param speed Command operating speed in percent
   * @param angleToDrive Angle to drive in relative to the field
   * @param angleToFace Angle for the bot to face relative to the field
   * @param distance Distance for the bot to travel
   * @param gyroYaw Yaw value on the bot's gyroscope
  */
  public DriveAndTurn(Drivetrain drive, double speed, double angleToDrive, double angleToFace, double distance, double gyroYaw) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new DriveForDistance(drive, speed, angleToDrive, distance, gyroYaw),
      new TurnToAngle(angleToFace, speed, drive)
    );
  }
}
