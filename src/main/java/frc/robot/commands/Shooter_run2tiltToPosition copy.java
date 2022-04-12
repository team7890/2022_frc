// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.commands;

// import edu.wpi.first.networktables.NetworkTableEntry;
// import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
// import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
// import edu.wpi.first.wpilibj2.command.CommandBase;

// import frc.robot.Constants;
// import frc.robot.subsystems.Shooter2tilt;


// public class Shooter_run2tiltToPosition extends CommandBase {

//   private final double dPos;
//   private final Shooter2tilt m_Shooter2Tilt;
//   ShuffleboardTab tab = Shuffleboard.getTab("CompWindow");
//   NetworkTableEntry hoodSetting = tab.add("Hood Setting", -1000.0).getEntry();
//   /** Creates a new Shooter_run2tiltToPosition. */
//   public Shooter_run2tiltToPosition(Shooter2tilt m_shooter2tilt_in, double pos_in) {
//     m_Shooter2Tilt = m_shooter2tilt_in;
//     dPos = pos_in;

//     addRequirements(m_Shooter2Tilt);


//     // Use addRequirements() here to declare subsystem dependencies.
//   }

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {
//     m_Shooter2Tilt.stopShooter();
//   }

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {
//     double dPos = hoodSetting.getDouble(-1000.0);
//     m_Shooter2Tilt.tiltToPosition(dPos);
//   }

//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(boolean interrupted) {
//     m_Shooter2Tilt.stopShooter();
//   }

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     return false;
//   }
// }
