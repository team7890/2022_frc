// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ClimberRight extends SubsystemBase {
  private CANSparkMax m_climbRight = new CANSparkMax(17, MotorType.kBrushless);
  /** Creates a new ClimberRight. */
  public ClimberRight() {
    m_climbRight.setIdleMode(IdleMode.kBrake);
    m_climbRight.setSmartCurrentLimit(60);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void runClimberRight(double speed_in)
  {
    // m_climbMotorLeft.set(ControlMode.PercentOutput, speed_in);
    m_climbRight.set(-speed_in);
  }

  public void stopClimber()
  {
    // m_climbMotorLeft.set(ControlMode.PercentOutput, 0.0);
    m_climbRight.set(0.0);
  }


}
