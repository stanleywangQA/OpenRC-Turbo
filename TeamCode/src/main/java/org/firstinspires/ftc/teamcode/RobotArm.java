package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp()
public class RobotArm extends OpMode{

    Servo clawRot;
    Servo claw;
    //DcMotor roboArm = null;

    public void init() {
        clawRot = hardwareMap.get(Servo.class, "Servo");
        claw = hardwareMap.get(Servo.class, "Servo2");
        //roboArm = hardwareMap.get(DcMotor.class, "Motor");
    }


    public void loop() {
        double MIN_POSITION = 0.0;
        double MAX_POSITION = 1.0;

        //if (claw.getDirection() == Servo.Direction.FORWARD) {
            //double clawPos = claw.getDirection();
        //} else if (claw.getDirection() == Servo.Direction.REVERSE) {

        //}


            clawRot.setPosition(gamepad2.right_stick_y* 2);
            telemetry.addData("action:","rightstick, claw rotation");
            if (gamepad2.right_bumper) {
                clawRot.setPosition(-2);
                telemetry.addData("action:", "right key, reverse rotation");
            }

            claw.setPosition(gamepad2.right_stick_x);

        //roboArm.setPower(gamepad2.left_stick_y);

    }
}
