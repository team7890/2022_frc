// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class Intake_run extends CommandBase {
  SlewRateLimiter filter = new SlewRateLimiter(Constants.Intake.slewRate);
  private final double dSpeed;
  private final Intake m_intake;
  /** Creates a new Intake_runIn. */
  public Intake_run(Intake intake_in, double speed_in) {
    m_intake = intake_in;
    dSpeed = speed_in;
    addRequirements(m_intake);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    m_intake.stopIntake();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    // Ability to toggle slew rate
    if (Constants.Intake.applySlewRate)
    {
      m_intake.runIntake(filter.calculate(dSpeed));
    }
    else
    {
      m_intake.runIntake(dSpeed);
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    m_intake.stopIntake();
    filter.reset(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    return false;
  }
}
