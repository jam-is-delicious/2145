/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveInCircle.CircleDirection;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class DriveSequence extends SequentialCommandGroup {
  /**
   * Creates a new DriveSequence.
   */
  public DriveSequence(Drivetrain drive, double speed) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new DriveInCircle(drive, speed, 0, 3, 5, CircleDirection.CounterClock),

    new DriveInCircle(drive, speed, 180, 360, 5, CircleDirection.Clock));
  }
}
