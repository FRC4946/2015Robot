package org.usfirst.frc4946.AlphaDogs2015Robot.util;
import edu.wpi.first.wpilibj.SpeedController;

public class MultiSpeedController implements SpeedController {
    private SpeedController[] speedControllers;
    private double speed;

    public MultiSpeedController(SpeedController... speedControllers) {
        this.speedControllers = speedControllers;
        this.set(0.0);
    }

    @Override
    public double get() {
        return this.speed;
    }

    @Override
    public void set(double speed) {
        this.speed = speed;

        for (SpeedController speedController : this.speedControllers) {
            speedController.set(speed);
        }
    }

    @Override
    public void set(double speed, byte syncGroup) {
        this.set(speed);
    }

    @Override
    public void pidWrite(double output) {
        this.set(output);
    }

    @Override
    public void disable() {
        for (SpeedController speedController : this.speedControllers) {
            speedController.disable();
        }
    }
}