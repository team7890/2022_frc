// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Climber;
import frc.robot.Constants;


import java.util.function.DoubleSupplier;

public class Climber_run extends CommandBase {
    SlewRateLimiter filter = new SlewRateLimiter(Constants.Climber.slewRate);
    private final Climber m_Climber;

    private final double m_speedSupplier;

    public Climber_run(Climber Climber, double speed_in) {
        this.m_Climber = Climber;
        this.m_speedSupplier = speed_in;

        addRequirements(Climber);
    }

    @Override
    public void execute() {
        // Ability to toggle slew rate
    if (Constants.Climber.applySlewRate)
    {
        m_Climber.runClimber(filter.calculate(m_speedSupplier));
    }
    else
    {
        m_Climber.runClimber(m_speedSupplier);
    }
    //   m_Climber.runClimber(m_speedSupplier.getAsDouble());
        // You can use `new ChassisSpeeds(...)` for robot-oriented movement instead of field-oriented movement
        
    }

    @Override
    public void end(boolean interrupted) {
        
        filter.reset(0.0);
        m_Climber.stopClimber();
     
    }
}