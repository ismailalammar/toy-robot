package com.idealo.robot

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
        isRobotOnTable()
        direction = direction?.right()
    }

    fun left(){
        isRobotOnTable()
        direction = direction?.left()
    }

    fun move() {
        isRobotOnTable()
        coordinate = gridTable.nextMoveFor(coordinate!!, direction!!)
    }

    fun report() : String{
        return "${coordinate?.x},${coordinate?.y},$direction"
    }

    private fun isRobotOnTable() {
        if(coordinate == null || direction == null)
            throw IllegalArgumentException("robot is missing")
    }
}
