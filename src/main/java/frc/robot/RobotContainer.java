// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.Intake_run;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;


import edu.wpi.first.wpilibj2.command.InstantCommand;
import static edu.wpi.first.wpilibj.XboxController.Button;
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
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  public static final Intake m_intake = new Intake();

  XboxController m_driverController = new XboxController(Constants.JoystickOI.driverStickPort);
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
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
}
