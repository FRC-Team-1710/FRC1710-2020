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
    public static CANSparkMax R1, R2, L1, L2;
    public static Solenoid shifters;

    private final static double TURN_FACTOR = 0.3;
    private final static double SPEED_FACTOR = 0.4;
    private final static double BOOST_FACTOR = 1.0;

    public static void Driveinit(){
        R1 = new CANSparkMax(1, MotorType.kBrushless);
        R2 = new CANSparkMax(2, MotorType.kBrushless);
        L1 = new CANSparkMax(3, MotorType.kBrushless);
        L2 = new CANSparkMax(4, MotorType.kBrushless);
    
        R2.follow(R1);
        L2.follow(L1);

        /**
         * Instead of smoothing input values
         * psuedo-set acceleration limits by smoothing output (ie - ramp rate)
         * 
         * This is the time in seconds it takes to go from 0% to 100% power output/throttle on the motor controllers. 
         * Good way to fake an acceleration limit while leaving inputs as responsive as possible.
         * 
         * If we need to accelerate quicker, decrease the ramp rate value.
         * If we risk tipping, increase the ramp rate value
         * 
         * Ideally this would be set as low as possible without a risk of tipping when driving.
         */
        double rampRate = 1.0; //seconds
        R1.setOpenLoopRampRate(rampRate);
        R2.setOpenLoopRampRate(rampRate);
        L1.setOpenLoopRampRate(rampRate);
        L2.setOpenLoopRampRate(rampRate);        
        R1.setClosedLoopRampRate(rampRate);
        R2.setClosedLoopRampRate(rampRate);
        L1.setClosedLoopRampRate(rampRate);
        L2.setClosedLoopRampRate(rampRate);

        R1.setIdleMode(IdleMode.kBrake);
        R2.setIdleMode(IdleMode.kBrake);
        L1.setIdleMode(IdleMode.kBrake);
        L2.setIdleMode(IdleMode.kBrake);
    }
    public static void arcadeDrive(double forwardPower, double turnPower, boolean boost){
        double forward = forwardPower * (boost ? BOOST_FACTOR : SPEED_FACTOR);
        double turn = turnPower * TURN_FACTOR;

        L1.set(turn + forward);
        R1.set(turn - forward);
    }
}
