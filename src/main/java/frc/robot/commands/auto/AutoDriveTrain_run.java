package frc.robot.commands.auto;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.DriveTrain;

public class AutoDriveTrain_run extends CommandBase {
    private final DriveTrain m_DriveTrain;
    private final double x;
    private final double y;
    private final double rot;


    public AutoDriveTrain_run(DriveTrain DriveTrain, double x, double y, double rot) {
        this.m_DriveTrain = DriveTrain;
        this.x = x;
        this.y = y;
        this.rot = rot;
        // this.m_translationXSupplier = translationXSupplier;
        // this.m_translationYSupplier = translationYSupplier;
        // this.m_rotationSupplier = rotationSupplier;

        addRequirements(DriveTrain);
    }

    @Override
    // FIXME this needs to only activate after the robot is powered on, not after enabling
    public void initialize(){
    
    // m_DriveTrain.zeroGyroscope();

    }

    @Override
    public void execute() {
        // You can use `new ChassisSpeeds(...)` for robot-oriented movement instead of field-oriented movement
        m_DriveTrain.drive(
                ChassisSpeeds.fromFieldRelativeSpeeds(
                        x,
                        y,
                        rot,
                        m_DriveTrain.getGyroscopeRotation()
                )
        );
    }

    @Override
    public void end(boolean interrupted) {
        m_DriveTrain.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
    }
}