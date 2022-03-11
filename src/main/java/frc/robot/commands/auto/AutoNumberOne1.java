// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.commands.auto.AutoDriveTrain_run;
import frc.robot.commands.DriveTrain_run;

import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoNumberOne1 extends SequentialCommandGroup {

  /** Creates a new AutoNumberOne1. */
  public AutoNumberOne1(DriveTrain m_autoDriveTrain) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new AutoDriveTrain_run(m_autoDriveTrain, 1.0, 0.0, 0.8).withTimeout(1.0));

    addCommands(new AutoDriveTrain_run(m_autoDriveTrain, -0.8, 0.0, -1.0).withTimeout(1.5));

    addCommands(new AutoDriveTrain_run(m_autoDriveTrain, 0.0, 0.0, 1.0).withTimeout(10));

    // addCommands(new AutoDriveTrain_run(m_autoDriveTrain, -0.4, 0.0, 0.0).withTimeout(1), new AutoDriveTrain_run(m_autoDriveTrain, 0.0, 0.0, 1.0).withTimeout(1));
  }
}
