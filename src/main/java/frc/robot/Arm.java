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
