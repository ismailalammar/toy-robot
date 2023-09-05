package com.idealo.robot.controller

import com.idealo.robot.domain.RobotCommandType
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
    @PostMapping("/place")
    fun place(@RequestBody robotPlace: RobotPlace) {
        robotService.executeCommand(RobotCommandType.PLACE, robotPlace)
    }
    @PostMapping("/left")
    fun left() {
        robotService.executeCommand(RobotCommandType.LEFT)
    }
    @PostMapping("/right")
    fun right() {
        robotService.executeCommand(RobotCommandType.RIGHT)
    }
    @PostMapping("/move")
    fun move() {
        robotService.executeCommand(RobotCommandType.MOVE)
    }
    @PostMapping("/report")
    fun report() : String? {
        return robotService.executeCommand(RobotCommandType.REPORT)
    }
}