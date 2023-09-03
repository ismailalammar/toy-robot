package com.idealo.robot

class Robot {
    companion object {
        const val MAX_X = 4
        const val MAX_Y = 4
    }
    private var coordinate = Coordinate(0 , 0)
    private var direction: Direction? = Direction.NORTH

    fun right(){
        direction = direction?.right()
    }

    fun left(){
        direction = direction?.right()
    }

    fun move() {
        if(direction == Direction.NORTH)
            coordinate.y = (coordinate.y + 1).coerceAtMost(MAX_Y)
        else if(direction == Direction.EAST)
            coordinate.x = (coordinate.x + 1).coerceAtMost(MAX_X)
    }

    fun report() : String{
        return "${coordinate.x},${coordinate.y},$direction"
    }
}
