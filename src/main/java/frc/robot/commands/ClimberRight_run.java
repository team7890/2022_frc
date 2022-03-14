// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.ClimberRight;

import java.util.function.DoubleSupplier;

public class ClimberRight_run extends CommandBase {
  private final ClimberRight m_Climber;

  private final DoubleSupplier m_speedSupplier;

  /** Creates a new ClimberRight_run. */
  public ClimberRight_run(ClimberRight climber_in, DoubleSupplier speedSupplier_in) {
    this.m_Climber = climber_in;
    this.m_speedSupplier = speedSupplier_in;

    addRequirements(climber_in);

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_Climber.runClimberRight(m_speedSupplier.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Climber.stopClimber();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
