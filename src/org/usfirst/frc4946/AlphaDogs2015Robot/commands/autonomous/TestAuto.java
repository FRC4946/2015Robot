package org.usfirst.frc4946.AlphaDogs2015Robot.commands.autonomous;

import org.usfirst.frc4946.AlphaDogs2015Robot.commands.grabberarms.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TestAuto extends CommandGroup {
    
	private int m_initialPosition = 0;
	private int m_mouvementDirectionOrAmount = 0;
	private boolean m_toteIsPreLoaded = false;
	
     public TestAuto(int position, int movementDirection, boolean isPreLoaded) {
    	 
    	 
		m_initialPosition = position;
		m_mouvementDirectionOrAmount = movementDirection;
		m_toteIsPreLoaded = isPreLoaded;
		SmartDashboard.putString("Autonomous Status", "SPOOP");
		
		
		addSequential(new SetLeftGrabberArm(true));
		addSequential(new SetRightGrabberArm(3));
		//addSequential(new OpenGrabberArms());


		
		SmartDashboard.putString("Autonomous Status", "NOT SPOOP");

    }
}
