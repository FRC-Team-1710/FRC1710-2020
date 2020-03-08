/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climber {
    public static TalonSRX climb1, climb2;
    public static DoubleSolenoid brake;
    public static PowerDistributionPanel myPDP;

    // Make the forward and backward values easier to understand.
    private static final Value BRAKE_OFF = Value.kReverse;
    private static final Value BRAKE_ON = Value.kForward;

    // Brake toggle to disable motor input once it is engaged
    private static boolean brakeIsEngaged;

    /**
     * This initalizes the climber motors and the pneumatic brake solinoid.
     */
    public static void climberInit() {
        climb1 = new TalonSRX(5);
        climb2 = new TalonSRX(6);

        // Rest climber motor controllers to factory settings.
        // Unsure why we are doing this. Commented out for now.
        // climb1.configFactoryDefault();
        // climb2.configFactoryDefault();

        // Group climber motors together
        climb2.follow(climb1);

        // Initalize brake solinoids and PDP for power readout.
        brake = new DoubleSolenoid(51, 1, 6);
        myPDP = new PowerDistributionPanel();

        brake.set(BRAKE_OFF);
        brakeIsEngaged = false;
    }

    /**
     * Sets the climber to the given power and when passed the flag to turn on the brake there is no going back
     * @param climbPower (double) - Sets the climber motor power output to the given value
     * @param turnBrakeOn (boolean) - Toggles the flag to engage the brake and stop climber motors
     */
    public static void setClimber(final double climbPower, final boolean turnBrakeOn) {
        // If brake is engaged we don't allow any power to the climber.
        // Else if brake is not engaged we set it to the power passed in.
        climb1.set(ControlMode.PercentOutput, brakeIsEngaged ? 0 : climbPower);

        SmartDashboard.putNumber("Climb Motors Current(Amps)", myPDP.getCurrent(11));

        // Toggle brake on. 
        if (turnBrakeOn) { 
            brake.set(BRAKE_ON);
            brakeIsEngaged = true;
        }
    }
}
