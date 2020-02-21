/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;




public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  public XboxController driverController,mechController;
  public static boolean pressAButton, pressBButton;
  public static int Stage;
  public static String inStage, airPressureValue;
  public Compressor compressor;
  public DoubleSolenoid airRefill;
  public int pneumaticsAirPressure;
  public static double indexerSpeed = 0.4;
  public TalonSRX Indexer, Intake, Elevator1, Elevator2, Feeder;
  

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);   
    mechController = new XboxController(0);
    driverController = new XboxController(1);
    pressAButton = false;
    pressBButton = false;
    inStage = "Stage"; 
    airPressureValue = "airPressureValue";
    Intake = new TalonSRX(21);
    Indexer.follow(Intake);
    Elevator1.follow(Intake);
    Elevator2.follow(Intake);
    Feeder.follow(Intake);
    

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

    double motorSpeed = driverController.getTriggerAxis(Hand.kRight);

    Intake.set(ControlMode.PercentOutput, motorSpeed);

    

     if (mechController.getAButtonPressed()) {
      pressAButton = true;
      pressBButton = false;
      ColorWheel.reInit();
     } 
     if (pressAButton) {
       SmartDashboard.putString(inStage, "Stage2");
       pressAButton = ColorWheel.RunStage2();
       if (pressAButton == false) {
         SmartDashboard.putString(inStage, "");
       }
           }

           
     if (mechController.getBButtonPressed()) {
        pressBButton = true;
        pressAButton = false;
        ColorWheel.reInit();
     }
     if (pressBButton) {
       SmartDashboard.putString(inStage, "Stage3");
       pressBButton = ColorWheel.RunStage3();
       if (pressBButton == false) {
         SmartDashboard.putString(inStage, "");
       }

     }

    // if (pneumaticsAirPressure < 100) {
    //   compressor.start();
    //   compressor.getPressureSwitchValue();
    //   SmartDashboard.putString(airPressureValue, "");
    // }

    //  if (pneumaticsAirPressure > 100) {
    //    compressor.stop();
    //    compressor.getPressureSwitchValue();
    //    SmartDashboard.putString(airPressureValue, "");
    //  }

  //   boolean refillAirOpen = mechController.getXButtonPressed(); 
	// if (refillAirOpen) { airRefill.set(Value.kReverse); }
    
  //   boolean refillAirClose = mechController.getYButtonPressed(); 
  //   if (refillAirClose) { airRefill.set(Value.kForward); }
    
  //   // compressor
  //   boolean pressureLowAndMechXButtonPressed = compressor.getPressureSwitchValue() || mechController.getXButton();
  //   compressor.setClosedLoopControl(pressureLowAndMechXButtonPressed); 

  }
   

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
