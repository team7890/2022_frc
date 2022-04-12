// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.commands.Shooter_run2;
import frc.robot.commands.Shooter_run2tiltToPosition;
import frc.robot.commands.Indexer_run;

import frc.robot.subsystems.Shooter2;
import frc.robot.subsystems.Shooter2tilt;
import frc.robot.subsystems.Indexer;

import frc.robot.Constants;
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ImprovedShooterLow extends SequentialCommandGroup {
  /** Creates a new ImprovedShooter. */
  public ImprovedShooterLow(Shooter2 m_autoShooter, Indexer m_autoIndexer, Shooter2tilt m_autoShooterTilt) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands
    (
      new ParallelCommandGroup(
        new Indexer_run(m_autoIndexer, - Constants.Indexer.indexRevSpeed * 5),
        new Shooter_run2tiltToPosition(m_autoShooterTilt, Constants.Shooter.shooterLowHood)
      ).withTimeout(0.4),
      new Shooter_run2(m_autoShooter, Constants.Shooter.shooterLowSpeed).withTimeout(0.25),
      new ParallelCommandGroup
      (
        new Shooter_run2(m_autoShooter, Constants.Shooter.shooterLowSpeed),
        new Indexer_run(m_autoIndexer, Constants.Shooter.indexShootSpeed),
        new Shooter_run2tiltToPosition(m_autoShooterTilt, Constants.Shooter.shooterLowHood)
      )
    );
  }
}
