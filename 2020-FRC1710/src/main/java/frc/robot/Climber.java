/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
/**
 * Add your docs here.
 */
import com.ctre.phoenix.motorcontrol.NeutralMode;
public class Climber {
    public static TalonSRX climb1, climb2;
    public static double hexZero, hexPos;
    public static ErrorCode zero;
    public static Compressor compressor;
    public static DoubleSolenoid brake;
    public static PowerDistributionPanel myPDP;
    public static void climberInit(){
        climb1 = new TalonSRX(5);
        climb2 = new TalonSRX(6);
        climb1.configFactoryDefault();
        climb2.configFactoryDefault();
        int hexZero = climb2.getSensorCollection().getPulseWidthPosition();
        climb2.setSelectedSensorPosition(0,0,0);
        climb2.follow(climb1);
        brake = new DoubleSolenoid(51, 1, 6);
        myPDP = new PowerDistributionPanel();
        
    
    // compressor
        compressor = new Compressor(51);
        
    }
    public static void climb(double pwr, boolean brakeBol,boolean Compress){
        climb1.set(ControlMode.PercentOutput, pwr * 1);
        double hexPos = climb2.getSelectedSensorPosition(0);
        SmartDashboard.putNumber("position in rotations", hexPos/4000);
        SmartDashboard.putNumber("raw",  hexPos);
        SmartDashboard.putNumber("current draw from climb motors", myPDP.getCurrent(11));
        compressor.setClosedLoopControl(Compress);
        if (brakeBol) { brake.set(Value.kForward);} else {brake.set(Value.kReverse);}

    }
}
