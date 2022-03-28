// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.commands.auto.AutoDriveTrain_run;
import frc.robot.commands.Shooter_run;
import frc.robot.commands.Indexer_run;
import frc.robot.commands.Intake_run;
import frc.robot.commands.IntakeWinch_run;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeWinch;
import frc.robot.Constants;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoLeftTarmacTwoBall extends SequentialCommandGroup {
  /** Creates a new AutoLeftTarmac. */
  public AutoLeftTarmacTwoBall(DriveTrain m_autoDriveTrain, Shooter m_autoShooter, Indexer m_autoIndexer, Intake m_autoIntake, IntakeWinch m_autoIntakeWinch)
  {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());

    // addCommands
    // (

    //   new ParallelCommandGroup
    //   (
    //     // index and shoot
    //     new Indexer_run(m_autoIndexer, 0.75),
    //     new Shooter_run(m_autoShooter, Constants.Shooter.shooterHighSpeed)
// ?      ).withTimeout(4.0),
      
      
      // Drive train, variables in AutoRightTarmacOne
      // new AutoDriveTrain_run(m_autoDriveTrain, -1.0, 0.35, 0.0).withTimeout(0.5),
      addCommands
      (
        
        new ParallelCommandGroup
        (
          // index and shoot
          new Indexer_run(m_autoIndexer, 0.75),
          new Shooter_run(m_autoShooter, Constants.Shooter.shooterHighSpeed)
        ).withTimeout(4.0),
        // Drive train, variables in AutoRightTarmacOne
        new AutoDriveTrain_run(m_autoDriveTrain, -0.95, 0.0, 0.0).withTimeout(1.3),
  
  
  
        // WAITING 10 SECONDS
        // new AutoDriveTrain_run(m_autoDriveTrain, 0.0, 0.0, 0.0).withTimeout(10),
  
  
  
      
          // driving, intaking, intkae winching
       
        
  
      new ParallelCommandGroup
      (
        // driving, intaking, intkae winching
        // new AutoDriveTrain_run(m_autoDriveTrain, -1.0, 0.35, 0.0).withTimeout(1.0),
        new IntakeWinch_run(m_autoIntakeWinch, 0.2).withTimeout(1.5),
        new Intake_run(m_autoIntake, 0.9).withTimeout(1.5),
        new AutoDriveTrain_run(m_autoDriveTrain, -0.95, 1.0, 0.0).withTimeout(1.0)
      ),

      new ParallelCommandGroup
      (
        // intake, intake winch, drive
        new AutoDriveTrain_run(m_autoDriveTrain, 0.95, -1.0, 0.0).withTimeout(1.0),

        new IntakeWinch_run(m_autoIntakeWinch, -0.2).withTimeout(1.45),
        new Intake_run(m_autoIntake, 0.9).withTimeout(2.0)
        // new AutoDriveTrain_run(m_autoDriveTrain, 1.0, -0.8, -0.225).withTimeout(1.7)
      ),
  
  
  
        // WAITING 10 SECONDS
        // new AutoDriveTrain_run(m_autoDriveTrain, 0.0, 0.0, 0.0).withTimeout(10),
  
        new AutoDriveTrain_run(m_autoDriveTrain, 0.95, 0.0, 0.0).withTimeout(1.3),

  
      
          // driving, intaking, intkae winching

      new ParallelCommandGroup
      (
        // index and shoot
        new Indexer_run(m_autoIndexer, 0.75),
        new Shooter_run(m_autoShooter, Constants.Shooter.shooterHighSpeed)
      ).withTimeout(4.0)


      // new AutoDriveTrain_run(m_autoDriveTrain, 0.0, 0.0, 1.0).withTimeout(2.0),
      // new AutoDriveTrain_run(m_autoDriveTrain, 0, 0, 0.0).withTimeout(2.0),

      
    );
  }
}
