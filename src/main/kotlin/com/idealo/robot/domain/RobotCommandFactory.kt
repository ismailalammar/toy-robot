package com.idealo.robot.domain

import com.idealo.robot.domain.RobotCommandType.*
import com.idealo.robot.model.Robot
import com.idealo.robot.model.RobotPlace
import com.idealo.robot.service.GridTableService
import org.springframework.stereotype.Component

@Component
class RobotCommandFactory(
    private var gridTable: GridTableService,
    ) {
    fun createCommand(command: RobotCommandType, robot: Robot, robotPlace: RobotPlace?): Command {
        return when(command){
            PLACE -> PlaceCommand(robot, robotPlace!!)
            LEFT -> LeftCommand(robot)
            RIGHT -> RightCommand(robot)
            MOVE -> MoveCommand(robot, gridTable)
            REPORT -> ReportCommand(robot)
        }
    }
}