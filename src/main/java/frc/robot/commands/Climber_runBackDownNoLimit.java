// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Climber;
import frc.robot.Constants;

public class Climber_runBackDownNoLimit extends CommandBase {
  /** Creates a new Climber_runBackDownNoLimit. */

  private final Climber m_Climber;
  private final double speed;

  public Climber_runBackDownNoLimit(Climber climber, double speed_in) {
    this.m_Climber = climber;
    this.speed = speed_in;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_Climber.setClimberBackLimitOFF();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_Climber.runClimber(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Climber.setClimberBackLimitON();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
