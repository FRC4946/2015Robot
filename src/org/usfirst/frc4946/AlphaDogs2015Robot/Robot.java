package org.usfirst.frc4946.AlphaDogs2015Robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.*;

import org.usfirst.frc4946.AlphaDogs2015Robot.commands.*;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.autonomous.*;
import org.usfirst.frc4946.AlphaDogs2015Robot.subsystems.*;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;

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
    private String m_autonomousStatus = "";

    public static OI m_oi;
    public static DriveTrain m_driveTrain;
    public static Grabber m_grabber;
    public static AirCompressor m_airCompressor;
    public static Elevator m_elevator;
    
    Preferences m_prefs;
    double m_proportional = 0.0;
    double m_integral = 0.0;
    double m_derivative = 0.0;

   //public static Feeder m_feeder;
    
    private CameraServer m_camServer;
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
        m_driveTrain = new DriveTrain();
        m_grabber = new Grabber();
        m_airCompressor = new AirCompressor();
        m_elevator = new Elevator(m_proportional, m_integral, m_derivative);
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
        m_pickAutonomous.addDefault("Tote stacking. See other selectors",								new ToteStackAutonomousScript(-1, -1));
        m_pickAutonomous.addObject("Pickup the recycling container",									new RecyclingContainerAutonomousScript());
        m_pickAutonomous.addObject("Recycling container + tote (Robot parallel to driver's wall)",		new RecyclingContainerPlusToteAutonomousScript(false));
        m_pickAutonomous.addObject("Recycling container + tote (Robot perpendicular to driver's wall)",	new RecyclingContainerPlusToteAutonomousScript(true));
        SmartDashboard.putData("Select Autonomous Mode", m_pickAutonomous);
        
        SmartDashboard.putString("Autonomous Status", getAutonomousStatus());

        
        SmartDashboard.putData(m_driveTrain);
        SmartDashboard.putData(m_grabber);
        SmartDashboard.putData(m_airCompressor);
        SmartDashboard.putData(m_elevator);
        
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

        // the camera name (ex "cam0") can be found through the roborio web interface
        session = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);
        
        //m_camServer = CameraServer.getInstance();
        //m_camServer.setQuality(25);
        //the camera name (ex "cam0") can be found through the roborio web interface
        //m_camServer.startAutomaticCapture("cam0");
        
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
    	
    	RobotConstants.autonomousInitialPosition = (int) m_autonomousStartingPosition.getSelected();
    	RobotConstants.autonomousDirectionOrAmount = (int) m_autonomousAmountOrDirectionToMove.getSelected();
    	//boolean toteIsLoaded = (boolean) m_autonomousToteIsPreLoaded.getSelected();
    	
    	//m_autonomousCommandGroup = new ToteStackAutonomousScript(pos, amountOrDirection);
    	//m_autonomousCommandGroup = new TestAuto();    	
    	m_autonomousCommandGroup = (CommandGroup) m_pickAutonomous.getSelected();

    	
        if(m_autonomousCommandGroup != null) {
            m_autonomousCommandGroup.start();
        } else {
        	SmartDashboard.putString("Autonomous Status", "Autonomous command is null");
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
    
    public String getAutonomousStatus(){
    	return m_autonomousStatus;
    }
    
    public void setAutonomousStatus(String status){
    	m_autonomousStatus = status;
    }
}
