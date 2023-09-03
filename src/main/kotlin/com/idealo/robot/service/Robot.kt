package com.idealo.robot.service

import com.idealo.robot.domain.Direction
import com.idealo.robot.model.Coordinate
import org.springframework.stereotype.Service

@Service
class Robot(
    private var gridTable: GridTable
) {

    private var coordinate: Coordinate? = null
    private var direction: Direction? = null

    fun place(x: Int , y: Int , direction: Direction) {
        coordinate = Coordinate(x , y)
        this.direction = direction
    }

    fun right(){
        validateRobotOnTable()
        direction = direction?.right()
    }

    fun left(){
        validateRobotOnTable()
        direction = direction?.left()
    }

    fun move() {
        validateRobotOnTable()
        coordinate = gridTable.nextMoveFor(coordinate!!, direction!!)
    }

    fun report() : String{
        validateRobotOnTable()
        return "${coordinate?.x},${coordinate?.y},$direction"
    }

    private fun validateRobotOnTable() {
        requireNotNull(coordinate) { "Robot is missing coordinates" }
        requireNotNull(direction) { "Robot is missing direction" }
    }
}
