package frc.robot.subsystems;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;

// import com.revrobotics.CANSparkMax;
// import com.revrobotics.CANSparkMax.IdleMode;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;



public class Shooter extends SubsystemBase {
  SlewRateLimiter filter = new SlewRateLimiter(Constants.Intake.slewRate);
  /** Creates a new Shooter. */
  // May want to rename "DriveTrain" later
  private TalonFX m_ShooterMotorLeft = new TalonFX(Constants.CanID.ShooterLeft, "FastCAN");
  private TalonFX m_ShooterMotorRight = new TalonFX(Constants.CanID.ShooterRight, "FastCAN");
  public Shooter() 
  {
    m_ShooterMotorLeft.configFactoryDefault();
    m_ShooterMotorRight.configFactoryDefault();

    // enabled (boolean)| Limit(amp) (double) | Trigger Threshold(amp) (double) | Trigger Threshold Time(s)  (double)
    m_ShooterMotorLeft.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, Constants.Shooter.currentLimit, Constants.Shooter.currentLimit, 0.0));
    m_ShooterMotorRight.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, Constants.Shooter.currentLimit, Constants.Shooter.currentLimit, 0.0));

    // m_ShooterMotorLeft.configSupplyCurrentLimit(40.0, 0);

    // m_ShooterMotor.setIdleMode(IdleMode.kCoast);
    // m_ShooterMotor.setSmartCurrentLimit(Constants.Shooter.currentLimit);

  }

  @Override
  public void periodic() 
  {
    // This method will be called once per scheduler run
  }
  public void runShooter(double speed_in)
  {
    // if (Constants.applySlewRate)
    // {
    //   m_ShooterMotorLeft.set(ControlMode.PercentOutput, speed_in);
    //   m_ShooterMotorRight.set(ControlMode.PercentOutput, -speed_in);
    // }
    // else
    // {
    //   m_ShooterMotorLeft.set(ControlMode.PercentOutput, speed_in);
    //   m_ShooterMotorRight.set(ControlMode.PercentOutput, -speed_in);
    // }
    m_ShooterMotorLeft.set(ControlMode.PercentOutput,  speed_in);
    m_ShooterMotorRight.set(ControlMode.PercentOutput, - speed_in);

    
  }
  // public void runShooterOut()
  // {
  //   m_ShooterMotor.set(-(Constants.Shooter.ShooterSpeed));
  // }
  public void stopShooter()
  {
    m_ShooterMotorLeft.set(ControlMode.PercentOutput, 0.0);
    m_ShooterMotorRight.set(ControlMode.PercentOutput, 0.0);
  }
}
