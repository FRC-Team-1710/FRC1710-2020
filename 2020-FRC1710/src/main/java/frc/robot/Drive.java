/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;

/**
 * Add your docs here.
 */
public class Drive {
    public static CANSparkMax R1,R2, L1, L2;
    public static Solenoid shifters;
    public static double turnFactor, forwardFactor;
    public static InputSmoother leftIN, rightIN;
    public static void Driveinit(){
        CANSparkMax R1 = new CANSparkMax(1, MotorType.kBrushless);
        CANSparkMax R2 = new CANSparkMax(2, MotorType.kBrushless);
        CANSparkMax L1 = new CANSparkMax(3, MotorType.kBrushless);
        CANSparkMax L2 = new CANSparkMax(4, MotorType.kBrushless);
        turnFactor = .25;
        forwardFactor = .5;
        leftIN = new InputSmoother(1, -1);
        rightIN = new InputSmoother(1, -1);
        R2.follow(R1);
        L2.follow(L1);

        R1.setIdleMode(IdleMode.kBrake);
        R2.setIdleMode(IdleMode.kBrake);
        L1.setIdleMode(IdleMode.kBrake);
        L2.setIdleMode(IdleMode.kBrake);
    }
    public static void arcade_drive(double turnPower, double forwardPower, boolean elctricShift){
        if(elctricShift = true){
            forwardFactor = 1;
        } else {
            forwardFactor = .5;
        }
        Drive.R1.set(rightIN.smoothInput((turnPower * turnFactor) + forwardPower));
        Drive.L1.set(leftIN.smoothInput((turnPower * turnFactor) - forwardPower));
    }
}
