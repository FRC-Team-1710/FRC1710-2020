/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

// import com.revrobotics.ColorSensorV3.RawColor;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

// import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ColorWheel {
  // Class is intended to interact with {other classes}
  // This class should provide {color value? etc.}
  public static VictorSPX panelWheel; // names motor
 // public static int PCM_CAN_ID = 21;
  // public final ColorMatch m_ColorMatcher = new ColorMatch();
  public static ColorSensorV3 colorSensor;
  public static Color detectedColor;
  public static Color previousColor;
  public static Color startColor;
  public static ColorMatch colorMatcher;


  public final static Color defaultColor = ColorMatch.makeColor(0, 0, 0);
  public final static Color kBlueTarget = ColorMatch.makeColor(0.145, 0.438, 0.416); // Check color values at competition
  public final static Color kGreenTarget = ColorMatch.makeColor(0.183, 0.564, 0.253);
  public final static Color kRedTarget = ColorMatch.makeColor(0.488, 0.363, 0.149);
  public final static Color kYellowTarget = ColorMatch.makeColor(0.321, 0.547, 0.132);
  public static int counter;
  

  public static void ColorWheelinit() {
    colorMatcher = new ColorMatch();
    colorMatcher.addColorMatch(kBlueTarget); // matches to blue
    colorMatcher.addColorMatch(kGreenTarget); // matches to green
    colorMatcher.addColorMatch(kRedTarget); // matches to red
    colorMatcher.addColorMatch(kYellowTarget); // matches to yellow
    colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
    panelWheel = new VictorSPX(21);
    counter = 0;
    //alreadyCountedColor = false;
    //isFinishedStage2 = false;
    previousColor = defaultColor;
    startColor = defaultColor;
    
  }

  public static void setInitalColor() {
    // defaultColor = colorMatcher.matchClosestColor(colorSensor.getColor()).color;
  }

  public static boolean RunStage2() {
    detectedColor = colorSensor.getColor();
    //System.out.println("detectedColor " + detectedColor.red + ", " + detectedColor.green + ", " + detectedColor.blue );

    ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);
    // System.out.println("detectedColor " + match.color.red + ", " + match.color.green + ", " + match.color.blue + ", " + match.confidence);

    if (match.confidence > 0.96) {
      System.out.println(match.confidence);

      if (startColor == defaultColor) {
        startColor = match.color;
        // System.out.println(startColor);
        panelWheel.set(ControlMode.PercentOutput, 0.4);
        // double YES = 7;
        // SmartDashboard.putNumber("Working", YES); 
        
        
      }

      if (previousColor != match.color) {
        if (previousColor == startColor) {
          counter++;
          System.out.println(counter);
          if (counter >= 8) {
            panelWheel.set(ControlMode.PercentOutput, 0);
            return false;
          }
        
       }
      }
      previousColor = match.color;
    }
    return true;
  }
    
    
    
  //   if (isFinishedStage2 == false) {  
  //     panelWheel.set(ControlMode.PercentOutput, 0.5);
      
  //     if (match.color == detectedColor) { //kRedTarget
  //       if (!alreadyCountedColor) { 
  //         counter++;
  //       }
  //       alreadyCountedColor = true;
  //     } else { // if (colorSensor.getColor() != kRedTarget) // != kRedTarget
  //       alreadyCountedColor = false;
  //     }
  //     if (counter >= 7) { // Counts red color seven times and then the counter goes up one
  //       if (match.color != detectedColor) {
  //         isFinishedStage2 = true;
  //       }
  //     } 
  //   } else { // if (isFinishedStage2)
  //     panelWheel.set(ControlMode.PercentOutput, 0); // sets  motor to 0 after counter reaches 7
  //   }
  // }


   public static boolean RunStage3() {
     boolean returnValue;
     returnValue = true;
     detectedColor = colorSensor.getColor();
     ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);
     if (match.confidence > 0.96) {

      String gameData; 
      gameData = DriverStation.getInstance().getGameSpecificMessage();
      if(gameData.length() > 0) {
        switch (gameData.charAt(0)) {

          case 'B' :
           System.out.println("detectedColor " + detectedColor.red + ", " + detectedColor.green + ", " + detectedColor.blue );

            if (match.color == kRedTarget) {
              panelWheel.set(ControlMode.PercentOutput, 0);
            } else {
              panelWheel.set(ControlMode.PercentOutput, 0.4);
              returnValue = false;
            }   
            break;     
          
          case 'G' :
           if (match.color == kYellowTarget) {
              panelWheel.set(ControlMode.PercentOutput, 0);  
          } else {
              panelWheel.set(ControlMode.PercentOutput, 0.4);
              returnValue = false;
          } 
          break;   

          case 'R' :
           if (match.color == kBlueTarget) {
              panelWheel.set(ControlMode.PercentOutput, 0);
            } else {
              panelWheel.set(ControlMode.PercentOutput, 0.4);
              returnValue = false;
          }
          break;  

          case 'Y' :
           if (match.color == kGreenTarget) {
              panelWheel.set(ControlMode.PercentOutput, 0);
           } else {
              panelWheel.set(ControlMode.PercentOutput, 0.4);
              returnValue = false;
           }
           break;  
          
          default :
          // This is corrupt data
             break;

}
}
}
    return returnValue;
}
   }


