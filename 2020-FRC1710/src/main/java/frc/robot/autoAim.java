/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * This class reads data from the limelight and returns values to the SmartDashboard.
 */
public class autoAim {
    public static NetworkTable table;

    public static void aim() {
        // https://docs.wpilib.org/en/latest/docs/software/networktables/networktables-intro.html
        final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        final NetworkTableEntry tx = table.getEntry("tx");
        final NetworkTableEntry ty = table.getEntry("ty");
        final NetworkTableEntry ta = table.getEntry("ta");
        final NetworkTableEntry tv = table.getEntry("tv");

        // Read data from tables
        final double x = tx.getDouble(0.0);
        final double y = ty.getDouble(0.0);
        final double v = tv.getDouble(0.0);
        final double area = ta.getDouble(0.0);

        // Check if there is only one target from limelight data.
        SmartDashboard.putBoolean("target?", v == 1);
        SmartDashboard.putNumber("tv", v); // # number of targets

        // Put data to smart dashboard periodically
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
    }
}