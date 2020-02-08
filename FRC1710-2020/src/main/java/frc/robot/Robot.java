/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.buttons.Trigger.ButtonScheduler;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;




public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  public XboxController motor1Controller;
  public static boolean pressAButton, pressBButton;
  

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);   
    motor1Controller = new XboxController(0);
    //Intake.Intakeinit();
    // Pixy.Pixyinit();
    // Climber.Climberinit();
    pressAButton = false;
    pressBButton = false;

  }
  
  

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  @Override
  public void teleopInit() {
    super.teleopInit();
    ColorWheel.ColorWheelinit();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
     //final double yAxisDemand = motor1Controller.getY(Hand.kRight); // controlling hinge motor with right joystick
    //final double Trigger = motor1Controller.getTriggerAxis(Hand.kLeft); // Controlling control panel spinner with left trigger
     // final double yAxisDemand2 = motor1Controller.getY(Hand.kLeft); // Controlling climber to go up and down

    //Intake.IntakeBall(yAxisDemand);
    //ColorWheel.panelWheel(Trigger);
    // Climber.ClimbControl(yAxisDemand2);
    //ColorWheel.RunStage2();
    //ColorWheel.RunStage3();

    
    // Assigns left trigger to move colorwheel motor
    // if (motor1Controller.getTriggerAxis(Hand.kLeft) > 0) {
    //   ColorWheel.panelWheel.set(ControlMode.PercentOutput, motor1Controller.getTriggerAxis(Hand.kLeft));
    // }

    // Test comment
    
    //Scheduler.this.addButton(panelSpinner);


     if (motor1Controller.getAButtonPressed()) {
      pressAButton = true;
     } 
     if (pressAButton) {
       pressAButton = ColorWheel.RunStage2();
           }

           
     if (motor1Controller.getBButtonPressed()){
       pressBButton = true;
     }
     if (pressBButton) {
       pressBButton = ColorWheel.RunStage3();

     }

     }
          // Assigns x button to open and close DoubleSolenoid
    // if (motor1Controller.getXButtonPressed()) {
       //Intake.clawMover.set(Value.kForward); // Opens solenoid
       //Intake.piston2.set(Value.kForward); // Opens solenoid

    // }  else {
     // Intake.clawMover.set(Value.kReverse); // Closes Solenoid
     // Intake.piston2.set(Value.kReverse); // Closes Solenoid


   //  SmartDashboard.putNumber("Intake Power", Intake.intakeWheels.get());
    
   
 // }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
