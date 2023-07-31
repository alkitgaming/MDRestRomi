// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private final Spark leftMotor = new Spark(Constants.LEFT_MOTOR);
  private final Spark rightMotor = new Spark(Constants.RIGHT_MOTOR);
  private final Encoder leftEncoder = new Encoder(Constants.LEFT_ENC_A, Constants.LEFT_ENC_B);
  private final Encoder rightEncoder = new Encoder(Constants.RIGHT_ENC_A, Constants.RIGHT_ENC_B);
  private final Drivetrain drivetrain = new Drivetrain(leftMotor, rightMotor, leftEncoder, rightEncoder);
  private final Servo wrist = new Servo(Constants.ARM_SHOULDER);
  private final Servo shoulder = new Servo(Constants.ARM_WRIST);
  private final Servo claw = new Servo(Constants.ARM_CLAW);
  private final Arm arm = new Arm(wrist, shoulder, claw);
  private final Timer t = new Timer();
  private boolean complete = false;

  Order order = null;
  private MovementInstruction[] miq;
  private boolean autoMoving = false;
  private final Automation auto = new Automation(drivetrain, arm);
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {

  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * Call API to get instructions
   * Parse Order JSON
   * Generate automatic movements queue
   * Set auto move queue
   * Run auto move periodic until completion
   */
  @Override
  public void autonomousInit() 
  {    
    t.start();
    drivetrain.resetEncoders();
    auto.resetAuto();
    order = CallAPI.GETOrder(order);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() 
  {    
    if (autoMoving && auto.autoMovePeriodic()) complete = true;
    else if (!autoMoving && !complete)
    {
      System.out.println(order);
      // miq = MovementInstruction.generateMoveQueue(order);
      miq = configCustomMIQ();
      auto.setAutoMoveQueue(miq);
      autoMoving = true;
    }
  }

  private MovementInstruction[] configCustomMIQ()
  {
    MovementInstruction[] miq = new MovementInstruction[5];
    miq[0] = new MovementInstruction(5, MovementType.drive);
    miq[1] = new MovementInstruction(-360, MovementType.turn);
    miq[2] = new MovementInstruction(-5, MovementType.drive);
    miq[3] = new MovementInstruction(540, MovementType.turn);
    miq[4] = new MovementInstruction(-180, MovementType.turn);
    return miq;
  }

  @Override
  public void teleopInit() 
  {
    drivetrain.arcadeDrive(.5, 0);
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
