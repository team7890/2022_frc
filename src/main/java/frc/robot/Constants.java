// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants 
{
    // Toggle Slew Rate
    // public static final boolean applySlewRate = true;

    public static final double DRIVETRAIN_TRACKWIDTH_METERS = (21.5*2.54)/100; // FIXME Measure and set trackwidth
    /**
     * The front-to-back distance between the drivetrain wheels.
     *
     * Should be measured from center to center.
     */
    public static final double DRIVETRAIN_WHEELBASE_METERS = (21.5*2.54)/100; // FIXME Measure and set wheelbase

    // public static final int DRIVETRAIN_PIGEON_ID = 0; // FIXME Set Pigeon ID

    // steer offset values
    // Rotate each module so the bevel gear on the sides of each wheel are pointing to the robot's left.
    // reset offsets to 0.0 before doing the test

    // values for test drive chassis
    // public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(352.2); // FIXME Measure and set front left steer offset
    // public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(215.9); // FIXME Measure and set front right steer offset
    // public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(143.8); // FIXME Measure and set back left steer offset
    // public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(90.1); // FIXME Measure and set back right steer offset

    // values for comp bot measured on 3/4/22 by John C.



    // DriveTrain
    public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(341.5); // FIXME Measure and set front left steer offset
    public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(82.6); // FIXME Measure and set front right steer offset
    public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(254.9); // FIXME Measure and set back left steer offset
    public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(356.2); // FIXMpublic static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(341.5); // FIXME Measure and set front left steer offset
    // public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(0); // FIXME Measure and set front left steer offset
    // public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(0); // FIXME Measure and set front right steer offset
    // public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(0); // FIXME Measure and set back left steer offset
    // public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(0); // FIXMpublic static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(341.5); // FIXME Measure and set front left steer offset
    




    public final class CanID

    {
        public static final int Intake = 10;
        public final static int Indexer = 11;
        public static final int ShooterRight = 12;
        public static final int ShooterLeft = 13;
        public static final int IntakeWinch = 15;
        public static final int ClimberLeft = 16;
        public static final int ClimberRight = 17;

        // Drive Train CanIDs
        public static final int FRONT_LEFT_MODULE_DRIVE_MOTOR = 21; // FIXME Set front left module drive motor ID
        public static final int FRONT_LEFT_MODULE_STEER_MOTOR = 22; // FIXME Set front left module steer motor ID
        public static final int FRONT_LEFT_MODULE_STEER_ENCODER = 23; // FIXME Set front left steer encoder ID
    
        public static final int FRONT_RIGHT_MODULE_DRIVE_MOTOR = 31; // FIXME Set front right drive motor ID
        public static final int FRONT_RIGHT_MODULE_STEER_MOTOR = 32; // FIXME Set front right steer motor ID
        public static final int FRONT_RIGHT_MODULE_STEER_ENCODER = 33; // FIXME Set front right steer encoder ID
    
        public static final int BACK_LEFT_MODULE_DRIVE_MOTOR = 41; // FIXME Set back left drive motor ID
        public static final int BACK_LEFT_MODULE_STEER_MOTOR = 42; // FIXME Set back left steer motor ID
        public static final int BACK_LEFT_MODULE_STEER_ENCODER = 43; // FIXME Set back left steer encoder ID
    
        public static final int BACK_RIGHT_MODULE_DRIVE_MOTOR = 51; // FIXME Set back right drive motor ID
        public static final int BACK_RIGHT_MODULE_STEER_MOTOR = 52; // FIXME Set back right steer motor ID
        public static final int BACK_RIGHT_MODULE_STEER_ENCODER = 53; // FIXME Set back right steer encoder ID
    }

    public final class JoystickOI
    {
        public static final int driverStickPort = 0;
        public static final int coStickPort = 1;
    }

    public final class Intake
    {
        public static final double slewRate = 0.7;
        public static final int currentLimit = 10;
        public static final double intakeSpeed = 0.9;
        public static final double intakeWinchSpeed = 0.6;
        public static final boolean applySlewRate = false;
    }

    // public final class IntakeWinch
    // {
    //     // public static final double slewRate = 0.7;
    //     // public static final int currentLimit = 10;
    //     // public static final double intakeSpeed = 1.0;
    //     // public static final double intakeWinchSpeed = .4;
    //     // public static final boolean applySlewRate = true;
    // }

    public final class Indexer
    {
        public static final double slewRate = 0.7;
        public static final int currentLimit = 10;
        public static final double indexRevSpeed = -0.1;
        public static final double indexSpeed = 0.25;
        public static final boolean applySlewRate = true;
    }

    public final class Shooter
    {
        public static final double slewRate = 0.4;
        public static final int currentLimit = 40;
        public static final double shooterSpeed = 0.265;
        public static final double shooterHighSpeed = 0.40;
        public static final double shooterRevSpeed = -0.25;
        public static final boolean applySlewRate = false;
        public static final double indexShootSpeed = 0.8;
    }
    
    public final class Climber
    {
        public static final double slewRate = 0.5;
        public static final int currentLimit = 60;
        public static final double elevatorSpeed = 0.1;
        public static final double elevatorRevSpeed = -0.05;
        public static final boolean applySlewRate = false;
    }
}