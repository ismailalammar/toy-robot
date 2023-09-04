package com.idealo.robot.service

import com.idealo.robot.domain.Direction
import com.idealo.robot.domain.LeftCommand
import com.idealo.robot.domain.MoveCommand
import com.idealo.robot.domain.PlaceCommand
import com.idealo.robot.domain.RightCommand
import com.idealo.robot.domain.RobotTabletop
import com.idealo.robot.model.Robot
import org.springframework.stereotype.Service

@Service
class RobotService(
    private var gridTable: GridTable
) {

    fun place(robot: Robot, x: Int , y: Int , facing: Direction) {
        val command = PlaceCommand(robot, x, y , facing)
        command.execute()
    }
    fun right(robot: Robot){
        val command = RightCommand(robot)
        command.execute()
    }
    fun left(robot: Robot){
        val command = LeftCommand(robot)
        command.execute()
    }
    fun move(robot: Robot) {
        val command = MoveCommand(robot, gridTable)
        command.execute()
    }
    fun report(robot: Robot) : String{
        RobotTabletop.validateRobotOnTable(robot)
        return "${robot.coordinate?.x},${robot.coordinate?.y},${robot.facing}"
    }
}