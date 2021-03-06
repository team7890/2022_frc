// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.commands.auto.AutoDriveTrain_run;
import frc.robot.commands.Shooter_run2;
import frc.robot.commands.Shooter_run2tiltToPosition;
import frc.robot.commands.Indexer_run;
import frc.robot.commands.Intake_run;
import frc.robot.commands.IntakeWinch_run;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter2;
import frc.robot.subsystems.Shooter2tilt;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeWinch;
import frc.robot.subsystems.Limelight;
import frc.robot.Constants;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoRightTarmacOne extends SequentialCommandGroup {
  /** Creates a new AutoRightTarmacOne. */
  public AutoRightTarmacOne(DriveTrain m_autoDriveTrain, Shooter2 m_autoShooter2, Indexer m_autoIndexer, Intake m_autoIntake, IntakeWinch m_autoIntakeWinch, Shooter2tilt m_autoShooterTilt, Limelight m_limelight) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    
    addCommands
    (
      // new ParallelCommandGroup
      // (
        // Indexer and shooter run, variables are speed
        // new Indexer_run(m_autoIndexer, Constants.Shooter.indexShootSpeed),
        // new Shooter_run2(m_autoShooter2, Constants.Shooter.shooterHighSpeed),
        // new Shooter_run2tiltToPosition(m_autoShooterTilt, 0)
      // ).withTimeout(2.0),

      // Drives, first variable is front/back, second is right/left, third is rotation
      new AutoDriveTrain_run(m_autoDriveTrain, -0.4, -1.0, 0.0).withTimeout(0.5),

      new ParallelCommandGroup
      (
        // Driving, intaking, and moving the intake winch, both variables for intake related things are speed variables
        new AutoDriveTrain_run(m_autoDriveTrain, -0.3, -1.0, 0.0).withTimeout(1.1),
        new IntakeWinch_run(m_autoIntakeWinch, 0.2).withTimeout(1.5),
        new Intake_run(m_autoIntake, 0.9).withTimeout(1.5)
      ),


      new ParallelCommandGroup
      (
        // Driving, intaking, and reversing the intake winch
        new IntakeWinch_run(m_autoIntakeWinch, -0.2).withTimeout(1.45),
        new Intake_run(m_autoIntake, 0.9).withTimeout(1.75)
        // IF auto continues to veer right, lower the first number in this command
        // new AutoDriveTrain_run(m_autoDriveTrain, 0.5, 1.0, 0.0).withTimeout(2.0)
      ),

      // new AutoHoodShoot(m_limelight, m_autoShooter2, m_autoShooterTilt, m_autoDriveTrain, m_autoIndexer).withTimeout(4.0)

      
      new ParallelCommandGroup
      (
        // Shooting and indexing
        new Indexer_run(m_autoIndexer, 0.75),
        new Shooter_run2(m_autoShooter2, Constants.Shooter.shooterHighSpeed)
      ).withTimeout(4.0)
    );
  }
}
