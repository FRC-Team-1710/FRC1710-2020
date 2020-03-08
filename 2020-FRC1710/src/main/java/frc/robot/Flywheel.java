/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Controls the flywheel on the shooter. Personally would name this Shooter.java
 */
public class Flywheel {
    public static CANSparkMax m_flyOne, m_flyTwo, m_feeder;
    public static CANEncoder e_flyOne, e_flyTwo, e_feeder;
    public static double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;
    public static MiniPID flywheelPID, feederPID;
    public static double flywheelSpeed, feederSpeed;
    
    public static void initShooter() {
        // Init motors
        m_flyOne = new CANSparkMax(8, MotorType.kBrushless);
        m_flyTwo = new CANSparkMax(9, MotorType.kBrushless);
        m_feeder = new CANSparkMax(11, MotorType.kBrushless);

        // Group and inverse secondary flywheel motor to primary flywheel motor.
        m_flyOne.follow(m_flyTwo, true);

        // Get encoders 
        e_flyOne = m_flyOne.getEncoder();
        e_flyTwo = m_flyTwo.getEncoder();
        e_feeder = m_feeder.getEncoder();
        
        m_flyOne.setIdleMode(IdleMode.kCoast);
        m_flyTwo.setIdleMode(IdleMode.kCoast);
        m_feeder.setIdleMode(IdleMode.kCoast);
        
        kP = .0002; 
        kI = .000000000001; // This is really small. Wonder looks like it's to compensate for steady-state error?
        // Review https://www.ni.com/en-us/innovations/white-papers/06/pid-theory-explained.html
        kD = 0; 
        kIz = 0; 
        kFF = 0; 
        kMaxOutput = 1; 
        kMinOutput = -1;
        maxRPM = 5700;
        
        flywheelPID = new MiniPID(kP,kI,kD); //values need to be changed
        feederPID = new MiniPID(kP,kI,kD); //values need to be changed
    }
    
    public static void setShooterSpeeds(double flywheelRPM, double feederRPM, boolean isOn) {
        if (isOn) {
            flywheelSpeed = flywheelPID.getOutput(-e_flyOne.getVelocity(), flywheelRPM);
            feederSpeed = feederPID.getOutput(e_feeder.getVelocity(), -feederRPM);
            
            m_flyTwo.set(flywheelSpeed);
            m_feeder.set(feederSpeed);
        } else {
            flywheelPID.reset();
            feederPID.reset();
            
            flywheelSpeed = flywheelPID.getOutput(e_flyOne.getVelocity(), 0);
            feederSpeed = feederPID.getOutput(e_feeder.getVelocity(), 0);
            
            m_flyTwo.set(0);
            m_feeder.set(0);
        }

        SmartDashboard.putNumber("pid-out", flywheelSpeed);
        SmartDashboard.putNumber("goal", flywheelRPM);
        SmartDashboard.putNumber("actual", e_flyOne.getVelocity());
    }

    public static double getFlySpeed(){
        return e_flyOne.getVelocity();
    }

    public static double getFeedSpeed(){
        return e_feeder.getVelocity();
    }
}
