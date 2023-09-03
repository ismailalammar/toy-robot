package com.idealo.robot.domain

import com.idealo.robot.model.Coordinate
import com.idealo.robot.model.Robot

class PlaceCommand( private val robot: Robot,
                    private val x: Int,
                    private val y: Int,
                    private val direction: Direction) : Command {
    override fun execute() {
        if(!RobotTabletop.isValidPosition(x , y))
            throw IllegalStateException("Invalid robot position")
        robot.coordinate = Coordinate(x , y)
        robot.facing = direction
    }

}
