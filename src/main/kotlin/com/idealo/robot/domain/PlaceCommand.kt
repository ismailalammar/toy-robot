package com.idealo.robot.domain

import com.idealo.robot.exception.RobotException
import com.idealo.robot.model.Coordinate
import com.idealo.robot.model.Robot
import com.idealo.robot.model.RobotPlace

class PlaceCommand( private val robot: Robot, private val robotPlace: RobotPlace) : Command {
    override fun execute() {
        if(!RobotTabletop.isValidPosition(robotPlace.x , robotPlace.y))
            throw RobotException("Invalid robot coordinates: The robot's coordinates should be within the 5x5 tabletop.")
        robot.coordinate = Coordinate(robotPlace.x , robotPlace.y)
        robot.facing = robotPlace.facing
    }
}