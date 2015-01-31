package org.usfirst.frc4946.AlphaDogs2015Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;

/**
 * A command to drive the robot with the strafe wheel.
 * Swaps the X value of the joystick from turning to strafing.
 *
 * @author Ashish
 */
public class  DriveWithJoystickStrafe extends Command {

    public DriveWithJoystickStrafe() {
        requires(Robot.m_driveTrain);
    }

    protected void initialize() {
    }

    protected void execute() {
    	double Yval = Robot.m_oi.getDriveJoystick().getY();
    	double Xval = Robot.m_oi.getDriveJoystick().getX();
    	double Zval = Robot.m_oi.getDriveJoystick().getZ();
    	
    	Robot.m_driveTrain.strafeDrive(Yval, -Zval, Xval);
    }

    protected boolean isFinished() {
        return false; // Runs until interrupted
    }

    protected void end() {
		Robot.m_driveTrain.drive(0, 0); // Stop driving
    }

    protected void interrupted() {
    	end();
    }
}
