// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.Constants.*;

// subsystems
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter2;
import frc.robot.subsystems.Shooter2tilt;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Indexer;

// commands
import frc.robot.commands.DriveAlignLimelight;
import frc.robot.commands.Shooter_run2;
import frc.robot.commands.Shooter_run2tilt;
import frc.robot.commands.auto.AutoShooter2_run;
import frc.robot.commands.Indexer_run;

// shuffleboard and network tables for debug
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.networktables.NetworkTableEntry;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoHoodShoot extends SequentialCommandGroup {

  /** Creates a new AutoHoodShoot. */
  public AutoHoodShoot(Limelight m_limelight, Shooter2 m_shooter2, Shooter2tilt m_shooter2tilt, DriveTrain m_drivetrain, Indexer m_indexer) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new Indexer_run(m_indexer, Constants.Indexer.indexRevSpeed).withTimeout(0.1),
      new DriveAlignLimelight(m_limelight, m_drivetrain).withTimeout(0.35),
      new ParallelCommandGroup(
        new Indexer_run(m_indexer, Constants.Shooter.indexShootSpeed),
        new AutoShooter2_run(m_shooter2tilt, m_shooter2, m_limelight.getTargetVerticalAngle(), m_limelight.getArea())
      )
    );
  }
}
