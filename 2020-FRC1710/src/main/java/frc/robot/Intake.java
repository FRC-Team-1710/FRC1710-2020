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
import com.ctre.phoenix.motorcontrol.ControlFrameEnhanced;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
/**
 * Add your docs here.
 */
public class Intake {
    public static DoubleSolenoid s_intakeMaster, s_intakeSlave;
    public static TalonSRX  m_intake, m_elevator, m_indexer;
    /**
     *creates the motor instances
     **/
    public static void intakeInit(){
        m_intake = new TalonSRX(10);
        m_indexer = new TalonSRX(0);
        m_elevator = new TalonSRX(0);
        s_intakeMaster = new DoubleSolenoid(50, 0, 2);
        s_intakeSlave = new DoubleSolenoid(0, 5, 6);
    }

    /**
     *when true this extends the ground intake and runs things to get balls up the elevator
     **/
    public static void gIntake(boolean IsOn){
        if(IsOn){
            s_intakeMaster.set(Value.kForward);
            s_intakeSlave.set(Value.kReverse);
            m_intake.set(ControlMode.PercentOutput, 1);
            m_indexer.set(ControlMode.PercentOutput, 1);
            m_elevator.set(ControlMode.PercentOutput, 1);
        } else {
            s_intakeMaster.set(Value.kReverse);
            s_intakeSlave.set(Value.kForward);
            m_intake.set(ControlMode.PercentOutput, 0);
            m_indexer.set(ControlMode.PercentOutput, 0);
            m_elevator.set(ControlMode.PercentOutput, 0);
        }
        
    }

    

}
