/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="teleop", group="Iterative Opmode")
public class test extends OpMode
{
    public DcMotor wheel;
    public DcMotor wheel2;
    public Servo intake1;
    public Servo intake2;
    public DcMotor leftfront;
    public DcMotor leftback;
    public DcMotor rightfront;
    public DcMotor rightback;
    public DcMotor pulley;
    @Override
    public void init() {

        wheel = hardwareMap.dcMotor.get("wheel");
        wheel2 = hardwareMap.dcMotor.get("wheel2");
        intake2 = hardwareMap.servo.get("intake1");
        intake1 = hardwareMap.servo.get("intake2");
        pulley = hardwareMap.dcMotor.get("pulley");
        leftfront = hardwareMap.dcMotor.get("leftfront");
        leftback = hardwareMap.dcMotor.get("leftback");
        rightback = hardwareMap.dcMotor.get("rightback");
        rightfront = hardwareMap.dcMotor.get("rightfront");
        rightback.setDirection(DcMotorSimple.Direction.REVERSE);
        rightfront.setDirection(DcMotorSimple.Direction.REVERSE);


    }
    @Override
    public void loop() {
        float x;
        float y;
        float z;

        if (Math.abs(gamepad2.left_stick_x) > .1) {
            x = gamepad2.left_stick_x;
        } else {
            x = 0;
        }

        if (Math.abs(gamepad2.left_stick_y) > .1) {
            y = -gamepad2.left_stick_y;
        } else {
            y = 0;
        }

        if (Math.abs(gamepad2.right_stick_x) > .1) {
            z = -gamepad2.right_stick_x;
        } else {
            z = 0;
        }

        if (gamepad1.x) {
            wheel.setPower(1);
            wheel2.setPower(1);
        } else {
            wheel.setPower(0.0);
            wheel2.setPower(0.0);
        }

        if((gamepad1.right_trigger) >0.1) {
            intake1.setPosition(intake1.getPosition() + 0.1);
        } else if ((gamepad1.left_trigger) >0.1) {
            intake1.setPosition(intake1.getPosition() - 0.1);
        } else {
            intake1.setPosition(0.5);
        }
        if((gamepad1.right_trigger) >0.1) {
            intake2.setPosition(intake2.getPosition() + 0.1);
        } else if ((gamepad1.left_trigger) >0.1) {
            intake2.setPosition(intake2.getPosition() - 0.1);
        } else {
            intake2.setPosition(0.5);
        }

        pulley.setPower(gamepad1.right_stick_x/2);

        leftback.setPower((x - y - z)*.75);
        leftfront.setPower((-x - y -z)*.75);
        rightback.setPower((-x - y + z)*.75);
        rightfront.setPower((x - y + z)*.75);


    }

}

