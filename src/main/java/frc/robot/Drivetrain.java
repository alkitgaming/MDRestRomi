// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

public class Drivetrain 
{
  private final Spark leftMotor;
  private final Spark rightMotor;
  private final Encoder leftEncoder;
  private final Encoder rightEncoder;
    
  public Drivetrain(Spark leftMotor, Spark rightMotor, Encoder leftEncoder, Encoder rightEncoder)
  {
    this.leftMotor = leftMotor;
    this.rightMotor = rightMotor;
    this.leftEncoder = leftEncoder;
    this.rightEncoder = rightEncoder;

    leftEncoder.setDistancePerPulse((Math.PI * Constants.ENC_WHEEL_DIAMETER_IN) / Constants.ENC_TICKS_PER_REVOLUTION);
    rightEncoder.setDistancePerPulse((Math.PI * Constants.ENC_WHEEL_DIAMETER_IN) / Constants.ENC_TICKS_PER_REVOLUTION);
    resetEncoders();
    rightMotor.setInverted(true);
  }
  


  public void arcadeDrive(double x, double zaxisRotate) {
    double xaxisSpeed = x * .8;
    leftMotor.set(xaxisSpeed + (0.5 * zaxisRotate));
    rightMotor.set(xaxisSpeed - (0.5 * zaxisRotate));
  }

  public void resetEncoders() {
    leftEncoder.reset();
    rightEncoder.reset();
  }

  public double getDistanceInch() {
    return rightEncoder.getDistance();
  }
  
  public void autoArcadeDrive(double set)
  {
    if (set > 0) arcadeDrive(0.25, 0);
    else if (set < 0) arcadeDrive(-0.25, 0);
  }

  public void autoTurn(double set)
  {
    if (set > 0) arcadeDrive(0, 0.25);
    else if (set < 0) arcadeDrive(0, -0.25);
  }

  /*
   * distance divided by circumference is number of full circles
   * 360 degrees in a circle, so must multiply by 360
   * both wheels are moving the same amount so must double the right encoder
   * negating because right's forward direction is counter-clockwise
   * and intended direction is clockwise
   */
  public double getEncoderAsAngleDeg() 
  {
    return -360 * rightEncoder.getDistance() / Constants.WHEELSPAN_CIRC;
  }
}
