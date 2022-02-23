// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;



public class Shooter extends SubsystemBase {
  private SupplyCurrentLimitConfiguration supplyConfig = new SupplyCurrentLimitConfiguration(true, 40, 45, 0);
  /** Creates a new Shooter. */
  private TalonFX m_ShooterMotorRight = new TalonFX(12);
  private TalonFX m_ShooterMotorLeft = new TalonFX(13);
  public Shooter() 
  {

  }

  @Override
  public void periodic() 
  {
    m_ShooterMotorRight.configSupplyCurrentLimit(supplyConfig, 0);
    m_ShooterMotorLeft.configSupplyCurrentLimit(supplyConfig, 0);
    // This method will be called once per scheduler run
  }
  public void runShooter(double speed_in)
  {
    m_ShooterMotorRight.set(ControlMode.PercentOutput, speed_in);
    m_ShooterMotorLeft.set(ControlMode.PercentOutput, -speed_in);
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
