// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
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
public class ImprovedShooter extends SequentialCommandGroup {
  /** Creates a new ImprovedShooter. */
  public ImprovedShooter(Shooter2 m_autoShooter, Indexer m_autoIndexer, Shooter2tilt m_shooter2tilt) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands
    (
      new Indexer_run(m_autoIndexer, - Constants.Shooter.indexShootSpeed).withTimeout(0.2),
        new Shooter_run2(m_autoShooter, Constants.Shooter.shooterHighSpeed).withTimeout(0.3),
      new ParallelCommandGroup
      (
        new Shooter_run2(m_autoShooter, Constants.Shooter.shooterHighSpeed),
        new Indexer_run(m_autoIndexer, Constants.Shooter.indexShootSpeed)
      )
    );
  }



  // private ShuffleboardTab tab = Shuffleboard.getTab("CompWindow");
  // private NetworkTableEntry shooterSpeed = tab.add("Shooter Speed", 0.5).getEntry();
  // private NetworkTableEntry hoodSetting = tab.add("Hood Setting", -1000.0).getEntry();
  // public ImprovedShooter(Shooter2 m_autoShooter, Indexer m_autoIndexer, Shooter2tilt m_shooter2tilt) {
  //   // Add your commands in the addCommands() call, e.g.
  //   // addCommands(new FooCommand(), new BarCommand());
  //   double speed = shooterSpeed.getDouble(-0.5);
  //   double hood = hoodSetting.getDouble(-1000.0);

  //   System.out.println("--- Improved Shooter Data ---");
  //   System.out.println("Speed:  " + speed);
  //   System.out.println("Hood:  " + hood);
  //   System.out.println("");
  //   System.out.println("");
  //   System.out.println("");

  //   addCommands
  //   (
  //     new Indexer_run(m_autoIndexer, - Constants.Shooter.indexShootSpeed).withTimeout(0.2),
  //     new ParallelCommandGroup(
  //       new Shooter_run2(m_autoShooter, speed),
  //       new Shooter_run2tiltToPosition(m_shooter2tilt, hood)
  //     ).withTimeout(0.3),
  //     new ParallelCommandGroup
  //     (
  //       new Shooter_run2(m_autoShooter, speed),
  //       new Indexer_run(m_autoIndexer, Constants.Shooter.indexShootSpeed),
  //       new Shooter_run2tiltToPosition(m_shooter2tilt, hood)
  //     )
  //   );
  // }



}
