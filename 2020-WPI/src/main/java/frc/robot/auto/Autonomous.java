
package frc.robot.auto;

import frc.robot.Robot;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

public class Autonomous {
    private final int[][] motorID = { {0, 1}, {2, 3} }; // motor ids for the motor controllers
    private CANSparkMax[][] motors = { {null, null}, {null, null} }; // actual motor controllers, not defined yet
    public CANEncoder[][] encoders = {{null, null}, {null, null}};

    public PID pid = Robot.pid;

    public Autonomous() {
        for (int a = 0; a < 2; a++) {                       // nested for loop (a "2D" loop, essentially) that assignes a variable to each motor based on position on the robot (a = left & right, b = front & back)
            for (int b = 0; b < 2; b++) {
                motors[a][b] = new CANSparkMax(motorID[a][b], MotorType.kBrushless);
                encoders[a][b] = motors[a][b].getEncoder();
            }
        }
        motors[0][1].follow(motors[0][0]); // makes the rear-left motor (the left "slave") follow the front-left motor (the left "masster")
        motors[1][1].follow(motors[1][0]); // same as above for the right side
    }

    public void run() {
        autoLine();
    }

    void autoLine() {
        pid.target = 10;
    }

    void backToStart() {
        pid.target = 0;
    }
}
