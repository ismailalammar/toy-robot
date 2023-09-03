package com.idealo.robot

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource


class RobotShould {
    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4]) // Number of times to rotate right
    fun rotate_right(rotations: Int){
        var direction = Direction.NORTH
        repeat(rotations) {
            direction = direction.right()!!
        }
        when (rotations % 4) {
            0 -> assertEquals(Direction.NORTH, direction)
            1 -> assertEquals(Direction.EAST, direction)
            2 -> assertEquals(Direction.SOUTH, direction)
            3 -> assertEquals(Direction.WEST, direction)
        }
    }
}