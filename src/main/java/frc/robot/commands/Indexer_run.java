// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Constants;
import frc.robot.subsystems.Indexer;

public class Indexer_run extends CommandBase {
  SlewRateLimiter filter = new SlewRateLimiter(Constants.Indexer.slewRate);
  private final double dSpeed;
  private final Indexer m_index;
  /** Creates a new Index_runIn. */
  public Indexer_run(Indexer index_in, double speed_in) {
    m_index = index_in;
    dSpeed = speed_in;
    addRequirements(m_index);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    m_index.stopIndex();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    // Ability to toggle slew rate
    if (Constants.Indexer.applySlewRate)
    {
      m_index.runIndexer(filter.calculate(dSpeed));
    }
    else
    {
      m_index.runIndexer(dSpeed);
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    m_index.stopIndex();
    filter.reset(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    return false;
  }
}