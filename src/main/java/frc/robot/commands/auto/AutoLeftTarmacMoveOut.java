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
// import frc.robot.commands.Intake_run;
// import frc.robot.commands.IntakeWinch_run;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter2;
import frc.robot.subsystems.Shooter2tilt;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeWinch;
import frc.robot.Constants;
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoLeftTarmacMoveOut extends SequentialCommandGroup {
  /** Creates a new AutoLeftTarmac. */
  public AutoLeftTarmacMoveOut(DriveTrain m_autoDriveTrain, Shooter2 m_autoShooter, Indexer m_autoIndexer, Intake m_autoIntake, IntakeWinch m_autoIntakeWinch, Shooter2tilt m_autoShooterTilt)
  {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());

    addCommands
    (
      
      new ParallelCommandGroup
      (
        // index and shoot
        new Indexer_run(m_autoIndexer, Constants.Shooter.indexShootSpeed),
        new Shooter_run2(m_autoShooter, Constants.Shooter.shooterHighSpeed),
        new Shooter_run2tiltToPosition(m_autoShooterTilt, 0)
      ).withTimeout(4.0),
      // Drive train, variables in AutoRightTarmacOne

      new AutoDriveTrain_run(m_autoDriveTrain, -0.95, 0.0, 0.0).withTimeout(1.7),



      // WAITING 10 SECONDS
      // new AutoDriveTrain_run(m_autoDriveTrain, 0.0, 0.0, 0.0).withTimeout(10),



    
        // driving, intaking, intkae winching
 
        new AutoDriveTrain_run(m_autoDriveTrain, -0.95, 0.0, 0.0).withTimeout(1.0)
     
      


      // new AutoDriveTrain_run(m_autoDriveTrain, 0.0, 0.0, 1.0).withTimeout(2.0),
      // new AutoDriveTrain_run(m_autoDriveTrain, 0, 0, 0.0).withTimeout(2.0),

      
    );
  }
}
