package frc.robot;

public enum ArmMoveData 
{
    example(1, 0, 1, 2, 3),
    pencil (2, 0, 1, 2, 3);
    
    ArmMoveData(double id, double drive2, double claw, double wrist, double shoulder)
    {
        this.drive2 = drive2;
        this.claw = claw;
        this.wrist = wrist;
        this.shoulder = shoulder;
        this.id = id;
    }
    
    double id;
    double drive2;
    double claw;
    double wrist;
    double shoulder;

    double id()
    {
        return id;
    }

    double claw()
    {
        return claw;
    }
    
    double drive2()
    {
        return drive2;
    }

    double wrist()
    {
        return wrist;
    }

    double shoulder()
    {
        return shoulder;
    }
}
