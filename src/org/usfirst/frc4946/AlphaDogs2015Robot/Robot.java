package org.usfirst.frc4946.AlphaDogs2015Robot;

import org.usfirst.frc4946.AlphaDogs2015Robot.commands.autonomous.DriveAutonomousScript;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.autonomous.OpenGrabberArms;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.autonomous.RecyclingContainerAutonomousScript;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.autonomous.RecyclingContainerPlusToteAutonomousScript;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.autonomous.ToteStackStrafeAutonomousScript;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.autonomous.ToteStackStraightAutonomousScript;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.strafedropper.ActuateStrafeSolenoid;
import org.usfirst.frc4946.AlphaDogs2015Robot.subsystems.AirCompressor;
import org.usfirst.frc4946.AlphaDogs2015Robot.subsystems.DriveTrain;
import org.usfirst.frc4946.AlphaDogs2015Robot.subsystems.Elevator;
import org.usfirst.frc4946.AlphaDogs2015Robot.subsystems.LeftGrabber;
import org.usfirst.frc4946.AlphaDogs2015Robot.subsystems.RightGrabber;
import org.usfirst.frc4946.AlphaDogs2015Robot.subsystems.StrafeDropper;
import org.usfirst.frc4946.AlphaDogs2015Robot.subsystems.Transmission;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    SendableChooser m_autonomousStartingPosition;
    SendableChooser m_autonomousAmountOrDirectionToMove;
    private String[] autonomousStatuses = {"NAN", "NAN", "NAN"};

    public static OI m_oi;
    public static DriveTrain m_driveTrain;
    public static StrafeDropper m_strafeDropper;
    public static RightGrabber m_rightGrabber;
    public static LeftGrabber m_leftGrabber;
    public static AirCompressor m_airCompressor;
    public static Elevator m_elevator;
    public static Transmission m_transmission;

    
    double m_proportional = 0.0;
    double m_integral = 0.0;
    double m_derivative = 0.0;

   //public static Feeder m_feeder;
    
//    private CameraServer m_camServer;
    int session;
    Image frame;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	    	
    	
        //m_proportional = m_prefs.getDouble("ProportionalValue", 0.0);
        //m_integral = m_prefs.getDouble("IntegralValue", 0.0);
        //m_derivative = m_prefs.getDouble("DerivativeValue", 0.0);

        RobotMap.init();
        m_driveTrain = new DriveTrain(); m_driveTrain.initGyro();
        m_rightGrabber = new RightGrabber();
        m_leftGrabber = new LeftGrabber();
        m_airCompressor = new AirCompressor();
        m_strafeDropper = new StrafeDropper();
        m_elevator = new Elevator(m_proportional, m_integral, m_derivative);
        m_transmission = new Transmission();
        //m_feeder = new Feeder();

        m_oi = new OI(); // Make sure you define this AFTER the subsystems.

        
        
        
        // =*=*=*=*=*= Autonomous Setup =*=*=*=*=*=
        
        // Select the starting position
        m_autonomousStartingPosition = new SendableChooser();
        m_autonomousStartingPosition.addDefault("Tote stacking: Left position", 0);
        m_autonomousStartingPosition.addObject("Tote stacking: Middle position", 1);
        m_autonomousStartingPosition.addObject("Tote stacking: Right position", 2);
        SmartDashboard.putData("Select autonomous starting position", m_autonomousStartingPosition);
        
        // Select the number of positions to move
        m_autonomousAmountOrDirectionToMove = new SendableChooser();
        m_autonomousAmountOrDirectionToMove.addDefault("Tote stacking: Do not pick up any additional totes", 0);
        m_autonomousAmountOrDirectionToMove.addObject("Tote stacking: Move 1 space OR move left", 1);
        m_autonomousAmountOrDirectionToMove.addObject("Tote stacking: Move 2 spaces OR move right", 2);
        SmartDashboard.putData("directionOrAmount", m_autonomousAmountOrDirectionToMove);

        
        // Select whether the tote is pre-loaded or not
        //m_autonomousToteIsPreLoaded = new SendableChooser();
       //m_autonomousToteIsPreLoaded.addDefault("Tote is pre-loaded", true);
        //m_autonomousToteIsPreLoaded.addObject("Tote is not pre-loaded", false);
       // SmartDashboard.putData("Select whether or not the tote is pre-loaded", m_autonomousToteIsPreLoaded);
        
        m_pickAutonomous = new SendableChooser();
        m_pickAutonomous.addDefault("Drive forward",													new DriveAutonomousScript());
        m_pickAutonomous.addObject("Pickup the recycling container",									new RecyclingContainerAutonomousScript());
        m_pickAutonomous.addObject("Recycling container + tote (Robot parallel to driver's wall)",		new RecyclingContainerPlusToteAutonomousScript(false));
        m_pickAutonomous.addObject("Recycling container + tote (Robot perpendicular to driver's wall *RECOMMENDED*)",	new RecyclingContainerPlusToteAutonomousScript(true));
        m_pickAutonomous.addObject("Tote stacking - Square. See other selectors *DO NOT USE*",			new ToteStackStrafeAutonomousScript(-1, -1));
        m_pickAutonomous.addObject("Tote stacking - Straight. See other selectors *DO NOT USE*",		new ToteStackStraightAutonomousScript(-1, -1));

        SmartDashboard.putData("Select Autonomous Mode", m_pickAutonomous);
        
        SmartDashboard.putString("Autonomous Status", getAutonomousStatus());

        
        SmartDashboard.putData(m_driveTrain);
        SmartDashboard.putData(m_rightGrabber);
        SmartDashboard.putData(m_elevator);
        
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

        session = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

        new ActuateStrafeSolenoid(true).start();
        new OpenGrabberArms().start();
    	
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
    	
    	RobotConstants.autonomousInitialPosition = (int) m_autonomousStartingPosition.getSelected();
    	RobotConstants.autonomousDirectionOrAmount = (int) m_autonomousAmountOrDirectionToMove.getSelected();
    	
    	m_autonomousCommandGroup = (CommandGroup) m_pickAutonomous.getSelected();

    	
        if(m_autonomousCommandGroup != null) {
        	setAutonomousStatus("Starting script:" + m_autonomousCommandGroup.getName());

            m_autonomousCommandGroup.start();
        } else {
        	setAutonomousStatus("Error: Autonomous command is null");
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
        
        NIVision.IMAQdxStartAcquisition(session);

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
     
        NIVision.IMAQdxGrab(session, frame, 1);
        CameraServer.getInstance().setImage(frame);
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    public void setAutonomousStatus(String status) {
       String backup1 = autonomousStatuses[0];
       String backup2 = autonomousStatuses[1];
       autonomousStatuses[0] = status;
       autonomousStatuses[1] = backup1;
       autonomousStatuses[2] = backup2;
    }
    
    public String getAutonomousStatus() {
        String status = "";
        status = autonomousStatuses[0];
        status = status + "\n" + autonomousStatuses[1];
        status = status + "\n" + autonomousStatuses[2];
        return status;
    }
}
