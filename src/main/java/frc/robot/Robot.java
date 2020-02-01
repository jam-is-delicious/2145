/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.drive.Drivetrain;
import frc.robot.app.*;

public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  public static Drivetrain drive;
  public static Camera cam;
  private Door door;
  public static OI oi;
  private Conveyor conv;
  private Lift lift;
  private Autonomous auton;

  private double startTime;
  public double autonTime;

  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    drive = new Drivetrain();
    Robot.oi = new OI();
    cam = new Camera();
    door = new Door();
    conv = new Conveyor();
    lift = new Lift();
    auton = new Autonomous();

    try {
      door.init();
      cam.init();
      drive.init();
      lift.init();
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    door.init();
    cam.init();
    drive.init();
    lift.init();

    startTime = Timer.getFPGATimestamp();
    
  }

  @Override
  public void robotPeriodic() {
    cam.run();
  }

  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);

    autonTime = Timer.getFPGATimestamp();
  }

  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        auton.autoLine();
        break;
    }

    if(autonTime - startTime < 3) {
      drive.motors[0][0].set(ControlMode.PercentOutput, 0.6);
      drive.motors[0][1].set(ControlMode.PercentOutput, 0.6);
    }
  }

  @Override
  public void teleopPeriodic() {
    drive.run();
    door.run();
    conv.run();
    lift.run();
}

  @Override
  public void testPeriodic() {
  }
}
