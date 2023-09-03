package com.idealo.robot.domain

enum class Direction(
    private val value: String,
    private val left: String,
    private val right: String
) {
    NORTH("NORTH" , "WEST" , "EAST"),
    SOUTH("SOUTH", "EAST" , "WEST"),
    EAST("EAST", "NORTH" , "SOUTH"),
    WEST("WEST" , "SOUTH" , "NORTH");

    fun right(): Direction? {
        return directionMatching(right)
    }

    fun left(): Direction? {
        return directionMatching(left)
    }

    private fun directionMatching(value: String): Direction? {
        for (direction in values()) {
            if (direction.value == value) {
                return direction
            }
        }
        return null
    }
}
