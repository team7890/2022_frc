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
    public final class CanID
    {
        public static final int Intake = 10;
        public final static int Indexer = 11;
        public static final int ShooterRight = 12;
        public static final int ShooterLeft = 13;
    }
    public final class Intake
    {
        public static final double slewRate = 0.1;
        public static final int currentLimit = 5;
        public static final double intakeSpeed = 0.4;
    }
    public final class JoystickOI
    {
        public static final int driverStickPort = 0;
    }
    public final class Indexer
    {
        public static final double slewRate = 0.1;
        public static final int currentLimit = 5;
        public static final double indexRevSpeed = -0.1;
        public static final double indexSpeed = 0.125;
    }
    public final class Shooter
    {
        public static final double slewRate = 0.4;
        public static final int currentLimit = 5;
        public static final double shooterSpeed = 0.4;
        public static final double shooterRevSpeed = -0.1;
    }
    public final class Elevator
    {
        public static final double slewRate = 0.1;
        public static final int currentLimit = 10;
        public static final double elevatorSpeed = 0.1;
        public static final double elevatorRevSpeed = -0.05;
    }
}