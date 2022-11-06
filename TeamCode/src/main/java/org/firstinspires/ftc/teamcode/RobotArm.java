package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp()
public class RobotArm extends OpMode{

    Servo clawRot;
    Servo claw;
    DcMotor roboArm = null;
    DcMotor roboArmUp = null;

    DcMotor leftMotor = null;
    DcMotor rightMotor = null;

    DcMotor frontLMotor = null;
    DcMotor frontRMotor = null;

    public void init() {
        clawRot = hardwareMap.get(Servo.class, "Servo");
        claw = hardwareMap.get(Servo.class, "Servo2");
        roboArm = hardwareMap.get(DcMotorEx.class, "Motor");
        roboArmUp = hardwareMap.get(DcMotorEx.class, "Motor2");

        leftMotor = hardwareMap.get(DcMotor.class, "backL");
        rightMotor = hardwareMap.get(DcMotor.class, "backR");

        frontLMotor = hardwareMap.get(DcMotor.class, "frontL");
        frontRMotor = hardwareMap.get(DcMotor.class, "frontR");
    }


    public void loop() {
        double MIN_POSITION = 0.0;
        double MAX_POSITION = 1.0;

        //if (claw.getDirection() == Servo.Direction.FORWARD) {
            //double clawPos = claw.getDirection();
        //} else if (claw.getDirection() == Servo.Direction.REVERSE) {

        //}

            double RotPos = 1;
            int speedMod = 2;

            // Claw rotation
            if (gamepad2.dpad_up) {
                clawRot.setPosition(RotPos);
                telemetry.addData("action:", "rightstick, claw rotation");
            } else if (gamepad2.dpad_down) {
                clawRot.setPosition(-RotPos);
                telemetry.addData("action:", "reverse rotation");
            } else {
                clawRot.setPosition(0);
            }


            // Clwa open / close
            if (gamepad2.left_trigger != 0) {
                claw.setPosition(1);
                telemetry.addData("action:", "claw open");
            } else if (gamepad1.left_bumper) {
                claw.setPosition(-1);
                telemetry.addData("action:", "claw close");
            }

            //Robot arm
            if (gamepad2.right_bumper) {
                speedMod = 1;
            }

            roboArm.setPower(gamepad2.left_stick_y/speedMod);
            roboArm.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

            roboArmUp.setPower(gamepad2.right_stick_y/speedMod);
            roboArmUp.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);


            // Drive --------------------------------------------------------------------
            // assign speed modifier
            int speedModB = 2;


            if (gamepad1.right_bumper) {
                speedModB = 1;
            }
            if (gamepad1.left_bumper) {
                speedModB = 3;
            }

            // Mecanum Drive
            double r = Math.hypot(gamepad1.left_stick_x, gamepad1.right_stick_x);
            double robotAngle = Math.atan2(- 1 * gamepad1.right_stick_x, gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = gamepad1.left_stick_y;
            final double v1 = r * Math.cos(robotAngle) + rightX;
            final double v2 = r * Math.sin(robotAngle) - rightX;
            final double v3 = r * Math.sin(robotAngle) + rightX;
            final double v4 = r * Math.cos(robotAngle) - rightX;

            frontLMotor.setPower(v1 / speedModB);
            frontRMotor.setPower(v2 / speedModB);
            leftMotor.setPower(v3 / speedModB);
            rightMotor.setPower(v4 / speedModB);
    }
}
