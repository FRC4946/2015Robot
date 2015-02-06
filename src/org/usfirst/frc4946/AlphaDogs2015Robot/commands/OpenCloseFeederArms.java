//package org.usfirst.frc4946.AlphaDogs2015Robot.commands;
//
//import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;
//
//import edu.wpi.first.wpilibj.command.Command;
//
///**
// *
// */
//public class OpenCloseFeederArms extends Command {
//
//public boolean m_feederOpen;
//	
//    public OpenCloseFeederArms(boolean isOpen) {
//        requires(Robot.m_feeder);
//        m_feederOpen = isOpen;
//    }
//
//    // Called just before this Command runs the first time
//    protected void initialize() {
//    	if (m_feederOpen == true) {
//    		Robot.m_feeder.engageFeederArms(true);
//    	}
//    	else {
//    		Robot.m_feeder.engageFeederArms(false);
//    	}
//    }
//
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//    }
//
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//        return true;
//    }
//
//    // Called once after isFinished returns true
//    protected void end() {
//    }
//
//    // Called when another command which requires one or more of the same
//    // subsystems is scheduled to run
//    protected void interrupted() {
//    }
//}
