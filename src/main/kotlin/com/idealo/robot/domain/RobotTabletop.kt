package com.idealo.robot.domain

import com.idealo.robot.model.Robot

object RobotTabletop {
     private const val MAX_X = 4
     private const val MAX_Y = 4

    fun validateRobotOnTable(robot: Robot) {
        requireNotNull(robot.coordinate) { "Robot is missing" }
        requireNotNull(robot.facing) { "Robot is missing direction" }
    }

    fun isValidPosition(x: Int, y: Int): Boolean {
        return x in 0..MAX_X && y in 0..MAX_Y
    }
}