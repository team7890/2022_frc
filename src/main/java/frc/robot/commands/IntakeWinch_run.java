// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Constants;
import frc.robot.subsystems.IntakeWinch;

public class IntakeWinch_run extends CommandBase {
  SlewRateLimiter filter = new SlewRateLimiter(Constants.Intake.slewRate);
  private final double dSpeed;
  private final IntakeWinch m_intakeWinch;
  /** Creates a new Intake_runIn. */
  public IntakeWinch_run(IntakeWinch intakeWinch_in, double speed_in) {
    m_intakeWinch = intakeWinch_in;
    dSpeed = speed_in;
    addRequirements(m_intakeWinch);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    m_intakeWinch.stopIntakeWinch();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    // Ability to toggle slew rate
    if (Constants.applySlewRate)
    {
      m_intakeWinch.runIntakeWinch(filter.calculate(dSpeed));
    }
    else
    {
      m_intakeWinch.runIntakeWinch(dSpeed);
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    m_intakeWinch.stopIntakeWinch();
    filter.reset(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    return false;
  }
}
