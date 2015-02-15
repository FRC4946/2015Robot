package org.usfirst.frc4946.AlphaDogs2015Robot.commands.grabberarms;

import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Do nothing
 * @author Matthew
 */
public class GrabberDoNothing extends Command {

    public GrabberDoNothing() {
        requires(Robot.m_rightGrabber);
    }

    protected void initialize() {
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
