// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.math.kinematics.ChassisSpeeds;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.networktables.NetworkTableEntry;

public class DriveAlignLimelight extends CommandBase {
  /** Creates a new DriveAlignLimelight. */
  private DriveTrain m_driveTrain;
  private Limelight m_limelight;
  private double error;
  private double control_calc;
  private double control_limit = 1.0;
  private boolean targetFound = false;

  private ChassisSpeeds m_chassisSpeeds = new ChassisSpeeds(0.0, 0.0, 0.0);

  // private ShuffleboardTab tab = Shuffleboard.getTab("Sensors");
  // private NetworkTableEntry Control_entry = tab.add("Control_DriveAlign", 0).getEntry();



  public DriveAlignLimelight(Limelight limelight_in, DriveTrain driveTrain_in) 
  {
    m_limelight = limelight_in;
    m_driveTrain = driveTrain_in;
    addRequirements(m_limelight, m_driveTrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    m_limelight.turnLedOn();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    error = m_limelight.getTargetAngle();
    if (m_limelight.targetSighted()) {
      targetFound = true;
      control_calc = error * 0.5;
      if (control_calc > control_limit) {
        control_calc = control_limit;
      }
      else {
        if (control_calc < -control_limit) {
          control_calc = -control_limit;
        }
      }
      if (Math.abs(control_calc) < 0.01) {
        control_calc = 0.0;
      }

      m_chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(0.0, 0.0, -control_calc, m_driveTrain.getGyroscopeRotation());
      m_driveTrain.drive(m_chassisSpeeds);
    }
    // Control_entry.setDouble(control_calc);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(0.0, 0.0, 0.0, m_driveTrain.getGyroscopeRotation());
    m_driveTrain.drive(m_chassisSpeeds);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // return m_limelight.isAligned();
    if (targetFound && Math.abs(error) < 2.0) {
      targetFound = false;
      return true;
    }
    else {
      return false;
    }
  }
}
