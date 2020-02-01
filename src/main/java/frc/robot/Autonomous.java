package frc.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

public class Autonomous {
    private final int[][] motorID = { {0, 1}, {2, 3} }; // motor ids for the motor controllers
    private CANSparkMax[][] motors = { {null, null}, {null, null} }; // actual motor controllers, not defined yet
    private CANEncoder[][] encoders = {{null, null}, {null, null}};

    final double ticksToFeet = 1.0/4096*(5.28*Math.PI)/12;
    double setPoint = 0;
    double PosL = 0;
    double PosR = 0;


    public Autonomous() {
        for (int a = 0; a < 2; a++) {                       // nested for loop (a "2D" loop, essentially) that assignes a variable to each motor based on position on the robot (a = left & right, b = front & back)
            for (int b = 0; b < 2; b++) {
                motors[a][b] = new CANSparkMax(motorID[a][b], MotorType.kBrushless);
                encoders[a][b] = motors[a][b].getEncoder();
            }
        }
        motors[0][1].follow(motors[0][0]); // makes the rear-left motor (the left "slave") follow the front-left motor (the left "master")
        motors[1][1].follow(motors[1][0]); // same as above for the right side
    }

    void run() {
        PosL = encoders[0][0].getPosition() * ticksToFeet;
        PosR = encoders[1][0].getPosition() * ticksToFeet;
    }

    void displayNumbers(){
        SmartDashboard.putNumber("Left Encoder Position", PosL);
        SmartDashboard.putNumber("Right Encoder Position", PosR);
    }

    void autoLine() {
        setPoint = 10;
    }
}