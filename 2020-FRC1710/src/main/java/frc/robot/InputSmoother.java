/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * This class takes in an upper bound, lower bound, and a rate of change value.
 * When the class is used it will take the value internally stored and modifify it only by the rate of change amount.
 */
public class InputSmoother {
    private double outputTotal, upperBound, lowerBound;

    public InputSmoother(double ub, double lb) {
        outputTotal = 0;
        upperBound = ub;
        lowerBound = lb;
        
    }

    /**
     * This method takes an input in and only allows a 
     * specified rate of change to occur on the outputTotal stored within this instance.
     * @param input value of input given positive or negative.
     * @return the output total stored within this InputSmoother instance
     */
    public double smoothInput(double input) {
        
        
     
        outputTotal = outputTotal + input * .00000000000000000000000000000000001;
        outputTotal = Math.min(Math.max(input, lowerBound), upperBound);
        SmartDashboard.putNumber("smooth drive", outputTotal);
        return outputTotal;
    }
}
