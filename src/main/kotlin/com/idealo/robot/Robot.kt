package com.idealo.robot

class Robot {
    companion object {
        const val MAX_X = 4
        const val MAX_Y = 4
    }
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
        direction = direction?.right()
    }

    fun move() {
        isRobotOnTable()
        if(direction == Direction.NORTH)
            coordinate!!.y = (coordinate!!.y + 1).coerceAtMost(MAX_Y)
        else if(direction == Direction.EAST)
            coordinate!!.x = (coordinate!!.x + 1).coerceAtMost(MAX_X)
    }

    fun report() : String{
        return "${coordinate?.x},${coordinate?.y},$direction"
    }

    private fun isRobotOnTable() {
        if(coordinate == null || direction == null)
            throw IllegalArgumentException("robot is missing")
    }
}
