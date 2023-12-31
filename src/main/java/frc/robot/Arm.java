package frc.robot;

import edu.wpi.first.wpilibj.Servo;

public class Arm 
{
    Servo wrist;
    Servo shoulder;
    Servo claw;

    public Arm(Servo wrist, Servo shoulder, Servo claw)
    {
        this.wrist = wrist;
        this.shoulder = shoulder;
        this.claw = claw;
    }

    public void setClaw(double num)
    {
        claw.set(num);
    }

    public void setWrist(double num)
    {
        wrist.set(num);
    }

    public void setShoulder(double num)
    {
        shoulder.set(num);
    }

    public double getClaw()
    {
        return claw.get();
    }

    public double getWrist()
    {
        return wrist.get();
    }

    public double getShoulder()
    {
        return shoulder.get();
    }

    public double clamp(double top, double bot, double val)
    {
        if (val > top)
        {
            return top;
        }
        else if (bot > val)
        {
            return bot;
        }
        return val;
    }
}
