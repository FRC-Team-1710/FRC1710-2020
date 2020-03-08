/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
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
 * Limelight class. 
 * Depends on the Flywheel.java class in order to accomplish auto-shooting.
 */
public class Limelight {
    //shooting anywhere on field
    public static double rpm;
    public NetworkTable shooterTable;
    public NetworkTable intakeTable;
    
    public static void initShooter() {
        Flywheel.initShooter();
    }

    public static void limelight_periodic() {
        //shooting
        NetworkTable shooterTable = NetworkTableInstance.getDefault().getTable("limelight-shooter");
        NetworkTableEntry txShooter = shooterTable.getEntry("tx");
        NetworkTableEntry tyShooter = shooterTable.getEntry("ty");
        NetworkTableEntry taShooter = shooterTable.getEntry("ta");
        NetworkTableEntry tvShooter = shooterTable.getEntry("tv");
      
        //read values periodically
        double xShooter = txShooter.getDouble(0.0);
        double yShooter = tyShooter.getDouble(0.0);
        double areaShooter = taShooter.getDouble(0.0);
        double validShooter = tvShooter.getDouble(0.0);
      
        //post to smart dashboard periodically
        SmartDashboard.putNumber("ShooterLimelightX", xShooter);
        SmartDashboard.putNumber("ShooterLimelightY", yShooter);
        SmartDashboard.putNumber("ShooterLimelightArea", areaShooter);
        SmartDashboard.putNumber("ShooterifTarget", validShooter);

        //calculating distnace and rpm of flywheel
        double d = 98.25 - 36 / Math.tan(yShooter);
        SmartDashboard.putNumber("ShooterDistance", d);
        double v;

        if (validShooter == 1) {
        //figuring out what rpm for flywheel
            if (d >= 110 && d <= 180) {
                v = 0.0454 * d * d - 14.899 * d + 1610.3;
                rpm = v * 60 / (3.1415 * 4); //c of wheel (in)
            } else if (d >= 190 && d <= 450) {
                v = 0.377 * d + 313.75;
                rpm = v * 60 / (3.1415 * 4); //c of wheel (in)
            } else if (rpm > 5700) {
                rpm = 5700;
            }

            Flywheel.setShooterSpeeds(0, 0, false);
            SmartDashboard.putNumber("ShooterRPM", rpm);
        } else {
            rpm = 0;
        }
        
        //driving to loading station
        NetworkTable intakeTable = NetworkTableInstance.getDefault().getTable("limelight-intake");
        NetworkTableEntry txIntake = intakeTable.getEntry("tx");
        NetworkTableEntry tyIntake = intakeTable.getEntry("ty");
        NetworkTableEntry taIntake = intakeTable.getEntry("ta");
        NetworkTableEntry tvIntake = intakeTable.getEntry("tv");
      
        //read values periodically
        double xIntake = txIntake.getDouble(0.0);
        double yIntake = tyIntake.getDouble(0.0);
        double areaIntake = taIntake.getDouble(0.0);
        double validIntake = tvIntake.getDouble(0.0);
      
        //post to smart dashboard periodically
        SmartDashboard.putNumber("IntakeLimelightX", xIntake);
        SmartDashboard.putNumber("IntakeLimelightY", yIntake);
        SmartDashboard.putNumber("IntakeLimelightArea", areaIntake);
        SmartDashboard.putNumber("IntakeifTarget", validIntake);
    }
}

