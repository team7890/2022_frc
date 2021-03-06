// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.commands.auto.AutoDriveTrain_run;
// import frc.robot.commands.DriveTrain_run;
// import frc.robot.commands.auto.AutoShooter_run;
import frc.robot.commands.Shooter_run;
import frc.robot.commands.Indexer_run;
import frc.robot.commands.Intake_run;
import frc.robot.commands.IntakeWinch_run;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeWinch;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoNumberOne1 extends SequentialCommandGroup 
{

  /** Creates a new AutoNumberOne1. */
  
  // Testing auto phase
  public AutoNumberOne1(DriveTrain m_autoDriveTrain, Shooter m_autoShooter, Indexer m_autoIndexer, Intake m_autoIntake, IntakeWinch m_autoIntakeWinch) 
  {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    // AutoDriveTrain_run(m_autoDriveTrain, joystick_Y, joystick_X, rotation)
    // Moving and rotating
    addCommands(new AutoDriveTrain_run(m_autoDriveTrain, 1.0, 0.0, 0.0).withTimeout(1.0));

    addCommands(new AutoDriveTrain_run(m_autoDriveTrain, 0.0, 0.0, -1.0).withTimeout(1.0));

    addCommands(
      // Moving and intaking
      new ParallelCommandGroup
      (
        new AutoDriveTrain_run(m_autoDriveTrain, 0.7, -0.9, -1.0).withTimeout(1.0),
        new IntakeWinch_run(m_autoIntakeWinch, 0.2).withTimeout(2),
        new Intake_run(m_autoIntake, 0.9).withTimeout(2)
      ),
      // Intaking while moving the winch back to its original(ish) position
      new ParallelCommandGroup
      (
        new Intake_run(m_autoIntake, 0.9),
        new IntakeWinch_run(m_autoIntakeWinch, -0.2)
      ).withTimeout(1.8)
    );
    // Moving
    addCommands(new AutoDriveTrain_run(m_autoDriveTrain, -0.8, 0.0, -0.60).withTimeout(2));
    // FIRE! (and move)
    addCommands(new ParallelCommandGroup
    (
      new AutoDriveTrain_run(m_autoDriveTrain, 0.0, -0.3, 0.0).withTimeout(0.75),
      new Indexer_run(m_autoIndexer, 0.75),
      new Shooter_run(m_autoShooter, 0.3)
    ).withTimeout(2));

    // Dance!
    addCommands(new AutoDriveTrain_run(m_autoDriveTrain, 0.0, 0.0, 0.0).withTimeout(3));

    addCommands(new AutoDriveTrain_run(m_autoDriveTrain, 0.0, 0.0, 1.5).withTimeout(7));
  }
}
