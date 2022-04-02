// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// package frc.robot.commands;

// import edu.wpi.first.wpilibj2.command.CommandBase;

// import frc.robot.subsystems.WindmillClimber;

// import java.util.function.DoubleSupplier;


// public class WindmillClimber_run extends CommandBase {
//   /** Creates a new WindmillClimber_run. */
//   private final WindmillClimber m_WindmillClimber;

//   private final DoubleSupplier m_speedSupplier;


//   public WindmillClimber_run(WindmillClimber climber_in, DoubleSupplier speedSupplier_in) {
//     this.m_WindmillClimber = climber_in;
//     this.m_speedSupplier = speedSupplier_in;

//     addRequirements(climber_in);

//     // Use addRequirements() here to declare subsystem dependencies.
//   }

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {}

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {
//     m_WindmillClimber.runWindmillClimber(m_speedSupplier.getAsDouble());
//   }

//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(boolean interrupted) {
//     m_WindmillClimber.stopWindmillClimber();
//   }

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     return false;
//   }
// }




// // import edu.wpi.first.math.filter.SlewRateLimiter;
// // import edu.wpi.first.wpilibj2.command.CommandBase;

// // import frc.robot.Constants;
// // import frc.robot.subsystems.WindmillClimber;

// // public class WindmillClimber_run extends CommandBase {
// //   // SlewRateLimiter filter = new SlewRateLimiter(Constants.Windmill.slewRate);
// //   private final double dSpeed;
// //   private final WindmillClimber m_WindmillClimber;
// //   /** Creates a new WindmillClimber_runIn. */
// //   public WindmillClimber_run(WindmillClimber WindmillClimber_in, double speed_in) {
// //     m_WindmillClimber = WindmillClimber_in;
// //     dSpeed = speed_in;
// //     addRequirements(m_WindmillClimber);
// //     // Use addRequirements() here to declare subsystem dependencies.
// //   }

// //   // Called when the command is initially scheduled.
// //   @Override
// //   public void initialize() 
// //   {
// //     m_WindmillClimber.stopWindmillClimber();
// //   }

// //   // Called every time the scheduler runs while the command is scheduled.
// //   @Override
// //   public void execute() 
// //   {
// //     // Ability to toggle slew rate
// //     // if (Constants.Windmill.applySlewRate)
    
// //       m_WindmillClimber.runWindmillClimber(dSpeed);

// //   }

// //   // Called once the command ends or is interrupted.
// //   @Override
// //   public void end(boolean interrupted) 
// //   {
// //     m_WindmillClimber.stopWindmillClimber();
// //     // filter.reset(0.0);
// //   }

// //   // Returns true when the command should end.
// //   @Override
// //   public boolean isFinished() 
// //   {
// //     return false;
// //   }
// // }

