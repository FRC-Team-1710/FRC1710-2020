package frc.robot;

import edu.wpi.first.wpilibj.I2C;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorMatchResult;
import edu.wpi.first.wpilibj.DriverStation;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ColorWheel {
  //public static int Clockwise = 1;
  public static int Counterclockwise = -1;
  public static double Speed = 0.4;
  public static TalonSRX panelWheel; // names motor
  // public final ColorMatch m_ColorMatcher = new ColorMatch();
  public static ColorSensorV3 colorSensor;
  public static Color detectedColor;
  public static Color previousColor;
  public static Color startColor;
  public static ColorMatch colorMatcher;


  public final static Color defaultColor = ColorMatch.makeColor(0, 0, 0); // Still check values at every competition
  public final static Color kBlueTarget = ColorMatch.makeColor(0.145, 0.438, 0.416); // (0,255,255)
  public final static Color kGreenTarget = ColorMatch.makeColor(0.192, 0.558, 0.250); // (0,255,0)
  public final static Color kRedTarget = ColorMatch.makeColor(0.459, 0.367, 0.174); // (255,0,0)
  public final static Color kYellowTarget = ColorMatch.makeColor(0.322, 0.555, 0.122); // (255,255,0)
  public static int counter, elseCounter;
  

  public static void ColorWheelinit() {
    colorMatcher = new ColorMatch();
    colorMatcher.addColorMatch(kBlueTarget); // matches to blue
    colorMatcher.addColorMatch(kGreenTarget); // matches to green
    colorMatcher.addColorMatch(kRedTarget); // matches to red
    colorMatcher.addColorMatch(kYellowTarget); // matches to yellow
    colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
    panelWheel = new TalonSRX(21);
    elseCounter = 0;
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
    ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);

    if (match.confidence > 0.90) {
      if (startColor == defaultColor) {
        startColor = match.color;
        panelWheel.set(ControlMode.PercentOutput, -0.4);
        
      }

      if (previousColor != match.color) {
        if (previousColor == startColor) {
          counter++;
          if(counter == 6) {
            panelWheel.set(ControlMode.PercentOutput, -0.2);
          }
          SmartDashboard.putNumber("Counter", counter);
          if (counter >= 8) {
            panelWheel.set(ControlMode.PercentOutput, 0);
            return false;
          }
        
       }
      }
      previousColor = match.color;
    } else {
      elseCounter++;
      if (elseCounter > 50) {
        panelWheel.set(ControlMode.PercentOutput, 0);
        startColor = defaultColor;
        previousColor = defaultColor;
        elseCounter = 0;
      }

    }
    return true;
  }
    
  public static void reInit() {
    counter = 0;
    elseCounter = 0;
    previousColor = defaultColor;
    startColor = defaultColor;
    panelWheel.set(ControlMode.PercentOutput, 0);
    
  }
    
 

   public static boolean RunStage3() {
     boolean returnValue;
     returnValue = true;
     detectedColor = colorSensor.getColor();
     ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);
     SmartDashboard.putNumber("Confidence", match.confidence);
     if (match.confidence > 0.90) {
       elseCounter = 0;

      String gameData; 
      gameData = DriverStation.getInstance().getGameSpecificMessage();
      if(gameData.length() > 0) {
        switch (gameData.charAt(0)) {

          case 'B' :    

            if (match.color == kRedTarget) {
              panelWheel.set(ControlMode.PercentOutput, 0);
              returnValue = false;
            } else { 
              if (match.color == kYellowTarget) {
                panelWheel.set(ControlMode.PercentOutput, Counterclockwise * Speed);
              } else if (match.color == kBlueTarget) {
                panelWheel.set(ControlMode.PercentOutput, Counterclockwise * Speed);
              } else if (match.color == kGreenTarget) {
                panelWheel.set(ControlMode.PercentOutput, Counterclockwise * Speed);
              } else {
              panelWheel.set(ControlMode.PercentOutput, 0.4); // here if all else fails
              }
            } 
            break;     
          
          case 'G' :
           if (match.color == kYellowTarget) {
              panelWheel.set(ControlMode.PercentOutput, 0); 
              returnValue = false; 
          } else { 
              if (match.color == kRedTarget) {
                panelWheel.set(ControlMode.PercentOutput, Counterclockwise * Speed);
              } else if (match.color == kBlueTarget) {
                panelWheel.set(ControlMode.PercentOutput, Counterclockwise * Speed);
              } else if (match.color == kGreenTarget) {
                panelWheel.set(ControlMode.PercentOutput, Counterclockwise * Speed);
              } else {
              panelWheel.set(ControlMode.PercentOutput, 0.4);
          } 
        }
          break;   

          case 'R' :
           if (match.color == kBlueTarget) {
              panelWheel.set(ControlMode.PercentOutput, 0);
              returnValue = false;
            } else { 
                if (match.color == kYellowTarget) {
                  panelWheel.set(ControlMode.PercentOutput, Counterclockwise * Speed);
                } else if (match.color == kRedTarget) {
                  panelWheel.set(ControlMode.PercentOutput, Counterclockwise * Speed);
                } else if (match.color == kGreenTarget) {
                  panelWheel.set(ControlMode.PercentOutput, Counterclockwise * Speed);
                } else {
              panelWheel.set(ControlMode.PercentOutput, 0.4);
          }
        }
          break;  

          case 'Y' :
           if (match.color == kGreenTarget) {
              panelWheel.set(ControlMode.PercentOutput, 0);
              returnValue = false;
          } else { 
            if (match.color == kYellowTarget) {
              panelWheel.set(ControlMode.PercentOutput, Counterclockwise * Speed);
            } else if (match.color == kBlueTarget) {
              panelWheel.set(ControlMode.PercentOutput, Counterclockwise * Speed);
            } else if (match.color == kRedTarget) {
              panelWheel.set(ControlMode.PercentOutput, Counterclockwise * Speed);
            } else {
              panelWheel.set(ControlMode.PercentOutput, 0.4);
           } 
          }
           break;  
          
          default :
          // This is corrupt data
             break;

}
} 
} else {
  elseCounter++;
  if (elseCounter >= 50) {
    panelWheel.set(ControlMode.PercentOutput, 0);
    elseCounter = 0;
  }
  
}
    return returnValue;
}
   }


