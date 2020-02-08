package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class Climber {

    public static CANSparkMax Climb;
    public static int PCM_CAN_ID = 33;

    public static void ClimbControl (double power) {
        Climb.set(power);
    }

    public static void Climberinit () {
        Climb = new CANSparkMax (PCM_CAN_ID, MotorType.kBrushless);
    }
}
