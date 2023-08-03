package frc.robot;

public class MovementInstruction 
{
    private double value;
    private MovementType type;

    public static MovementInstruction[] generateMoveQueue(Order order)
    { 
        MovementInstruction[] array = new MovementInstruction[Constants.MOVEMENT_QUEUE_LENGTH];
        int turnDegrees = (order.isLeftSide() ? -90 : 90);

        double primaryDriveDistance = 0;
        switch(order.getRow())
        {
          case(1):
            primaryDriveDistance = Constants.MAIN_DRIVE_DISTANCE_CLOSE;
            break;
          case(2):
            primaryDriveDistance = Constants.MAIN_DRIVE_DISTANCE_MID;
            break;
          case(3):
            primaryDriveDistance = Constants.MAIN_DRIVE_DISTANCE_FAR;
            break;
        }

        double secondaryDriveDistance = 0, wrist = 0, claw = 0, shoulder = 0;
        int orderid = order.getID();
        for (ArmMoveData a : Constants.ARM_MOVE_DATA)
        {
            if (a.id() == orderid)
            {
                secondaryDriveDistance = a.drive2();
                claw = a.claw();
                wrist = a.wrist();
                shoulder = a.shoulder();
                break;
            }
        }
        
        array[0] = new MovementInstruction(primaryDriveDistance, MovementType.drive);
        array[1] = new MovementInstruction(turnDegrees, MovementType.turn);
        array[2] = new MovementInstruction(secondaryDriveDistance, MovementType.drive);
        array[3] = new MovementInstruction(wrist, MovementType.wrist);
        array[4] = new MovementInstruction(shoulder, MovementType.shoulder);
        array[5] = new MovementInstruction(claw, MovementType.claw);
        array[6] = new MovementInstruction(Constants.TRAVEL_SHOULDER, MovementType.shoulder);
        array[7] = new MovementInstruction(Constants.TRAVEL_WRIST, MovementType.wrist);
        array[8] = new MovementInstruction(-secondaryDriveDistance, MovementType.drive);
        array[9] = new MovementInstruction(-turnDegrees, MovementType.turn);
        array[10] = new MovementInstruction(primaryDriveDistance, MovementType.drive);
        array[11] = new MovementInstruction(Constants.OPEN_CLAW, MovementType.claw);
        array[12] = new MovementInstruction(180, MovementType.turn);
        return array;
    }

    MovementInstruction(double value, MovementType type)
    {
        this.value = value;
        this.type = type;
    }

    /*
     *  Returns the turning value in degrees,
     *  the drive distance in inches,
     *  or the arm setpoint on 0 to 1 scale
     */
    public double getValue()
    {
        return value;
    }

    public MovementType getType()
    {
        return type;
    }

}

enum MovementType
{
    drive(0),
    turn(1),
    claw (2),
    wrist(3),
    shoulder(4);

    int i;
    MovementType(int i)
    {
        this.i = i;
    }
    int get()
    {
        return i;
    }
}