package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Automation 
{
  private MovementInstruction[] autoMoveQueue;
  private int currentMI;
  private Drivetrain drive;
  private Arm arm;

  public Automation(Drivetrain drive, Arm arm)
  {
    this.drive = drive;
    this.arm = arm;
  }

  public void setAutoMoveQueue(MovementInstruction[] autoMoveQueue)
  {
    this.autoMoveQueue = autoMoveQueue;
  }

  public boolean autoMovePeriodic()
  {

    if (currentMI == autoMoveQueue.length)
    {
      drive.arcadeDrive(0, 0);
      return true;
    } 

    if (autoMoveQueue[currentMI].getType() == MovementType.drive 
    && autoDrive(autoMoveQueue[currentMI].getValue())) 
      nextQueuedMove();

    else if (autoMoveQueue[currentMI].getType() == MovementType.turn 
    && autoTurn(autoMoveQueue[currentMI].getValue())) 
      nextQueuedMove();
    
    else if (autoMoveQueue[currentMI].getType() == MovementType.claw 
    && autoClaw(autoMoveQueue[currentMI].getValue())) 
      nextQueuedMove();

    else if (autoMoveQueue[currentMI].getType() == MovementType.wrist 
    && autoWrist(autoMoveQueue[currentMI].getValue())) 
      nextQueuedMove();

    else if (autoMoveQueue[currentMI].getType() == MovementType.shoulder 
    && autoShoulder(autoMoveQueue[currentMI].getValue())) 
      nextQueuedMove();

    try
    {
      dashboard();
    }
    catch (ArrayIndexOutOfBoundsException e)
    {
      System.out.println("well that sucks");
    }
    
    return false;
  }

  private void nextQueuedMove()
  {
    currentMI++;
    drive.resetEncoders();
    drive.arcadeDrive(0, 0);
  }

  private void dashboard() throws ArrayIndexOutOfBoundsException
  {
    SmartDashboard.putNumber("Movement Instruction #", currentMI);
    SmartDashboard.putNumber("Enumerated MI Type", autoMoveQueue[currentMI].getType().get());
    SmartDashboard.putNumber("Right Encoder", drive.getDistanceInch());
    SmartDashboard.putNumber("Setpoint", autoMoveQueue[currentMI].getValue());
    SmartDashboard.putNumber("Right Encoder as Degrees", drive.getEncoderAsAngleDeg());
  }

  private boolean autoDrive(double value)
  {
    if ( Math.abs(value - drive.getDistanceInch()) < Constants.AUTODRIVE_ALLOW_ERROR_INCH)
    {
      drive.resetEncoders();
      drive.autoArcadeDrive(0);
      return true;
    } 
    drive.autoArcadeDrive(value);
    return false;
  }

  private boolean autoTurn(double value)
  {
    if (Math.abs(value - drive.getEncoderAsAngleDeg()) < Constants.AUTOTURN_ALLOW_ERROR_DEG) 
    {
      drive.resetEncoders();
      drive.autoArcadeDrive(0);
      return true;
    }
    drive.autoTurn(value);
    return false;
  }

  public void resetAuto()
  {
    currentMI = 0;
  }

  private boolean autoClaw(double value) 
  {
    if (value > arm.getClaw() + Constants.CLAW_ALLOWED_ERROR)
    {
      arm.setClaw(arm.getClaw() + Constants.CLAW_MOVE_SPEED);
      return false;
    }
    else if (value < arm.getClaw() - Constants.CLAW_ALLOWED_ERROR)
    {
      arm.setClaw(arm.getClaw() - Constants.CLAW_MOVE_SPEED);
      return false;
    }
    else return true;
  }

  private boolean autoWrist(double value)
  {
    if (value > arm.getWrist() + Constants.WRIST_ALLOWED_ERROR)
    {
      arm.setWrist(arm.getWrist() + Constants.WRIST_MOVE_SPEED);
      return false;
    }
    else if (value < arm.getWrist() - Constants.WRIST_ALLOWED_ERROR)
    {
      arm.setWrist(arm.getWrist() - Constants.WRIST_MOVE_SPEED);
      return false;
    }
    else return true;
  }

  private boolean autoShoulder(double value)
  {
    if (value > arm.getShoulder() + Constants.SHOULDER_ALLOWED_ERROR)
    {
      arm.setShoulder(arm.getShoulder() + Constants.SHOULDER_MOVE_SPEED);
      return false;
    }
    else if (value < arm.getShoulder() - Constants.SHOULDER_ALLOWED_ERROR)
    {
      arm.setShoulder(arm.getShoulder() - Constants.SHOULDER_MOVE_SPEED);
      return false;
    }
    else return true;
  } 

  public static double clamp(double val, double min, double max)
  {
    if (val < min)
    {
      return min;
    }
    else if (val > max)
    {
      return max;
    }
    return val;
  }
}
