// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.Intake_run;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.DriveTrain;
import frc.robot.commands.DriveTrain_run;
import edu.wpi.first.wpilibj2.command.Command;

import com.swervedrivespecialties.swervelib.*;


import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */



public class RobotContainer {
  private final DriveTrain m_driveTrain = new DriveTrain();

  private final XboxController m_controller = new XboxController(0);

  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  public static final Intake m_intake = new Intake();

  XboxController m_driverController = new XboxController(Constants.JoystickOI.driverStickPort);
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
    
   // m_DriveTrain.setDefaultComm
  public RobotContainer() {
    // Set up the default command for the drivetrain.
    // The controls are for field-oriented driving:
    // Left stick Y axis -> forward and backwards movement
    // Left stick X axis -> left and right movement
    // Right stick X axis -> rotation
    m_driveTrain.setDefaultCommand(new DriveTrain_run(
            m_driveTrain,
            () -> -modifyAxis(m_controller.getLeftY()) * DriveTrain.MAX_VELOCITY_METERS_PER_SECOND,
            () -> -modifyAxis(m_controller.getLeftX()) * DriveTrain.MAX_VELOCITY_METERS_PER_SECOND,
            () -> -modifyAxis(m_controller.getRightX()) * DriveTrain.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND
    ));
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() 
  {
    /*
    FIXME (Check this statement vs the next statement)
    new Button(m_controller::getBackButton)
            // No requirements because we don't need to interrupt anything
            .whenPressed(m_driveTrain::zeroGyroscope);
    */
    // Back button zeros the gyroscope
    new JoystickButton(m_driverController, Button.kBack.value)
            // No requirements because we don't need to interrupt anything
            .whenPressed(m_driveTrain::zeroGyroscope);
  
    // THIS IS FOR THE FIRST HALF OF THE INDEXER
    new JoystickButton(m_driverController, Button.kY.value).whenHeld(new Intake_run(m_intake, Constants.Indexer.indexSpeed));
    // THIS IS FOR THE SECOND HALF OF THE INDEXER
    new JoystickButton(m_driverController, Button.kA.value).whenHeld(new Intake_run(m_intake, Constants.Indexer.indexSpeed));


    new JoystickButton(m_driverController, Button.kB.value).whenHeld(new Intake_run(m_intake, Constants.Intake.intakeSpeed));
    //new JoystickButton(m_driverController, Button.kA.value).whenHeld(new Intake_run(m_intake, 0.25 * Constants.Intake.intakeSpeed));
    
    new JoystickButton(m_driverController, Button.kLeftBumper.value).whenHeld(new Intake_run(m_intake, Constants.Shooter.shooterRevSpeed));
    new JoystickButton(m_driverController, Button.kRightBumper.value).whenHeld(new Intake_run(m_intake, Constants.Shooter.shooterSpeed));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
  private static double deadband(double value, double deadband) {
    if (Math.abs(value) > deadband) {
      if (value > 0.0) {
        return (value - deadband) / (1.0 - deadband);
      } else {
        return (value + deadband) / (1.0 - deadband);
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
