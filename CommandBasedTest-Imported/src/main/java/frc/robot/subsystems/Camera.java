// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Camera extends SubsystemBase {

  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");

  double x;
  double y;

  /** Creates a new Camera. */
  public Camera() {
    
  }

  @Override
  public void periodic() {
    x = tx.getDouble(0.0);
    y = ty.getDouble(0.0);

    SmartDashboard.putNumber("camX", x);
    SmartDashboard.putNumber("camY", y);
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }
}
