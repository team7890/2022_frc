// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ClimberLeft extends SubsystemBase {
  private CANSparkMax m_climbLeft = new CANSparkMax(16, MotorType.kBrushless);


  /** Creates a new ClimberLeft. */
  public ClimberLeft() {
    m_climbLeft.setIdleMode(IdleMode.kBrake);
    m_climbLeft.setSmartCurrentLimit(60);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void runClimberLeft(double speed_in)
  {
    // m_climbMotorLeft.set(ControlMode.PercentOutput, speed_in);
    m_climbLeft.set(-speed_in);
  }

  public void stopClimber()
  {
    // m_climbMotorLeft.set(ControlMode.PercentOutput, 0.0);
    m_climbLeft.set(0.0);
  }

}
