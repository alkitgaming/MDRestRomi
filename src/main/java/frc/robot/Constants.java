package frc.robot;

public class Constants {

    //API
    public static final String GET_ORDER_URL = "http://devvpn.mdcms.ch:8080/skmdrapi/romipvdr?request=getOrder";
    public static final String UPDATE_INVENTORY_URL = "http://devvpn.mdcms.ch:8080/skmdrapi/romipvdr?request=updateInventory";

    //Robot components
    public static final int LEFT_MOTOR = 0;
    public static final int RIGHT_MOTOR = 1;

    public static final int LEFT_ENC_A = 4;
    public static final int LEFT_ENC_B = 5;
    public static final int RIGHT_ENC_A = 6;
    public static final int RIGHT_ENC_B = 7;
    
    public static final int ARM_SHOULDER = 4;
    public static final int ARM_WRIST = 5;
    public static final int ARM_CLAW = 6;

    //Automation
    public static final double ENC_TICKS_PER_REVOLUTION = 1440.0;
    public static final double ENC_WHEEL_DIAMETER_IN = 2.75591;
    public static final double WHEELSPAN_CIRC = Math.PI * 5.6875 * 1.135;

    public static final double DRIVE_P = 0.05;
    public static final double TURN_P = 0.001;
    public static final double LEFT_WHEEL_DRIVE_MULT = 1.17;
    
    public static final int MOVEMENT_QUEUE_LENGTH = 13;
    public static final double AUTOTURN_ALLOW_ERROR_DEG = 3;
    public static final double AUTODRIVE_ALLOW_ERROR_INCH = 0.15;

    public static final double MAIN_DRIVE_DISTANCE_CLOSE = 1;
    public static final double MAIN_DRIVE_DISTANCE_MID = 2;
    public static final double MAIN_DRIVE_DISTANCE_FAR = 3;

    public static final double CLAW_ALLOWED_ERROR = 0.01;
    public static final double CLAW_MOVE_SPEED = 0.0025;
    public static final double WRIST_ALLOWED_ERROR = 0.01;
    public static final double WRIST_MOVE_SPEED = 0.0025;
    public static final double SHOULDER_ALLOWED_ERROR = 0.01;
    public static final double SHOULDER_MOVE_SPEED = 0.0025;

    public static final double TRAVEL_CLAW = 0;
    public static final double OPEN_CLAW = 0;

    public static final double TRAVEL_SHOULDER = 1;
    public static final double TRAVEL_WRIST = 0.5;
    //Field and positioning

}
