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



public class Intake extends SubsystemBase {
  SlewRateLimiter filter = new SlewRateLimiter(Constants.Intake.slewRate);
  /** Creates a new Intake. */
  private CANSparkMax m_intakeMotor = new CANSparkMax(10, MotorType.kBrushless);
  public Intake() 
  {
    m_intakeMotor.setIdleMode(IdleMode.kCoast);
    m_intakeMotor.setSmartCurrentLimit(Constants.Intake.currentLimit);
    //m_intakeMotor.setClosedLoopRampRate(20);
  }

  @Override
  public void periodic() 
  {
    // This method will be called once per scheduler run
  }
  public void runIntake(double speed_in)
  {
    m_intakeMotor.set(speed_in);
  }
  // public void runIntakeOut()
  // {
  //   m_intakeMotor.set(-(Constants.Intake.intakeSpeed));
  // }
  public void stopIntake()
  {
    m_intakeMotor.set(0.0);
  }
}
