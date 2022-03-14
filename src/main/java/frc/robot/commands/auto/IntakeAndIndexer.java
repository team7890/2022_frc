// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.commands.Intake_run;
import frc.robot.commands.Indexer_run;

import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Indexer;

import frc.robot.Constants;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class IntakeAndIndexer extends SequentialCommandGroup {
  /** Creates a new IntakeAndIndexer. */
  public IntakeAndIndexer(Indexer m_autoIndexer, Intake m_autoIntake) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
    new ParallelCommandGroup(
      new Indexer_run(m_autoIndexer, Constants.Indexer.indexSpeed),
      new Intake_run(m_autoIntake, Constants.Intake.intakeSpeed)
    )
    );
  }
}
