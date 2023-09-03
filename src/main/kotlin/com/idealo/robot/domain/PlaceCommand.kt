package com.idealo.robot.domain

import com.idealo.robot.exception.RobotException
import com.idealo.robot.model.Coordinate
import com.idealo.robot.model.Robot

class PlaceCommand( private val robot: Robot,
                    private val x: Int,
                    private val y: Int,
                    private val direction: Direction) : Command {
    override fun execute() {
        if(!RobotTabletop.isValidPosition(x , y))
            throw RobotException("Invalid robot coordinates: The robot's coordinates should be within the 5x5 tabletop.")
        robot.coordinate = Coordinate(x , y)
        robot.facing = direction
    }

}
