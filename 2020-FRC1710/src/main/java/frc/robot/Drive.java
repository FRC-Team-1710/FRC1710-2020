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

import edu.wpi.first.wpilibj.Solenoid;
//import jdk.internal.jshell.tool.resources.version;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class Drive {
    public static CANSparkMax R1,R2, L1, L2;
    public static CANEncoder rDrive, lDrive;
    public static Solenoid shifters;
    public static double turnFactor, forwardFactor;
    public static InputSmoother leftIN, rightIN;
    public static void Driveinit(){
        R1 = new CANSparkMax(1, MotorType.kBrushless);
        R2 = new CANSparkMax(2, MotorType.kBrushless);
        L1 = new CANSparkMax(3, MotorType.kBrushless);
        L2 = new CANSparkMax(4, MotorType.kBrushless);
        R1.restoreFactoryDefaults();
        L2.restoreFactoryDefaults();
        L1.restoreFactoryDefaults();
        R2.restoreFactoryDefaults();
        turnFactor = .3;
        forwardFactor = .4;
        leftIN = new InputSmoother(1, -1);
        rightIN = new InputSmoother(1, -1);
        R2.follow(R1);
        L2.follow(L1);

        R1.setIdleMode(IdleMode.kBrake);
        R2.setIdleMode(IdleMode.kBrake);
        L1.setIdleMode(IdleMode.kBrake);
        L2.setIdleMode(IdleMode.kBrake);
    }
    public static void arcadeDrive(double forwardPower, double turnPower, boolean Boost){
        double speedScale = 0.4;
        if(Boost){
            speedScale = 1.0;
        } else {
            speedScale = 0.4;
        }
        forwardPower = speedScale * forwardPower;
        turnPower = turnPower * turnFactor;
        SmartDashboard.putNumber("FWD POWER!", forwardPower);
        SmartDashboard.putNumber("smooth FWD POWER!", leftIN.smoothInput(forwardPower));
        //SmartDashboard.putNumber(   "Velocity_X",           navx.getVelocityX());
        //  SmartDashboard.putNumber(   "Velocity_Y",           navx.getVelocityY());
        L1.set(leftIN.smoothInput(turnPower + forwardPower));
        R1.set(rightIN.smoothInput(turnPower - forwardPower));
    }
   
}
