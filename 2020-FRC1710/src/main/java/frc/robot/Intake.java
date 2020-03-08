/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
/**
 * Controls the intake system of the robot.
 */
public class Intake {
    public static TalonSRX m_intake, m_elevator_second, m_elevator_first;

    private static final double INTAKE_ON_SPEED = 1.00;
    private static final double INTAKE_OFF_SPEED = 0.0;

    private static final double ELEVATOR_ON_SPEED = 1.00;
    private static final double ELEVATOR_OFF_SPEED = 0.0;

    /**
     * Initalizes the intake motor controllers
     */
    public static void intakeInit() {
        m_intake = new TalonSRX(10);
        m_elevator_first = new TalonSRX(12);
        m_elevator_second = new TalonSRX(13);

        // Group the elevator motors together.
        m_elevator_second.follow(m_elevator_first);
    }

    /**
     * Turns on or off the intake depending on the parameter given
     * @param isOn (boolean) sets the intake on or off.
     */
    public static void setIntake(final boolean isOn) {
        m_intake.set(ControlMode.PercentOutput, isOn ? INTAKE_ON_SPEED : INTAKE_OFF_SPEED);
    }

    /**
     * Turns on or off the elevator depending on the parameter given
     * @param isOn (boolean) sets the intake on or off.
     */
    public static void setElevator(final boolean isOn) {
        m_elevator_first.set(ControlMode.PercentOutput, isOn ? ELEVATOR_ON_SPEED : ELEVATOR_OFF_SPEED);
    }
}
