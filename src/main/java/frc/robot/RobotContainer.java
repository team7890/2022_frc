// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.networktables.NetworkTableEntry;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.IntakeWinch;
import frc.robot.subsystems.ClimberLeft;
import frc.robot.subsystems.ClimberRight;
import frc.robot.subsystems.Sensor;
import frc.robot.subsystems.Shooter2;
import frc.robot.subsystems.Shooter2tilt;
// import frc.robot.subsystems.WindmillClimber;

import frc.robot.commands.IntakeWinch_run;
import frc.robot.commands.Climber_run;
import frc.robot.commands.ClimberLeft_run;
import frc.robot.commands.ClimberRight_run;
import frc.robot.commands.Shooter_run;
import frc.robot.commands.DriveTrain_run;
import frc.robot.commands.Indexer_run;
import frc.robot.commands.Intake_run;
import frc.robot.commands.auto.AutoLeftTarmac;
import frc.robot.commands.auto.AutoNumberOne1;
import frc.robot.commands.auto.AutoRightTarmacOne;
import frc.robot.commands.auto.AutoRightTarmacOnePosTwo;
import frc.robot.commands.auto.AutoLeftTarmacPosTwo;
import frc.robot.commands.auto.AutoLeftTarmacMoveOut;
import frc.robot.commands.auto.ImprovedShooter;
import frc.robot.commands.auto.ImprovedShooterLow;
import frc.robot.commands.auto.IntakeAndIndexer;
import frc.robot.commands.auto.IntakeAndIndexerOut;
import frc.robot.commands.auto.AutoLeftTarmacTwoBall;
import frc.robot.commands.Shooter_run2;
import frc.robot.commands.Shooter_run2tilt;
// import frc.robot.commands.WindmillClimber_run;

import edu.wpi.first.wpilibj2.command.Command;

import com.swervedrivespecialties.swervelib.*;


import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */



public class RobotContainer {
  public static SendableChooser sendablechooser;
  private SendableChooser<Command> m_auto_chooser;
  // private static SendableChooser<AutonomousMode> autonomousModeChooser;


  private final DriveTrain m_driveTrain = new DriveTrain(2, 2);
  private final Intake m_intake = new Intake();
  private final Climber m_climber = new Climber();
  private final ClimberLeft m_climberLeft = new ClimberLeft();
  private final ClimberRight m_climberRight = new ClimberRight();
  private final Indexer m_indexer = new Indexer();
  private final Shooter m_shooter = new Shooter();
  private final Shooter2tilt m_shooter2tilt = new Shooter2tilt();
  private final Shooter2 m_shooter2 = new Shooter2();
  private final IntakeWinch m_intakeWinch = new IntakeWinch();
  private final Sensor m_Sensor = new Sensor();
  // private final WindmillClimber m_windmill = new WindmillClimber();
  

  private final XboxController m_coController = new XboxController(Constants.JoystickOI.coStickPort);

  XboxController m_driverController = new XboxController(Constants.JoystickOI.driverStickPort);
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
    

  private ShuffleboardTab tab = Shuffleboard.getTab("Auto");
  private NetworkTableEntry autoPhase =
       tab.add("Auto Phase", 1)
          .getEntry();

  // Retrieve the auto selection from the dashboard
  // 1 = left tarmac auto to shoot a ball gthen go get another one, drive back and shoot ti
  // 2 = 
  double autoSelection = autoPhase.getDouble(1);
  int autoSelectionInt = (int)autoSelection;

   // m_DriveTrain.setDefaultComm
  public RobotContainer() {
    // Set up the default command for the drivetrain.
    // The controls are for field-oriented driving:
    // Left stick Y axis -> forward and backwards movement
    // Left stick X axis -> left and right movement
    // Right stick X axis -> rotation
    m_driveTrain.setDefaultCommand(new DriveTrain_run(
            m_driveTrain,
            () -> -modifyAxis(m_driverController.getLeftY()) * DriveTrain.MAX_VELOCITY_METERS_PER_SECOND,
            () -> -modifyAxis(m_driverController.getLeftX()) * DriveTrain.MAX_VELOCITY_METERS_PER_SECOND,
            () -> -modifyAxis(m_driverController.getRightX()) * DriveTrain.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND
    ));
    // m_climber.setDefaultCommand(new Climber_run(m_climber, () -> modifyAxis(m_driverController.getRightY()))); //FIXME change control




    // OLD CLIMBER
    m_climberLeft.setDefaultCommand(new ClimberLeft_run(m_climberLeft, () -> modifyAxis(m_coController.getLeftY() * -0.85 )));
    m_climberRight.setDefaultCommand(new ClimberRight_run(m_climberRight, () -> modifyAxis(m_coController.getRightY() * 0.85 )));




    // m_windmill.setDefaultCommand(new WindmillClimber_run(m_windmill, () -> modifyAxis(m_coController.getLeftY() * 1.0 )));
    // m_climberRight.setDefaultCommand(new WindmillClimber_run(m_climberRight, () -> modifyAxis(m_coController.getRightY() * 0.85 )));
  
    // m_climber.setDefaultCommand(new Climber_run(m_climber, m_driverController::getRightY));
    // Configure the button bindings
    configureButtonBindings();

    
    m_auto_chooser = new SendableChooser<Command>();
    m_auto_chooser.addOption("Left Tarmac", new AutoLeftTarmacTwoBall(m_driveTrain, m_shooter, m_indexer, m_intake, m_intakeWinch));
    m_auto_chooser.addOption("Right Tarmac One", new AutoRightTarmacOne(m_driveTrain, m_shooter, m_indexer, m_intake, m_intakeWinch));
    m_auto_chooser.addOption("(pos2) Right Tarmac One", new AutoRightTarmacOnePosTwo(m_driveTrain, m_shooter, m_indexer, m_intake, m_intakeWinch));
    m_auto_chooser.addOption("(pos2) Left Tarmac", new AutoLeftTarmacPosTwo(m_driveTrain, m_shooter, m_indexer, m_intake, m_intakeWinch));
    m_auto_chooser.addOption("(pos1, move out) Left Tarmac", new AutoLeftTarmacMoveOut(m_driveTrain, m_shooter, m_indexer, m_intake, m_intakeWinch));
    tab.add(m_auto_chooser);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() 
  {
    
    // FIXE (Check this statement vs the next statement)
    // new Button(m_driverController::getBackButton)
    //         // No requirements because we don't need to interrupt anything
    //         .whenPressed(m_driveTrain::zeroGyroscope);
    


    // Start button zeros the gyroscope
    // Pilot
    new JoystickButton(m_driverController, Button.kStart.value)
            // No requirements because we don't need to interrupt anything
            .whenPressed(m_driveTrain::zeroGyroscope);


    // CLIMBER
    
    // new JoystickButton(m_driverController, Button.kX.value).whenHeld(new Climber_run(m_climber, m_driverController::getRightY));
    new JoystickButton(m_driverController, Button.kY.value).whenHeld(new Climber_run(m_climber, 0.22));
    new JoystickButton(m_driverController, Button.kX.value).whenHeld(new Climber_run(m_climber, -0.17));

    new JoystickButton(m_driverController, Button.kB.value).whenHeld(new Shooter_run2tilt(m_shooter2tilt, -0.1));
    new JoystickButton(m_driverController, Button.kA.value).whenHeld(new Shooter_run2(m_shooter2, 0.15));
    new JoystickButton(m_driverController, Button.kBack.value).whenHeld(new Shooter_run2tilt(m_shooter2tilt, 0.1));

    // new JoystickButton(m_driverController, Button.kBack.value).whenHeld(new Climber_run(m_climber, -0.10));
  
    // Indexer + Intake
    new JoystickButton(m_coController, Button.kB.value).whenHeld(new IntakeAndIndexer(m_indexer, m_intake));

    // THIS IS FOR THE INDEXER
    // co-pilot
    
    new JoystickButton(m_coController, Button.kY.value).whenHeld(new Indexer_run(m_indexer, Constants.Indexer.indexSpeed));
    
    // REVERSE INDEXER
    // co-pilot
    new JoystickButton(m_coController, Button.kA.value).whenHeld(new IntakeAndIndexerOut(m_indexer, m_intake));
    // new JoystickButton(m_coController, Button.kA.value).whenHeld(new Indexer_run(m_indexer, - Constants.Indexer.indexSpeed));

    // SET UP TO THE WINCH AND CHANGE BUTTON
    // FIXME change buttons to dpad up/down
    // Co-pilot
    new JoystickButton(m_coController, Button.kStart.value).whenHeld(new IntakeWinch_run(m_intakeWinch, Constants.Intake.intakeWinchSpeed));
    new JoystickButton(m_coController, Button.kBack.value).whenHeld(new IntakeWinch_run(m_intakeWinch, - Constants.Intake.intakeWinchSpeed));

    // BASIC INTAKE
    // co-pilot

    // new JoystickButton(m_coController, Button.kB.value).whenHeld(new Intake_run(m_intake, Constants.Intake.intakeSpeed));
    //new JoystickButton(m_driverController, Button.kA.value).whenHeld(new Intake_run(m_intake, 0.25 * Constants.Intake.intakeSpeed));
    
    // SHOOTER
    // Co-pilot
    new JoystickButton(m_coController, Button.kX.value).whenHeld(new Shooter_run(m_shooter, Constants.Shooter.shooterRevSpeed));
    // High goal shoot
    // new JoystickButton(m_coController, Button.kLeftBumper.value).whenHeld(new Shooter_run(m_shooter, Constants.Shooter.shooterHighSpeed));
    // Low goal shoot
    new JoystickButton(m_coController, Button.kLeftBumper.value).whenHeld(new ImprovedShooterLow(m_shooter, m_indexer));
    // FIXME CHANGE INPUT to trigger, if possible
    new JoystickButton(m_coController, Button.kRightBumper.value).whenHeld(new ImprovedShooter(m_shooter, m_indexer));


    // RESET GYROSCOPE
    // new JoystickButton(m_driverController, Button.kBack.value).whenPressed(m_DriveTrain.zeroGyroscope());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */



  public Command getAutonomousCommand() {
    return m_auto_chooser.getSelected();
    // return new AutoNumberOne1(m_driveTrain, m_shooter, m_indexer, m_intake, m_intakeWinch);

    // Read command names for each auto
    // if (autoSelection == 1)
    // {
    // return new AutoLeftTarmac(m_driveTrain, m_shooter, m_indexer, m_intake, m_intakeWinch);
    // }
    // else if(autoSelection == 2)
    // {
    // return new AutoRightTarmacOne(m_driveTrain, m_shooter, m_indexer, m_intake, m_intakeWinch);
    // }
    // else
    // {
    // return new AutoLeftTarmac(m_driveTrain, m_shooter, m_indexer, m_intake, m_intakeWinch);
    // }
    // Shuffleboard.selectTab("Auto");
    // sendablechooser = new SendableChooser();
    // sendablechooser.setDefaultOption("Do Nothing", new AutoLeftTarmac(m_driveTrain, m_shooter, m_indexer, m_intake, m_intakeWinch));
    // sendablechooser.addOption("Drive", new AutoRightTarmacOne(m_driveTrain, m_shooter, m_indexer, m_intake, m_intakeWinch));
    // sendablechooser.addOption("Drive And Shoot", new AutoRightTarmacOne(m_driveTrain, m_shooter, m_indexer, m_intake, m_intakeWinch));
    // tab.add("sendable", sendablechooser);

    // String sChosen = sendablechooser.getSelected();


    // switch(autoSelectionInt)
    // {
    //   case 1:
    //     return new AutoLeftTarmac(m_driveTrain, m_shooter, m_indexer, m_intake, m_intakeWinch);
    //   case 2:
    //     return new AutoRightTarmacOne(m_driveTrain, m_shooter, m_indexer, m_intake, m_intakeWinch);
    //   default:
    //     return new AutoRightTarmacOne(m_driveTrain, m_shooter, m_indexer, m_intake, m_intakeWinch);
    // }
  }



  private static double deadband(double value, double deadband) {
    if (Math.abs(value) > deadband) {
      if (value > 0.0) {
        // return (value - deadband) / (1.0 - deadband);
        return ((value - deadband) / (1.0 - deadband))*((value - deadband) / (1.0 - deadband));
      } else {
        // return (value + deadband) / (1.0 - deadband);
        return -((value + deadband) / (1.0 - deadband))*((value + deadband) / (1.0 - deadband));
      }
    } else {
      return 0.0;
    }
  }

  private static double modifyAxis(double value) {
    // Deadband
    value = deadband(value, 0.05);

    // Square the axis
    value = Math.copySign(value * value, value);

    return value;
  }
}
