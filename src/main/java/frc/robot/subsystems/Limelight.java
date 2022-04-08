               // Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight extends SubsystemBase {
  private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  private NetworkTableEntry m_tx = table.getEntry("tx");
  private NetworkTableEntry m_ty = table.getEntry("ty");
  private NetworkTableEntry m_ta = table.getEntry("ta");
  private NetworkTableEntry m_tv = table.getEntry("tv");
  private NetworkTableEntry ledMode = table.getEntry("ledMode");//NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);


  /** Creates a new Limelight. */
  public Limelight() 
  {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public boolean targetSighted()
  {
    return (m_tv.getDouble(0.0) == 1);
  }

  public double getTargetAngle()
  {
    return m_tx.getDouble(0.0);
  }

  public double getTargetVerticalAngle()
  {
    return m_ty.getDouble(0.0);
  }

  public double getArea()
  {
    return m_ta.getDouble(0.0);
  }

  public void turnLedOff()
  {
    ledMode.setNumber(1);
  }

  public void turnLedOn()
  {
    ledMode.setNumber(3);
  }

  public boolean isAligned()
  {
    boolean isAligned = false;
    if (targetSighted() && Math.abs(this.getTargetAngle()) > 3)
    {
      isAligned = true;
    }
    return isAligned;
  }
}
