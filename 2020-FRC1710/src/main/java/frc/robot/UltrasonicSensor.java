
package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Ultrasonic class used to find distance from shooet to wall.
 */
public class UltrasonicSensor {
    public static AnalogInput m_US;
    public static void initUltras(){
        m_US = new AnalogInput(0);
    }

    public static void UltrasVoid(){
        double sensorValue = m_US.getVoltage();
        final double scaleFactor = 1/(5./1024.); //scale converting voltage to distance
        double distance = 5*sensorValue*scaleFactor; //convert the voltage to distance
        SmartDashboard.putNumber("DB/Slider 0", distance); //write the value to the LabVIEW DriverStation
    }
    
}
