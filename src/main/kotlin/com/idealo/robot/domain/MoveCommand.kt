package com.idealo.robot.domain

import com.idealo.robot.model.Robot
import com.idealo.robot.service.GridTable

class MoveCommand(private val robot: Robot , private val gridTable: GridTable) : Command {
    override fun execute(): String? {
        RobotTabletop.validateRobotOnTable(robot)
        robot.coordinate = gridTable.nextMoveFor(robot.coordinate!! , robot.facing!!)
        return null
    }
}