// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Constants;
import frc.robot.subsystems.Shooter2tilt;
import frc.robot.subsystems.Shooter2;
import frc.robot.subsystems.Indexer;

// shuffleboard and network tables for debug
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.networktables.NetworkTableEntry;

public class AutoShooter2_run extends CommandBase {

  private ShuffleboardTab tab = Shuffleboard.getTab("ShooterTilt PID");
  // private NetworkTableEntry targetRPM_entry = tab.add("Target RPM", 0).getEntry();
  private NetworkTableEntry m_ty_entry = tab.add("LL ty", 0).getEntry();


  private final Shooter2tilt m_shooter2tilt;
  private final Shooter2 m_shooter2;
  private final double m_ty;
  private final double m_ta;
  
  private double hood_position;
  private double shooter_speed;

  /** Creates a new AutoShooter2_run. */
  public AutoShooter2_run(Shooter2tilt m_shooter2tilt_in, Shooter2 m_shooter2_in, double m_ty_in, double m_ta_in) {
    m_shooter2tilt = m_shooter2tilt_in;
    m_shooter2 = m_shooter2_in;
    m_ty = m_ty_in;
    m_ta = m_ta_in;

    addRequirements(m_shooter2tilt, m_shooter2);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // first set hood position from experiments
    if (m_ty > 7) {
      hood_position = 0;
    }
    else {
      if (m_ty > 2) {
        hood_position = -2000;
      }
      else {
        if (m_ty > -2) {
          hood_position = -2500;
        }
      }
    }

    // then set shooter velocity
    if (m_ty > 7) {
      shooter_speed = -0.575;
    }
    else {
      if (m_ty > -2) {
        shooter_speed = -0.6;
      }
    }

    m_shooter2tilt.tiltToPosition(hood_position);
    m_shooter2.runShooter(shooter_speed);

    m_ty_entry.setDouble(m_ty);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter2.stopShooter();
    m_shooter2tilt.stopShooter();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
