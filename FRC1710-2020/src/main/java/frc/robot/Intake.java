/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DoubleSolenoid;



public class Intake {
    // Introducing CANSparkMax Motors
    public static CANSparkMax intakeWheels;

    // Introducing DoubleSolenoids
    public static DoubleSolenoid clawMover, piston2;
    // DoubleSolenoid CANID
    public static int PCM_CAN_ID = 16;
   

    // intakes ball from bottom or floor
    public static void IntakeBall(double power) {
        intakeWheels.set(power);
        
    }

    

    public static void Intakeinit() {
        clawMover = new DoubleSolenoid(PCM_CAN_ID, 0, 7);
        piston2 = new DoubleSolenoid(PCM_CAN_ID, 0, 6);
        
    }
   


}


