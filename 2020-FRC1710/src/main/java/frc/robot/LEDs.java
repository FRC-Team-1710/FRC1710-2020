/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
import edu.wpi.first.wpilibj.SerialPort;



public class LEDs {
    public static SerialPort serialPort;
    public static void initLEDs(){
        serialPort = new SerialPort(9600, SerialPort.Port.kUSB);
    }
    public static void sendRAWStatusCode(String statusCode){
        serialPort.writeString(statusCode);   
    }
    public static void setBlueALliance(){
        serialPort.writeString("2");   
    }
    public static void setRedALliance(){
        serialPort.writeString("3");   
    }
    public static void setIncramentBall(){
        serialPort.writeString("4");   
    }
    public static void setDecramentBall(){
        serialPort.writeString("5");   
    }
    
}
