// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;


//import static frc.robot.Constants.*;

public class Climber extends SubsystemBase {

  //declare motor
  //private CANSparkMax m_indexMotor = new CANSparkMax(11, MotorType.kBrushless);
  
  private TalonFX m_climbMotorLeft = new TalonFX(15, "rio");
  // private TalonFX m_climbMotorRight = new TalonFX(16, "rio");




  /** Creates a new Climber. */
  public Climber() 
  {
    m_climbMotorLeft.configFactoryDefault();
    m_climbMotorLeft.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, Constants.Climber.currentLimit, Constants.Climber.currentLimit, 0.0));

    // m_climbMotorRight.configFactoryDefault();
    // m_climbMotorRight.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, Constants.Climber.currentLimit, Constants.Climber.currentLimit, 0.0));

  }

  @Override
  public void periodic() 
  {
    // This method will be called once per scheduler run
  }

  public void runClimber(double speed_in)
  {
    m_climbMotorLeft.set(ControlMode.PercentOutput, speed_in);
  }
  // public void runIndexOut()
  // {
  //   m_indexMotor.set(-(Constants.Index.indexSpeed));
  // }
  
  public void stopClimber()
  {
    m_climbMotorLeft.set(ControlMode.PercentOutput, 0.0);
  }

//methods



}