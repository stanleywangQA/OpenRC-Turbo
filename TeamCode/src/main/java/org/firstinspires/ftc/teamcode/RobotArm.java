package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp()
public class RobotArm extends OpMode{

    Servo claw = null;
    DcMotor roboArm = null;

    public void init() {
        claw = hardwareMap.get(Servo.class, "Servo");
        roboArm = hardwareMap.get(DcMotor.class, "Motor");
    }


    public void loop() {
        double MIN_POSITION = 0.0;
        double MAX_POSITION = 1.0;

        //if (claw.getDirection() == Servo.Direction.FORWARD) {
            //double clawPos = claw.getDirection();
        //} else if (claw.getDirection() == Servo.Direction.REVERSE) {

        //}

        if (gamepad2.right_bumper) {
             claw.setDirection(Servo.Direction.FORWARD);
        }
        if (gamepad2.left_bumper) {
            claw.setDirection(Servo.Direction.REVERSE);
        }
        roboArm.setPower(gamepad2.left_stick_y);

    }
}
