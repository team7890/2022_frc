package frc.robot.subsystems;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
import frc.robot.utils.PID;

import javax.management.remote.TargetedNotification;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import edu.wpi.first.math.controller.PIDController;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// import com.revrobotics.CANSparkMax;
// import com.revrobotics.CANSparkMax.IdleMode;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;



public class Shooter extends SubsystemBase {
  SlewRateLimiter filter = new SlewRateLimiter(Constants.Intake.slewRate);
  /** Creates a new Shooter. */
  // May want to rename "DriveTrain" later
  private TalonFX m_ShooterMotorLeft = new TalonFX(Constants.CanID.ShooterLeft, "FastCAN");
  private TalonFX m_ShooterMotorRight = new TalonFX(Constants.CanID.ShooterRight, "FastCAN");

  // add PID controller
  private double speedActualLeft;
  private double speedActualRight;
  private double speedTuneLeft;
  private double speedTuneRight;
  private PIDController leftController;
  private PIDController rightController;
  private double targetRPM;
  private ShuffleboardTab tab = Shuffleboard.getTab("Shooter PID");
  private NetworkTableEntry targetRPM_entry = tab.add("Target RPM", 0).getEntry();
  private NetworkTableEntry speedActualLeft_entry = tab.add("Speed Actual Left", 0).getEntry();
  private NetworkTableEntry speedActualRight_entry = tab.add("Speed Actual Right", 0).getEntry();
  private NetworkTableEntry speedTuneLeft_entry = tab.add("Speed Tune Left", 0).getEntry();
  private NetworkTableEntry speedTuneRight_entry = tab.add("Speed Tune Right", 0).getEntry();


  public Shooter() 
  {
    m_ShooterMotorLeft.configFactoryDefault();
    m_ShooterMotorRight.configFactoryDefault();

    // enabled (boolean)| Limit(amp) (double) | Trigger Threshold(amp) (double) | Trigger Threshold Time(s)  (double)
    m_ShooterMotorLeft.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, Constants.Shooter.currentLimit, Constants.Shooter.currentLimit, 0.0));
    m_ShooterMotorRight.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, Constants.Shooter.currentLimit, Constants.Shooter.currentLimit, 0.0));

    // add PID controller
    m_ShooterMotorLeft.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    m_ShooterMotorRight.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    leftController = new PIDController(0.004, 0.0, 0.0002);
    rightController = new PIDController(0.004, 0.0, 0.0002);

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


    // add PID controller
    targetRPM = speed_in * 6380.0;
    speedActualLeft = m_ShooterMotorLeft.getSelectedSensorVelocity();
    speedActualRight = m_ShooterMotorRight.getSelectedSensorVelocity();
    speedTuneLeft = leftController.calculate(speedActualLeft, targetRPM);
    speedTuneRight = rightController.calculate(speedActualRight, targetRPM);
    SmartDashboard.putNumber("Target RPM sd", targetRPM);
    SmartDashboard.putNumber("Speed Actual Left sd", speedActualLeft);
    SmartDashboard.putNumber("Speed Actual Right sd", speedActualRight);
    SmartDashboard.putNumber("Speed Tune Left sd", speedTuneLeft);
    SmartDashboard.putNumber("Speed Tune Right sd", speedTuneRight);
    targetRPM_entry.setDouble(targetRPM);
    speedActualLeft_entry.setDouble(speedActualLeft);
    speedActualRight_entry.setDouble(speedActualRight);
    speedTuneLeft_entry.setDouble(speedTuneLeft);
    speedTuneRight_entry.setDouble(speedTuneRight);

    // m_ShooterMotorRight.set(ControlMode.Velocity, demand);
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
