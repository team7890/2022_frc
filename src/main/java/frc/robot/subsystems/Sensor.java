// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.networktables.NetworkTableEntry;
// import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;

public class Sensor extends SubsystemBase {

  // RIGHT SENSOR, FACING LEFT
  private final AnalogInput m_Sensor0 = new AnalogInput(0);

  // private final DigitalInput m_Switch0 = new DigitalInput(0);

 
  // LEFT SENSOR, FACING RIGHT
  private final AnalogInput m_Sensor1 = new AnalogInput(1);

  private double sensor0_old = 0;
  private double sensor0_filter = 0;

  private double sensor1_old = 0;
  private double sensor1_filter = 0;

  // private final DigitalInput m_Switch1 = new DigitalInput(1);
  // private final Encoder m_Encoder1 = new Encoder(2, 3);

  private ShuffleboardTab tab = Shuffleboard.getTab("Sensors");
  private NetworkTableEntry Sensor0_entry = tab.add("SonarSensor0", 0).getEntry();
  private NetworkTableEntry Sensor1_entry = tab.add("SonarSensor1", 0).getEntry();
  

  /** Creates a new Sensors. */
  public Sensor() 
  {
  }

  @Override
  public void periodic() {

    sensor0_filter = 0.97 * sensor0_old + 0.03 * m_Sensor0.getValue();
    sensor0_old = sensor0_filter;

    sensor1_filter = 0.97 * sensor1_old + 0.03 * m_Sensor1.getValue();
    sensor1_old = sensor1_filter;

    displaySensorData();


    // This method will be called once per scheduler run
    // SmartDashboard.putNumber("Analog ch0", sensor0_filter);
    // SmartDashboard.putBoolean("Digital ch0", m_Switch0.get());
    // SmartDashboard.putNumber("Analog ch1", sensor1_filter);
    // SmartDashboard.putBoolean("Digital ch1", m_Switch1.get());
    // SmartDashboard.putBoolean("Encoder Direction", m_Encoder1.getDirection());
    // SmartDashboard.putNumber("Encoder Distance", m_Encoder1.getDistance());
  }

  public void displaySensorData() {
    Sensor0_entry.setDouble(sensor0_filter);
    Sensor1_entry.setDouble(sensor1_filter);

  }
}
