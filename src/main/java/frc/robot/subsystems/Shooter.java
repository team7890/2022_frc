package frc.robot.subsystems;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;



public class Shooter extends SubsystemBase {
  SlewRateLimiter filter = new SlewRateLimiter(Constants.Intake.slewRate);
  /** Creates a new Shooter. */
  private CANSparkMax m_ShooterMotor = new CANSparkMax(11, MotorType.kBrushless);
  public Shooter() 
  {
    m_ShooterMotor.setIdleMode(IdleMode.kCoast);
    m_ShooterMotor.setSmartCurrentLimit(Constants.Shooter.currentLimit);

  }

  @Override
  public void periodic() 
  {
    // This method will be called once per scheduler run
  }
  public void runShooter(double speed_in)
  {
    m_ShooterMotor.set(speed_in);
  }
  // public void runShooterOut()
  // {
  //   m_ShooterMotor.set(-(Constants.Shooter.ShooterSpeed));
  // }
  public void stopShooter()
  {
    m_ShooterMotor.set(0.0);
  }
}
