// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.commands.Shooter_run;
import frc.robot.commands.Indexer_run;

import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Indexer;

import frc.robot.Constants;
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ImprovedShooterLow extends SequentialCommandGroup {
  /** Creates a new ImprovedShooter. */
  public ImprovedShooterLow(Shooter m_autoShooter, Indexer m_autoIndexer) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands
    (
      new Indexer_run(m_autoIndexer, - Constants.Shooter.indexShootSpeed).withTimeout(0.2),
      new Shooter_run(m_autoShooter, Constants.Shooter.shooterSpeed).withTimeout(0.3),
      new ParallelCommandGroup
      (
        new Shooter_run(m_autoShooter, Constants.Shooter.shooterSpeed),
        new Indexer_run(m_autoIndexer, Constants.Shooter.indexShootSpeed)
      )
    );
  }
}
