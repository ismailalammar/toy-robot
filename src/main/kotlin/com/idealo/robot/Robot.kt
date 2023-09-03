package com.idealo.robot

class Robot {
    private var x: Int = 0
    private var y: Int = 0
    private var direction: Direction? = Direction.NORTH

    fun right(){
        direction = direction?.right()
    }

    fun left(){
        direction = direction?.right()
    }

    fun move() {

    }

    fun report() : String{
        return "$x,$y,$direction"
    }
}
