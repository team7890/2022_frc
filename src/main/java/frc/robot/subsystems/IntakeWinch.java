// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class IntakeWinch extends SubsystemBase {
  SlewRateLimiter filter = new SlewRateLimiter(Constants.Intake.slewRate);
    /** Creates a new Intake. */
  private CANSparkMax m_intakeWinchMotor = new CANSparkMax(15, MotorType.kBrushless);

  /** Creates a new IntakeWinch. */
  public IntakeWinch() {
    m_intakeWinchMotor.setIdleMode(IdleMode.kCoast);
    m_intakeWinchMotor.setSmartCurrentLimit(Constants.Intake.currentLimit);
  }

  @Override
  public void periodic() 
  {
    // This method will be called once per scheduler run
  }
  public void runIntakeWinch(double speed_in)
  {
    m_intakeWinchMotor.set(speed_in);

    // m_intakeWinchMotor.set(speed_in);
  }
  // public void runIntakeOut()
  // {
  //   m_intakeMotor.set(-(Constants.Intake.intakeSpeed));
  // }
  public void stopIntakeWinch()
  {
    m_intakeWinchMotor.set(0.0);

    // m_intakeWinchMotor.set(0.0);
  }
}
