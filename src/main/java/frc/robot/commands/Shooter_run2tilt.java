// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Constants;
import frc.robot.subsystems.Shooter2tilt;

public class Shooter_run2tilt extends CommandBase {
  SlewRateLimiter filter = new SlewRateLimiter(Constants.Shooter.slewRate);
  private final double dPos;
  private final Shooter2tilt m_Shooter2Tilt;
  /** Creates a new Shooter_runIn. */
  public Shooter_run2tilt(Shooter2tilt Shooter_in, double speed_in) {
    m_Shooter2Tilt = Shooter_in;
    dPos = speed_in;
    addRequirements(m_Shooter2Tilt);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    m_Shooter2Tilt.stopShooter();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    // Ability to toggle slew rate


    // m_Shooter2Tilt.tiltToPosition(dPos);
    m_Shooter2Tilt.runShooter(dPos);



    // if (Constants.Shooter.applySlewRate)
    // {
    //   m_Shooter2Tilt.runShooter(filter.calculate(dSpeed));
    // }
    // else
    // {
    //   m_Shooter2Tilt.runShooter(dSpeed);
    // }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    m_Shooter2Tilt.stopShooter();
    filter.reset(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    return false;
  }
}

