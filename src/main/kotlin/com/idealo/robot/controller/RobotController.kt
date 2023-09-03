package com.idealo.robot.controller

import com.idealo.robot.model.Robot
import com.idealo.robot.model.RobotPlace
import com.idealo.robot.service.RobotService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/robot")
class RobotController(
    private var robotService: RobotService
) {
    private val robot = Robot()

    @PostMapping("/place")
    fun place(@RequestBody robotPlace: RobotPlace) {
        robotService.place(robot, robotPlace.x, robotPlace.y, robotPlace.facing)
    }

    @PostMapping("/left")
    fun left() {
        robotService.left(robot)
    }

    @PostMapping("/right")
    fun right() {
        robotService.right(robot)
    }


    @PostMapping("/move")
    fun move() {
        robotService.move(robot)
    }


    @PostMapping("/report")
    fun report() : String {
        return robotService.report(robot)
    }
}