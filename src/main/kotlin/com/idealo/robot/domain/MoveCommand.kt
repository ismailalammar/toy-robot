package com.idealo.robot.domain

import com.idealo.robot.model.Robot
import com.idealo.robot.service.GridTableService

class MoveCommand(private val robot: Robot , private val gridTable: GridTableService) : Command {
    override fun execute(): String? {
        RobotTabletop.validateRobotOnTable(robot)
        robot.coordinate = gridTable.getNextDirection(robot.coordinate!! , robot.facing!!)
        return null
    }
}