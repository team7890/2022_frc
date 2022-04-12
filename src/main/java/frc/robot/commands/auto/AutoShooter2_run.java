// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Constants;
import frc.robot.subsystems.Shooter2tilt;
import frc.robot.subsystems.Shooter2;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Limelight;

// shuffleboard and network tables for debug
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTableEntry;

// utils
import frc.robot.utils.ShooterInterpolator;


public class AutoShooter2_run extends CommandBase {

  // private ShuffleboardTab tab = Shuffleboard.getTab("ShooterTilt PID");
  // private NetworkTableEntry targetRPM_entry = tab.add("Target RPM", 0).getEntry();
  // private NetworkTableEntry m_ty_entry = tab.add("LL ty", 0).getEntry();


  private final Shooter2tilt m_shooter2tilt;
  private final Shooter2 m_shooter2;
  private final Limelight m_limelight;
  // private final double m_ty;
  // private final double m_ta;
  private ShooterInterpolator m_interpolator;
  
  private double hood_position;
  private double shooter_speed;
  private double m_ty;
  private double m_ta;
  private double tyFilter;
  private double tyFilterOld;

  /** Creates a new AutoShooter2_run. */
  // public AutoShooter2_run(Shooter2tilt m_shooter2tilt_in, Shooter2 m_shooter2_in, double m_ty_in, double m_ta_in) {
  //   m_shooter2tilt = m_shooter2tilt_in;
  //   m_shooter2 = m_shooter2_in;
  //   m_ty = m_ty_in;
  //   m_ta = m_ta_in;
  public AutoShooter2_run(Shooter2tilt m_shooter2tilt_in, Shooter2 m_shooter2_in, Limelight m_limelight_in) {
    m_shooter2tilt = m_shooter2tilt_in;
    m_shooter2 = m_shooter2_in;
    m_limelight = m_limelight_in;
  
  addRequirements(m_shooter2tilt, m_shooter2, m_limelight);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  static {
    SmartDashboard.putNumber("angle", 0.0);
    SmartDashboard.putNumber("Speed", 100.0);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_ty = 0.1 * m_limelight.getTargetVerticalAngle() + 0.9 * tyFilterOld;
    tyFilterOld = m_ty;
    m_ta = m_limelight.getArea();

    // shooter_speed = m_interpolator.getSpeed(m_ty);
    // hood_position = m_interpolator.getHood(m_ty);

    // System.out.println("--- AutoShooter2_run Data ---");
    // System.out.println("limelight ty:  " + m_ty);
    // System.out.println("shooter speed:  " + shooter_speed);
    // System.out.println("hood position:  " + hood_position);
    // System.out.println("");
    // System.out.println("");
    // System.out.println("");


    // m_ty = 4.0;
    // first set hood position from experiments
    hood_position = 208.0 * m_ty - 2492.0;
    hood_position = Math.max(-3000.0, hood_position);
    hood_position = Math.min(0.0, hood_position);
    //hood_position = SmartDashboard.getNumber("angle", 0.0);

    // if (m_ty > 7) {
    //   hood_position = 0;
    // }
    // else {
    //   if (m_ty > 2) {
    //     hood_position = -2000;
    //   }
    //   else {
    //     if (m_ty > -2) {
    //       hood_position = -2250;
    //     }
    //     else {
    //       hood_position = -2700;
    //     }
    //   }
    // }

    // then set shooter velocity
      shooter_speed = 0.00542 * m_ty - 0.695;
      shooter_speed = Math.min(-0.57, shooter_speed);
      shooter_speed = Math.max(-0.75, shooter_speed);
      shooter_speed = shooter_speed - 0.04;
      //shooter_speed = SmartDashboard.getNumber("speed", 0.0);


    // if (m_ty > 7) {
    //   shooter_speed = -0.6;
    // }
    // else {
    //   if (m_ty > -2) {
    //     shooter_speed = -0.68;
    //   }
    //   else {
    //     shooter_speed = -0.72;
    //   }
    // }




    m_shooter2tilt.tiltToPosition(hood_position);
    m_shooter2.runShooter(shooter_speed);

    // m_ty_entry.setDouble(m_ty);
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
