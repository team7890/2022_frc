// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.filter.SlewRateLimiter;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;



public class Shooter extends SubsystemBase {
  SlewRateLimiter filter = new SlewRateLimiter(10);
  /** Creates a new Shooter. */
  private TalonFX m_ShooterMotorRight = new TalonFX(12);
  private TalonFX m_ShooterMotorLeft = new TalonFX(13);
  public Shooter() 
  {

  }

  @Override
  public void periodic() 
  {
    // This method will be called once per scheduler run
  }
  public void runShooter(double speed_in)
  {
    m_ShooterMotorRight.set(ControlMode.PercentOutput, filter.calculate(speed_in));
    m_ShooterMotorLeft.set(ControlMode.PercentOutput, -filter.calculate(speed_in));
  }
  // public void runShooterOut()
  // {
  //   m_ShooterMotor.set(-(Constants.Shooter.ShooterSpeed));
  // }
  public void stopShooter()
  {
    m_ShooterMotorRight.set(ControlMode.PercentOutput, 0.0);
    m_ShooterMotorLeft.set(ControlMode.PercentOutput, 0.0);
  }
}
