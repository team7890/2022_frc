// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;


import java.util.function.DoubleSupplier;

public class Climber_run extends CommandBase {
    private final Climber m_Climber;

    private final DoubleSupplier m_speedSupplier;

    public Climber_run(Climber Climber, DoubleSupplier speedSupplier) {
        this.m_Climber = Climber;
        this.m_speedSupplier = speedSupplier;

        addRequirements(Climber);
    }

    @Override
    public void execute() {
      m_Climber.runClimber(m_speedSupplier.getAsDouble());
        // You can use `new ChassisSpeeds(...)` for robot-oriented movement instead of field-oriented movement
        
    }

    @Override
    public void end(boolean interrupted) {
     
    }
}