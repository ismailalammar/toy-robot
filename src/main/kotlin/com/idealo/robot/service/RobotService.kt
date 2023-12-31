package com.idealo.robot.service

import com.idealo.robot.domain.RobotCommandFactory
import com.idealo.robot.domain.RobotCommandType
import com.idealo.robot.domain.RobotTabletop
import com.idealo.robot.model.Robot
import com.idealo.robot.model.RobotPlace
import org.springframework.stereotype.Service

@Service
class RobotService(
    private var robotCommandFactory: RobotCommandFactory,
    private var robot: Robot
    ) {
    fun executeCommand(commandType: RobotCommandType, robotPlace: RobotPlace? = null): String? {
        val command = robotCommandFactory.createCommand(commandType, robot, robotPlace)
        return command.execute()
    }
}