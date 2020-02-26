/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import org.graalvm.compiler.graph.spi.Canonicalizable.Binary;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
/**
 * Add your docs here.
 */
public class autoAim {
    public static NetworkTable table;
    public static boolean oo;
    public static void initAutoAim(){
        
    }

public static void aim(){
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ty = table.getEntry("ty");
        NetworkTableEntry ta = table.getEntry("ta");
        NetworkTableEntry tv = table.getEntry("tv");
        //read values periodically
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);
        
        if(1==tv.getDouble(0.0)){
            boolean oo = true;
        } else {
            boolean oo = false;
        }
        SmartDashboard.putBoolean("target?", oo);
        SmartDashboard.putNumber("tv", tv.getDouble(0.0));
        //post to smart dashboard periodically
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
}

}
