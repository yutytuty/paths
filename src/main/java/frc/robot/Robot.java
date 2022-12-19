// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.spikes2212.command.drivetrains.TankDrivetrain;
import com.spikes2212.command.drivetrains.commands.DriveArcadeWithPID;
import com.spikes2212.command.drivetrains.commands.DriveTankWithPID;
import com.spikes2212.control.PIDSettings;
import com.spikes2212.dashboard.Namespace;
import com.spikes2212.dashboard.RootNamespace;
import com.spikes2212.path.OdometryHandler;
import com.spikes2212.path.Paths;
import com.spikes2212.path.PurePursuitController;
import com.spikes2212.path.Waypoint;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Robot extends TimedRobot {
    private List<Waypoint> path;

    private final RootNamespace rootNamespace = new RootNamespace("paths");
    Namespace PIDNamespace = rootNamespace.addChild("PID");

    private final Supplier<Double> kP = PIDNamespace.addConstantDouble("kP", 1);
    private final Supplier<Double> kI = PIDNamespace.addConstantDouble("kI", 0);
    private final Supplier<Double> kD = PIDNamespace.addConstantDouble("kD", 0);
    private final Supplier<Double> tolerance = PIDNamespace.addConstantDouble("Tolerance", 0);
    private final Supplier<Double> waitTime = PIDNamespace.addConstantDouble("Wait Time", 1);
    private final PIDSettings settings = new PIDSettings(kP, kI, kD, tolerance, waitTime);

    private Drivetrain drivetrain;
    private OdometryHandler odometryHandler;
    private PurePursuitController purePursuitController;

    @Override
    public void robotInit() {
        path = Paths.loadFromCSV("~/Desktop/yes.csv");
        drivetrain = Drivetrain.getInstance();
        odometryHandler = new OdometryHandler(drivetrain::getLeftTicks, drivetrain::getRightTicks, drivetrain::getAngle, 0, 0);
        purePursuitController = new PurePursuitController(odometryHandler, path, )
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void autonomousInit() {

    }

    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {

    }

    @Override
    public void teleopPeriodic() {
    }

    @Override
    public void testInit() {
        CommandScheduler.getInstance().cancelAll();
    }

    @Override
    public void testPeriodic() {
    }

    @Override
    public void simulationInit() {
    }

    @Override
    public void simulationPeriodic() {
    }
}
