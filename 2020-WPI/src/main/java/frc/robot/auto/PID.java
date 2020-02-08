//PID code, used for autonomous to make the bot slow down/speed up depending on how close the bot is to the target point.

package frc.robot.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Robot;

public class PID {
    Autonomous auton = Robot.auton;

    final double ticksToFeet = 1.0/4096*(5.28*Math.PI)/12; //converts motor ticks to feet

    double target = 0; //the distance the bot needs to travel ie. 10 feet

    double errorSum = 0; //the sum of the error
    double lastTimestamp = 0; //the last second accounted for
    double lastError = 0; //the distance already travelled 
    double errorRate = 0; // the amount of distance still needed to go while the bot is going for the integral within PID

    double encoderPositionLeft = 0; //the value of the left encoder
    double encoderPositionRight = 0; //the value of the right encoder

    final double kP = 0.05; //change this value till it works. its used in calculating the error for the distance needed to travel
    final double kI = 0.1; //change this value till it works. it makes the errorRate build up over time for the intergral
    final double kD = 0.01; //change this value till it works. Turns down the errorRate so the effects of the intergral isn't seen until the bot is close to the target distance

    double iLimit = 1; //how close the bot has to be to the target inorder for the intergral to kick in
    double error = 0; //how much farther the bot has to travel to reach the target
    double dt = 0; //the time change from the last timestamp for the errorRate
    double outputSpeed = 0; //output speed of the motor

    public void init() {
        lastTimestamp = Timer.getFPGATimestamp(); //sets the last timestamp
    }

    public void run(){
        encoderPositionLeft = auton.encoders[0][0].getPosition() * ticksToFeet; // will get the values of the encoders and convert them to feet
        encoderPositionRight = auton.encoders[1][0].getPosition() * ticksToFeet;

        P();
        I();
        D();
        calculate();
        displayNumbers();
    }

    void displayNumbers(){
        SmartDashboard.putNumber("Left Encoder Position", encoderPositionLeft); //displays the encoder position on SmartDashboard
        SmartDashboard.putNumber("Right Encoder Position", encoderPositionRight);
    }

    void P() { //proportional
        error = target - (encoderPositionLeft + encoderPositionRight) / 2; //sets the error
    }

    void I() { //intergral
        dt = Timer.getFPGATimestamp() - lastTimestamp;

        if(Math.abs(error)<=iLimit) { //adds up the small amounts of error overtime to overcome small forces like friction to go the full distance
            errorSum += dt*error;
        }
    }

    void D() { //derivative
        errorRate = (error-lastError)/dt; //sets the error rate so the intergral doesn't add up too quickly until it's needed
    }

    public double calculate(){
        outputSpeed = kP*error + kI*errorSum + kD*errorRate; //puts in all parts of PID for the final output speed
        lastTimestamp = Timer.getFPGATimestamp();
        lastError = error;
        return outputSpeed;
    }
}