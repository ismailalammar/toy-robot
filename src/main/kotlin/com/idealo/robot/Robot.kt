package com.idealo.robot

class Robot {
    private var coordinate = Coordinate(0 , 0)
    private var direction: Direction? = Direction.NORTH

    fun right(){
        direction = direction?.right()
    }

    fun left(){
        direction = direction?.right()
    }

    fun move() {
        var y = coordinate.y
        if(direction == Direction.NORTH)
            y++
        coordinate = Coordinate(coordinate.x , y)
    }

    fun report() : String{
        return "${coordinate.x},${coordinate.y},$direction"
    }
}
