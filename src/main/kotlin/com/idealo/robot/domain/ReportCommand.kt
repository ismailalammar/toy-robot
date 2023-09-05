package com.idealo.robot.domain

import com.idealo.robot.model.Robot

class ReportCommand(private val robot: Robot) : Command {

    override fun execute(): String {
        RobotTabletop.validateRobotOnTable(robot)
        return "${robot.coordinate?.x},${robot.coordinate?.y},${robot.facing}"
    }
}