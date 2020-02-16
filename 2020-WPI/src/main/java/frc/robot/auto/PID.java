//PID code, used for autonomous to make the bot slow down/speed up depending on how close the bot is to the target point.

package frc.robot.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Robot;
import frc.robot.drive.*;

public class PID extends SubsystemBase {
    Drivetrain drive = Robot.drive;

    final double ticksToFeet = 1.0/4096*(5.28*Math.PI)/12; // converts encoder ticks to feet

    public double target = 0; //the distance the bot needs to travel ie. 10 feet  
    double error = 0; // how much farther the bot has to travel to reach the target
    double dt = 0; //the time change from the last timestamp for the errorRate

    // P Terms
    double encoderPositionLeft = 0; // the value of the left encoder in ticks
    double encoderPositionRight = 0; //the value of the right encoder in ticks

    // I Terms
    double errorSum = 0; // for the integral term, how much to increase the motor speed until bot overcomes friction
    double lastTimestamp = 0; // the last cycle accounted for
    double iLimit = 1; // how close the bot has to be to the target in order for the intergral term to have an effect

    // D Terms
    double lastError = 0; // the error in the previous cycle
    double errorRate = 0; // the rate of speed taken in one cycle


    // Constants
    final double kP = 0.05; // proportional term, multiplied by error to slow down as you approach the target
    final double kI = 0.1; // integral term, adds speed over time if bot is within 1 foot range of target
    final double kD = 0.01; // derivitive term, takes rate of speed and decreases it if robot will overtake the target at that speed

    double outputSpeed = 0; //output speed of the motor

    public void init() {
        lastTimestamp = Timer.getFPGATimestamp(); //sets the last timestamp
    }

    public void run(){
        displayNumbers();
    }

    void displayNumbers(){
        SmartDashboard.putNumber("Left Encoder Position", encoderPositionLeft); //displays the encoder position on SmartDashboard
        SmartDashboard.putNumber("Right Encoder Position", encoderPositionRight);
    }

    double P() { //proportional
        error = target - (encoderPositionLeft + encoderPositionRight) / 2; // sets the error equal to the distance to go minus the distance travelled
        return kP*error;
    }

    double I() { //intergral
        dt = Timer.getFPGATimestamp() - lastTimestamp;

        if(Math.abs(error)<=iLimit) { //adds up the small amounts of error overtime to overcome small forces like friction to go the full distance
            errorSum += dt*error;
        }

        return kI*errorSum;
    }

    double D() { //derivative
        errorRate = (error-lastError)/dt; //sets the error rate so the intergral doesn't add up too quickly until it's needed
        return kD * errorRate;
    }

    public double getError() {
        return error;
    }

    public double calculate(double targetPos){
        encoderPositionLeft = drive.getLeftEncoderTicks() * ticksToFeet; // will get the values of the encoders and convert them to feet
        encoderPositionRight = drive.getRightEncoderTicks() * ticksToFeet;
        target = targetPos;

        outputSpeed = P() + I() + D(); //puts in all parts of PID for the final output speed
        lastTimestamp = Timer.getFPGATimestamp();
        lastError = error;
        return outputSpeed;
    }
}