package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

import java.lang.Math;
// import frc.robot.utils.PID;

// import javax.management.remote.TargetedNotification;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
// import com.ctre.phoenix.motorcontrol.can.TalonFX.;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import edu.wpi.first.wpilibj.DigitalInput;

import edu.wpi.first.math.controller.PIDController;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.networktables.NetworkTableEntry;

import edu.wpi.first.math.filter.SlewRateLimiter;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// import com.revrobotics.CANSparkMax;
// import com.revrobotics.CANSparkMax.IdleMode;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;



public class Shooter2tilt extends SubsystemBase {
  SlewRateLimiter filter = new SlewRateLimiter(Constants.Intake.slewRate);
  /** Creates a new Shooter. */
  // May want to rename "DriveTrain" later
  //FIXME change canID to constatnt

  private TalonFX m_ShooterMotorTilt = new TalonFX(Constants.CanID.ShooterTilt, "FastCAN");

  private final DigitalInput m_Switch0 = new DigitalInput(0);


  // private TalonFX m_ShooterMotorRight = new TalonFX(Constants.CanID.ShooterRight, "FastCAN");

  // add PID controller
  private double posTilt;
  private double error;
  private double control;
  // private double speedActualRight;
  // private double speedTuneLeft;
  // private double speedTuneRight;
  // private PIDController leftController;
  // private PIDController rightController;
  // private double targetRPM;
  private ShuffleboardTab tab = Shuffleboard.getTab("ShooterTilt PID");
  // private NetworkTableEntry targetRPM_entry = tab.add("Target RPM", 0).getEntry();
  private NetworkTableEntry posTilt_entry = tab.add("Pos Tilt", 0).getEntry();
  // private NetworkTableEntry speedActualRight_entry = tab.add("Speed Actual Right", 0).getEntry();
  // private NetworkTableEntry speedTuneLeft_entry = tab.add("Speed Tune Left", 0).getEntry();
  // private NetworkTableEntry speedTuneRight_entry = tab.add("Speed Tune Right", 0).getEntry();

  public Shooter2tilt() 
  {
    m_ShooterMotorTilt.configFactoryDefault();

    

    // m_ShooterMotorRight.configFactoryDefault();

    // enabled (boolean)| Limit(amp) (double) | Trigger Threshold(amp) (double) | Trigger Threshold Time(s)  (double)
    m_ShooterMotorTilt.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, Constants.Shooter.currentLimit, Constants.Shooter.currentLimit, 0.0));
    // m_ShooterMotorRight.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, Constants.Shooter.currentLimit, Constants.Shooter.currentLimit, 0.0));

    m_ShooterMotorTilt.setNeutralMode(NeutralMode.Brake);
    // m_ShooterMotorRight.setNeutralMode(NeutralMode.Brake);

    // add PID controller
    m_ShooterMotorTilt.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);//, 0, 20);
    m_ShooterMotorTilt.configNeutralDeadband(0.0005);
    // m_ShooterMotorTilt.configNominalOutputForward(0);
    // m_ShooterMotorTilt.configNominalOutputReverse(0);
    // m_ShooterMotorTilt.configPeakOutputForward(0.15);
    // m_ShooterMotorTilt.configPeakOutputReverse(-0.15);
    // // m_ShooterMotorTilt.configAllowableClosedloopError(0,0,0);
    // m_ShooterMotorTilt.config_kF(0, 0);
    // m_ShooterMotorTilt.config_kP(0, 0.015);
    // m_ShooterMotorTilt.config_kD(0, 0.00001);
    // m_ShooterMotorTilt.config_kI(0, 0);
    // m_ShooterMotorTilt.setSensorPhase(false);
    // m_ShooterMotorTilt.setInverted(true);
    // m_ShooterMotorRight.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    // leftController = new PIDController(0.0010, 0.00000, 0.00001);
    // rightController = new PIDController(0.0010, 0.00000, 0.00001);

    // m_ShooterMotorTilt.configSupplyCurrentLimit(40.0, 0);

    // m_ShooterMotor.setIdleMode(IdleMode.kCoast);
    // m_ShooterMotor.setSmartCurrentLimit(Constants.Shooter.currentLimit);

//FIXME uncomment when ready
    // m_ShooterMotorTilt.configForwardSoftLimitThreshold(10000, 0);
    // m_ShooterMotorTilt.configReverseSoftLimitThreshold(-10000, 0);
    // m_ShooterMotorTilt.configForwardSoftLimitEnable(true, 0);
    // m_ShooterMotorTilt.configReverseSoftLimitEnable(true, 0);
    m_ShooterMotorTilt.setSelectedSensorPosition(0.0);

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
    //   m_ShooterMotorTilt.set(ControlMode.PercentOutput, speed_in);
    //   m_ShooterMotorRight.set(ControlMode.PercentOutput, -speed_in);
    // }
    // else
    // {
    //   m_ShooterMotorTilt.set(ControlMode.PercentOutput, speed_in);
    //   m_ShooterMotorRight.set(ControlMode.PercentOutput, -speed_in);
    // }


    // add PID controller
    // targetRPM = speed_in * 6380.0 * 3.413;
    posTilt = m_ShooterMotorTilt.getSelectedSensorPosition();
    // speedActualRight = -m_ShooterMotorRight.getSelectedSensorVelocity();
    // speedTuneLeft = leftController.calculate(speedActualLeft, targetRPM) / 50.0;
    // speedTuneRight = rightController.calculate(speedActualRight, targetRPM) / 50.0;
    // targetRPM_entry.setDouble(targetRPM);
    posTilt_entry.setDouble(posTilt);
    // speedActualRight_entry.setDouble(speedActualRight);
    // speedTuneLeft_entry.setDouble(speedTuneLeft);
    // speedTuneRight_entry.setDouble(speedTuneRight);

    // m_ShooterMotorRight.set(ControlMode.Velocity, demand);

    // if hood is moving back down and you hit the limit switch, don't let motor drive any farther
    if (speed_in > 0.0) {
      if (!m_Switch0.get()) {
        m_ShooterMotorTilt.set(ControlMode.PercentOutput, 0.0);
      }
      else {
        m_ShooterMotorTilt.set(ControlMode.PercentOutput, speed_in);
      }
    }
    else {
      m_ShooterMotorTilt.set(ControlMode.PercentOutput, speed_in);
    }
    // m_ShooterMotorRight.set(ControlMode.PercentOutput, (speed_in + speedTuneRight));

    // when the switch is hit, reset the tilt encoder
    if (!m_Switch0.get()) {
      resetShooterTiltEncoder();
    }

    
  }
  // public void runShooterOut()
  // {
  //   m_ShooterMotor.set(-(Constants.Shooter.ShooterSpeed));
  // }

  public void stopShooter()
  {
    m_ShooterMotorTilt.set(ControlMode.PercentOutput, 0.0);
    // m_ShooterMotorRight.set(ControlMode.PercentOutput, 0.0);
  }

  public void tiltToPosition(double targetPosRot)
  {
    posTilt = m_ShooterMotorTilt.getSelectedSensorPosition();
    posTilt_entry.setDouble(posTilt);
    if (posTilt < targetPosRot) {
      targetPosRot = targetPosRot + 400;
    }
    else {
      targetPosRot = targetPosRot - 400;
    }
    // m_ShooterMotorTilt.set(TalonFXControlMode.Position, targetPosRot);
    error = targetPosRot - posTilt;
    control = 0.000044 * error;
    if (control > 0.15)
    {
      control = 0.15;
    }
    else if (control < -0.15)
    {
      control = -0.15;
    }
    else;

    if (Math.abs(error) < 50)
    {
      control = 0;
    }
    else;

    m_ShooterMotorTilt.set(ControlMode.PercentOutput, control);
  }

  public void resetShooterTiltEncoder() {
    m_ShooterMotorTilt.setSelectedSensorPosition(0.0);
  }
}
