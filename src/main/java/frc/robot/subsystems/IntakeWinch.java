// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.networktables.NetworkTableEntry;

import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;


public class IntakeWinch extends SubsystemBase {
  // private ShuffleboardTab tab = Shuffleboard.getTab("Intake Winch");
  // private NetworkTableEntry LimitEntry = tab.add("Limit", 0).getEntry();

  private RelativeEncoder m_encoder;

  SlewRateLimiter filter = new SlewRateLimiter(Constants.Intake.slewRate);
    /** Creates a new Intake. */
  private CANSparkMax m_intakeWinchMotor = new CANSparkMax(Constants.CanID.IntakeWinch, MotorType.kBrushless);

  

  /** Creates a new IntakeWinch. */
  public IntakeWinch() {
    m_intakeWinchMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
    m_intakeWinchMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);

    m_intakeWinchMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, 65);
    m_intakeWinchMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, 0);

    m_encoder = m_intakeWinchMotor.getEncoder();

    m_intakeWinchMotor.setIdleMode(IdleMode.kBrake);
    m_intakeWinchMotor.setSmartCurrentLimit(Constants.Intake.currentLimit);
  }

  @Override
  public void periodic() 
  {
    // LimitEntry.setDouble(m_encoder.getPosition());
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

  public void setIntakeWinchCoast()
  {
    m_intakeWinchMotor.setIdleMode(IdleMode.kCoast);
  }
  public void setIntakeWinchBrake()
  {
    m_intakeWinchMotor.setIdleMode(IdleMode.kBrake);
  }
}
