package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;


import frc.robot.subsystems.DriveTrain;

import java.util.function.DoubleSupplier;

public class DriveTrain_run extends CommandBase {
    private final DriveTrain m_DriveTrain;

    private final DoubleSupplier m_translationXSupplier;
    private final DoubleSupplier m_translationYSupplier;
    private final DoubleSupplier m_rotationSupplier;

    public DriveTrain_run(DriveTrain DriveTrain,
                               DoubleSupplier translationXSupplier,
                               DoubleSupplier translationYSupplier,
                               DoubleSupplier rotationSupplier) {
        this.m_DriveTrain = DriveTrain;
        this.m_translationXSupplier = translationXSupplier;
        this.m_translationYSupplier = translationYSupplier;
        this.m_rotationSupplier = rotationSupplier;

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
                        m_translationXSupplier.getAsDouble(),
                        m_translationYSupplier.getAsDouble(),
                        m_rotationSupplier.getAsDouble(),
                        m_DriveTrain.getGyroscopeRotation()
                )
        );
    }

    @Override
    public void end(boolean interrupted) {
        m_DriveTrain.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
    }
}