package com.idealo.robot.domain

import com.idealo.robot.model.Robot

class RightCommand(private val robot: Robot) : Command {
    override fun execute(): String? {
        RobotTabletop.validateRobotOnTable(robot)
        robot.facing = robot.facing?.right()
        return null
    }
}