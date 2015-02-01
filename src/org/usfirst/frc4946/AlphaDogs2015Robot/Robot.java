package org.usfirst.frc4946.AlphaDogs2015Robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.*;

import org.usfirst.frc4946.AlphaDogs2015Robot.commands.*;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.autonomous.*;
import org.usfirst.frc4946.AlphaDogs2015Robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    CommandGroup m_autonomousCommandGroup;
    SendableChooser m_pickAutonomous;
    private String m_autonomousStatus = "";

    public static OI m_oi;
    public static DriveTrain m_driveTrain;
    public static Grabber m_grabber;
    public static AirCompressor m_airCompressor;
    public static Elevator m_elevator;
    public static Feeder m_feeder;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    RobotMap.init();
        m_driveTrain = new DriveTrain();
        m_grabber = new Grabber();
        m_airCompressor = new AirCompressor();
        m_elevator = new Elevator();
        m_feeder = new Feeder();
        // OI must be constructed after subsystems. If the OI creates Commands 
        //(which it very likely will), subsystems are not guaranteed to be 
        // constructed yet. Thus, their requires() statements may grab null 
        // pointers. Bad news. Don't move it.
        m_oi = new OI();

        m_pickAutonomous = new SendableChooser();
        m_pickAutonomous.addDefault("Left position   | Move 1 space  | Tote pre-loaded",	new DefaultAutonomousScript(0, 1, true));
        m_pickAutonomous.addObject("Left position   | Move 1 space  | Tote not pre-loaded",	new DefaultAutonomousScript(0, 1, false));
        m_pickAutonomous.addObject("Left position   | Move 2 spaces | Tote pre-loaded",		new DefaultAutonomousScript(0, 2, true));
        m_pickAutonomous.addObject("Left position   | Move 2 spaces | Tote not pre-loaded",	new DefaultAutonomousScript(0, 2, false));
        m_pickAutonomous.addObject("Middle position | Move left     | Tote pre-loaded",		new DefaultAutonomousScript(1, 1, true));
        m_pickAutonomous.addObject("Middle position | Move left     | Tote not pre-loaded",	new DefaultAutonomousScript(1, 1, false));
        m_pickAutonomous.addObject("Middle position | Move right    | Tote pre-loaded",		new DefaultAutonomousScript(1, 2, true));
        m_pickAutonomous.addObject("Middle position | Move right    | Tote not pre-loaded",	new DefaultAutonomousScript(1,2, false));
        m_pickAutonomous.addObject("Right position  | Move 1 space  | Tote pre-loaded",		new DefaultAutonomousScript(2, 1, true));
        m_pickAutonomous.addObject("Right position  | Move 1 space  | Tote not pre-loaded",	new DefaultAutonomousScript(2, 1, false));
        m_pickAutonomous.addObject("Right position  | Move 2 spaces | Tote pre-loaded",		new DefaultAutonomousScript(2, 2, true));
        m_pickAutonomous.addObject("Right position  | Move 2 spaces | Tote not pre-loaded",	new DefaultAutonomousScript(2, 2, false));
        m_pickAutonomous.addObject("Any position    | Don't strafe  | Tote pre-loaded",		new DefaultAutonomousScript(0, 0, true));
        m_pickAutonomous.addObject("Any position    | Don't strafe  | Tote not pre-loaded",	new DefaultAutonomousScript(0, 0, false));
        SmartDashboard.putData("Select Autonomous Mode", m_pickAutonomous);
        SmartDashboard.putString("Autonomous Status", getAutonomousStatus());

    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
    	m_autonomousCommandGroup = (CommandGroup) m_pickAutonomous.getSelected();
        if(m_autonomousCommandGroup != null) {
            m_autonomousCommandGroup.start();
        } else {
            setAutonomousStatus("Autonomous command is null");
        }
    }
    
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        if(m_autonomousCommandGroup != null) {
            m_autonomousCommandGroup.cancel();
        }
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    public String getAutonomousStatus(){
    	return m_autonomousStatus;
    }
    
    public void setAutonomousStatus(String status){
    	m_autonomousStatus = status;
    }
}
