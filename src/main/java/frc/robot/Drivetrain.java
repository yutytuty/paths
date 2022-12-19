package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.spikes2212.command.drivetrains.TankDrivetrain;
import com.spikes2212.util.PigeonWrapper;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class Drivetrain extends TankDrivetrain {

    private final CANSparkMax left1, left2, right1, right2;
    private final PigeonWrapper pigeon;

    private static Drivetrain instance;

    public static Drivetrain getInstance() {
        if (instance == null) {
            instance = new Drivetrain(
                    new CANSparkMax(RobotMap.CAN.LEFT_SPARKMAX_1, CANSparkMaxLowLevel.MotorType.kBrushless),
                    new CANSparkMax(RobotMap.CAN.LEFT_SPARKMAX_2, CANSparkMaxLowLevel.MotorType.kBrushless),
                    new CANSparkMax(RobotMap.CAN.RIGHT_SPARKMAX_1, CANSparkMaxLowLevel.MotorType.kBrushless),
                    new CANSparkMax(RobotMap.CAN.RIGHT_SPARKMAX_2, CANSparkMaxLowLevel.MotorType.kBrushless),
                    new WPI_TalonSRX(RobotMap.CAN.PIGEON_TALON)
            );
        }
        return instance;

    }

    private Drivetrain(CANSparkMax left1, CANSparkMax left2, CANSparkMax right1, CANSparkMax right2, WPI_TalonSRX pigeon_talon) {
        super(new MotorControllerGroup(left1, left2), new MotorControllerGroup(right1, right2));
        this.left1 = left1;
        this.left2 = left2;
        this.right1 = right1;
        this.right2 = right2;
        this.pigeon = new PigeonWrapper(pigeon_talon);
    }

    public double getLeftTicks() {
        return left1.getEncoder().getPosition();
    }

    public double getRightTicks() {
        return right1.getEncoder().getPosition();
    }

    public double getAngle() {
        return pigeon.getX(); // Maybe?
    }
}
