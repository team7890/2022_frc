// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// package frc.robot.subsystems;

// import edu.wpi.first.wpilibj2.command.SubsystemBase;

// import frc.robot.Constants;

// import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.NeutralMode;
// import com.ctre.phoenix.motorcontrol.can.TalonFX;
// import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;

// public class WindmillClimber extends SubsystemBase {
//   /** Creates a new WindmillClimber. */

//   private TalonFX m_WindmillMotorLeft = new TalonFX(Constants.CanID.WindmillLeft, "rio");
//   private TalonFX m_WindmillMotorRight = new TalonFX(Constants.CanID.WindmillRight, "rio");



//   public WindmillClimber() {
//     m_WindmillMotorLeft.configFactoryDefault();
//     m_WindmillMotorRight.configFactoryDefault();

//     m_WindmillMotorLeft.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, Constants.Windmill.currentLimit, Constants.Windmill.currentLimit, 0.0));
//     m_WindmillMotorRight.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, Constants.Windmill.currentLimit, Constants.Windmill.currentLimit, 0.0));

//     m_WindmillMotorLeft.setNeutralMode(NeutralMode.Coast);
//     m_WindmillMotorRight.setNeutralMode(NeutralMode.Coast);

//   }

//   @Override
//   public void periodic() {
//     // This method will be called once per scheduler run
//   }

//   public void runWindmillClimber(double speed_in)
//   {
//     m_WindmillMotorLeft.set(ControlMode.PercentOutput,  speed_in );
//     m_WindmillMotorRight.set(ControlMode.PercentOutput, - speed_in );
//   }

//   public void stopWindmillClimber()
//   {
//     m_WindmillMotorLeft.set(ControlMode.PercentOutput,  0.0);
//     m_WindmillMotorRight.set(ControlMode.PercentOutput, 0.0);
//   }
// }
