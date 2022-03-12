// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Constants;
import frc.robot.subsystems.Shooter;

public class AutoShooter_run extends CommandBase {
  SlewRateLimiter filter = new SlewRateLimiter(Constants.Shooter.slewRate);
  private final double dSpeed;
  private final Shooter m_Shooter;
  /** Creates a new Shooter_runIn. */
  public AutoShooter_run(Shooter Shooter_in, double speed_in) {
    m_Shooter = Shooter_in;
    dSpeed = speed_in;
    addRequirements(m_Shooter);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    m_Shooter.stopShooter();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    // Ability to toggle slew rate
    if (Constants.Shooter.applySlewRate)
    {
      m_Shooter.runShooter(filter.calculate(dSpeed));
    }
    else
    {
      m_Shooter.runShooter(dSpeed);
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    m_Shooter.stopShooter();
    filter.reset(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    return false;
  }
}

