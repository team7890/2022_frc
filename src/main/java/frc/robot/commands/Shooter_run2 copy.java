// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.commands;

// import edu.wpi.first.math.filter.SlewRateLimiter;
// import edu.wpi.first.networktables.NetworkTableEntry;
// import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
// import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
// import edu.wpi.first.wpilibj2.command.CommandBase;

// import frc.robot.Constants;
// import frc.robot.subsystems.Shooter2;

// public class Shooter_run2 extends CommandBase {
//   SlewRateLimiter filter = new SlewRateLimiter(Constants.Shooter.slewRate);
//   private final double dSpeed;
//   private final Shooter2 m_Shooter2;
//   ShuffleboardTab tab = Shuffleboard.getTab("CompWindow");
//   NetworkTableEntry shooterSpeed = tab.add("Shooter Speed", 0.5).getEntry();
//   /** Creates a new Shooter_runIn. */
//   public Shooter_run2(Shooter2 Shooter_in, double speed_in) {
//     m_Shooter2 = Shooter_in;
//     dSpeed = speed_in;
//     addRequirements(m_Shooter2);
//     // Use addRequirements() here to declare subsystem dependencies.
//   }

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() 
//   {
//     m_Shooter2.stopShooter();
//   }

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() 
//   {
//     double speed = shooterSpeed.getDouble(-0.5);
//     // Ability to toggle slew rate
//     if (Constants.Shooter.applySlewRate)
//     {
//       m_Shooter2.runShooter(filter.calculate(speed));
//     }
//     else
//     {
//       m_Shooter2.runShooter(speed);
//     }
    
//   }

//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(boolean interrupted) 
//   {
//     m_Shooter2.stopShooter();
//     filter.reset(0.0);
//   }

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() 
//   {
//     return false;
//   }
// }

