//PID code, used for autonomous to make the bot slow down/speed up depending on how close the bot is to the target point.

package frc.robot.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Robot;

public class PID {
    Autonomous auton = Robot.auton;

    final double ticksToFeet = 1.0/4096*(5.28*Math.PI)/12; // converts encoder ticks to feet

    double target = 0; //the distance the bot needs to travel ie. 10 feet

    double error = 0; // how much farther the bot has to travel to reach the target
    double errorSum = 0; // for the integral term, how much to increase the motor speed until bot overcomes friction
    double lastTimestamp = 0; // the last cycle accounted for
    double lastError = 0; // the error in the previous cycle
    double errorRate = 0; // the rate of speed taken in one cycle

    double encoderPositionLeft = 0; // the value of the left encoder in ticks
    double encoderPositionRight = 0; //the value of the right encoder in ticks

    final double kP = 0.05; // proportional term, multiplied by error to slow down as you approach the target
    final double kI = 0.1; // integral term, adds speed over time if bot is within 1 foot range of target
    final double kD = 0.01; // derivitive term, takes rate of speed and decreases it if robot will overtake the target at that speed

    double iLimit = 1; // how close the bot has to be to the target in order for the intergral term to have an effect
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
        error = target - (encoderPositionLeft + encoderPositionRight) / 2; // sets the error equal to the distance to go minus the distance travelled
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