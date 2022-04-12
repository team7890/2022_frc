// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utils;

/** Add your docs here. */
public class ShooterInterpolator {
    // distance data points (from limelight ty)
    static double[] distances = {11.0, 14.0, 23.0};  // note distances must be increasing
    // shooter speeds and hood angles that worked for the above distances
    static double[] speeds = {-0.60, -0.59, -0.58};
    static double[] hoods = {-1000.0, 0.0, 0.0};

    // index of the next lowest data point with input distance and output the index to use for the low data point
    private static int lowIndex(double distance) {
        for (int i=0; i<distances.length; i++) {
            if (distance < distances[i]) return i-1;
        }
        return -2;
    }

    // weighted average of two values with
    //     inputs   lowVal = the next down datapoint
    //              highVal = the next up datapoint
    //              weight = the percent of the way from low to high
    //     output   the weighted average value
    private static double interpolate(double lowVal, double highVal, double weight) {
        return lowVal * (1.0 - weight) + highVal * weight;
    }

    // get speed to shoot with input distance and output the flywheel spin speed
    public static double getSpeed(double distance) {
        int index = lowIndex(distance);
        if (index == -1) {
            return speeds[0];
        }
        if (index == -2) {
            return speeds[distances.length - 1];
        }
        double lowDis = distances[index];
        double highDis = distances[index + 1];
        double interpolationDis = highDis - lowDis;
        double indexDis = distance - lowDis;
        double percentProgress = indexDis / interpolationDis;
        return interpolate(speeds[index], speeds[index + 1], percentProgress);
    }

    // get hood setting to shoot with input distance and output the hood setting
    public static double getHood(double distance) {
        int index = lowIndex(distance);
        if (index == -1) {
            return hoods[0];
        }
        if (index == -2) {
            return hoods[distances.length-1];
        }
        double lowDis = distances[index];
        double highDis = distances[index + 1];
        double interpolationDis = highDis - lowDis;
        double indexDis = distance - lowDis;
        double percentProgress = indexDis / interpolationDis;
        return interpolate(hoods[index], hoods[index + 1], percentProgress);
    }

    // print outputs from test distances
    private static void testDis(double distance) {
        System.out.println("---");
        System.out.println("dis:  " + distance);
        System.out.println("speed:  " + getSpeed(distance));
        System.out.println("hood:  " + getHood(distance));
    }

    // use to test interpolator
    public static void main(String[] args) {
        testDis(3.0);
        testDis(17.0);
    }
}
