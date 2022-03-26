// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

// import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.NeutralMode;
// import com.ctre.phoenix.motorcontrol.can.TalonFX;
// import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.networktables.NetworkTableEntry;


//import static frc.robot.Constants.*;

public class Climber extends SubsystemBase {

  //declare motor
  private CANSparkMax m_climbHigh = new CANSparkMax(8, MotorType.kBrushless);
  private RelativeEncoder m_encoder;

  private ShuffleboardTab tab = Shuffleboard.getTab("Climber");
  private NetworkTableEntry LimitEntry = tab.add("Limit", 0).getEntry();



  /** Creates a new Climber. */
  public Climber() 
  {
    m_climbHigh.setIdleMode(IdleMode.kBrake);

    m_climbHigh.setSmartCurrentLimit(30);
    m_climbHigh.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
    // this.setClimberBackLimitON();
    m_climbHigh.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, false);

    m_climbHigh.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, 26);
    m_climbHigh.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, 0);

    m_encoder = m_climbHigh.getEncoder();
    m_encoder.setPosition(0.0);

  }

  @Override
  public void periodic() 
  {
    // This method will be called once per scheduler run
    LimitEntry.setDouble(m_encoder.getPosition());

  }

  public void runClimber(double speed_in)
  {
    m_climbHigh.set(speed_in);
  }

  public void stopClimber()
  {
    m_climbHigh.set(0.0);
  }

  public void setClimberToCoast()
  {
    m_climbHigh.setIdleMode(IdleMode.kCoast);
  }

  public void setClimberToBrake() {
    m_climbHigh.setIdleMode(IdleMode.kBrake);
  }

  public void setClimberBackLimitOFF() {
    m_climbHigh.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, false);
    m_climbHigh.burnFlash();
  }

  public void setClimberBackLimitON() {
    m_climbHigh.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);
    m_climbHigh.burnFlash();
  }

//methods



}