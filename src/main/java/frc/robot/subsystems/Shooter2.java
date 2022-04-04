package frc.robot.subsystems;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
// import frc.robot.utils.PID;

// import javax.management.remote.TargetedNotification;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import edu.wpi.first.math.controller.PIDController;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// import com.revrobotics.CANSparkMax;
// import com.revrobotics.CANSparkMax.IdleMode;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;



public class Shooter2 extends SubsystemBase {
  SlewRateLimiter filter = new SlewRateLimiter(Constants.Intake.slewRate);
  /** Creates a new Shooter. */
  // May want to rename "DriveTrain" later
  private TalonFX m_ShooterMotorLeft2 = new TalonFX(Constants.CanID.WindmillLeft, "rio");
  private TalonFX m_ShooterMotorRight2 = new TalonFX(Constants.CanID.WindmillRight, "rio");

  // add PID controller
  private double speedActualLeft;
  private double speedActualRight;
  private double speedTuneLeft;
  private double speedTuneRight;
  private PIDController leftController;
  private PIDController rightController;
  private double targetRPM;
  private ShuffleboardTab tab = Shuffleboard.getTab("Shooter PID2");
  private NetworkTableEntry targetRPM_entry = tab.add("Target RPM2", 0).getEntry();
  private NetworkTableEntry speedActualLeft_entry = tab.add("Speed Actual Left2", 0).getEntry();
  private NetworkTableEntry speedActualRight_entry = tab.add("Speed Actual Right2", 0).getEntry();
  private NetworkTableEntry speedTuneLeft_entry = tab.add("Speed Tune Left2", 0).getEntry();
  private NetworkTableEntry speedTuneRight_entry = tab.add("Speed Tune Right2", 0).getEntry();


  public Shooter2() 
  {
    m_ShooterMotorLeft2.configFactoryDefault();
    m_ShooterMotorRight2.configFactoryDefault();

    // enabled (boolean)| Limit(amp) (double) | Trigger Threshold(amp) (double) | Trigger Threshold Time(s)  (double)
    m_ShooterMotorLeft2.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, Constants.Shooter.currentLimit, Constants.Shooter.currentLimit, 0.0));
    m_ShooterMotorRight2.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, Constants.Shooter.currentLimit, Constants.Shooter.currentLimit, 0.0));

    m_ShooterMotorLeft2.setNeutralMode(NeutralMode.Brake);
    m_ShooterMotorRight2.setNeutralMode(NeutralMode.Brake);

    // add PID controller
    m_ShooterMotorLeft2.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    m_ShooterMotorRight2.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    leftController = new PIDController(0.0010, 0.00000, 0.00001);
    rightController = new PIDController(0.0010, 0.00000, 0.00001);

    // m_ShooterMotorLeft2.configSupplyCurrentLimit(40.0, 0);

    // m_ShooterMotor.setIdleMode(IdleMode.kCoast);
    // m_ShooterMotor.setSmartCurrentLimit(Constants.Shooter.currentLimit);
    
    // m_ShooterMotorRight2.follow(m_ShooterMotorLeft2);
    // m_ShooterMotorRight2.setInverted(InvertType.OpposeMaster);

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
    //   m_ShooterMotorLeft2.set(ControlMode.PercentOutput, speed_in);
    //   m_ShooterMotorRight2.set(ControlMode.PercentOutput, speed_in);
    // }
    // else
    // {
    //   m_ShooterMotorLeft2.set(ControlMode.PercentOutput, speed_in);
    //   m_ShooterMotorRight2.set(ControlMode.PercentOutput, speed_in);
    // }


    // add PID controller
    targetRPM = speed_in * 6380.0 * 3.413;
    speedActualLeft = m_ShooterMotorLeft2.getSelectedSensorVelocity();
    speedActualRight = - m_ShooterMotorRight2.getSelectedSensorVelocity();
    speedTuneLeft = leftController.calculate(speedActualLeft, targetRPM) / 50.0;
    speedTuneRight = rightController.calculate(speedActualRight, targetRPM) / 50.0;
    // SmartDashboard.putNumber("Target Ticks sd", targetRPM);
    // SmartDashboard.putNumber("Speed Actual Left sd", speedActualLeft);
    // SmartDashboard.putNumber("Speed Actual Right sd", speedActualRight);
    // SmartDashboard.putNumber("Speed Tune Left sd", speedTuneLeft);
    // SmartDashboard.putNumber("Speed Tune Right sd", speedTuneRight);
    targetRPM_entry.setDouble(targetRPM);
    speedActualLeft_entry.setDouble(speedActualLeft);
    speedActualRight_entry.setDouble(speedActualRight);
    speedTuneLeft_entry.setDouble(speedTuneLeft);
    speedTuneRight_entry.setDouble(speedTuneRight);

    // m_ShooterMotorRight2.set(ControlMode.Velocity, demand);
    m_ShooterMotorLeft2.set(ControlMode.PercentOutput,  speed_in + speedTuneLeft);
    m_ShooterMotorRight2.set(ControlMode.PercentOutput, - (speed_in + speedTuneRight));

    
  }
  // public void runShooterOut()
  // {
  //   m_ShooterMotor.set((Constants.Shooter.ShooterSpeed));
  // }
  public void stopShooter()
  {
    m_ShooterMotorLeft2.set(ControlMode.PercentOutput, 0.0);
    m_ShooterMotorRight2.set(ControlMode.PercentOutput, 0.0);
  }
}
