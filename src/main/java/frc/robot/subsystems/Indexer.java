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



public class Indexer extends SubsystemBase {
  SlewRateLimiter filter = new SlewRateLimiter(Constants.Intake.slewRate);
  /** Creates a new Index. */
  private CANSparkMax m_indexMotor = new CANSparkMax(11, MotorType.kBrushless);
  public Indexer() 
  {
    m_indexMotor.setIdleMode(IdleMode.kCoast);
    m_indexMotor.setSmartCurrentLimit(Constants.Indexer.currentLimit);

  }

  @Override
  public void periodic() 
  {
    // This method will be called once per scheduler run
  }
  public void runIndexer(double speed_in)
  {
    m_indexMotor.set(speed_in);
  }
  // public void runIndexOut()
  // {
  //   m_indexMotor.set(-(Constants.Index.indexSpeed));
  // }
  public void stopIndex()
  {
    m_indexMotor.set(0.0);
  }
}
