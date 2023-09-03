package com.idealo.robot

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
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

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4]) // Number of times to rotate left
    fun rotate_left(rotations: Int){
        var direction = Direction.NORTH
        repeat(rotations) {
            direction = direction.left()!!
        }
        when (rotations % 4) {
            0 -> assertEquals(Direction.NORTH, direction)
            1 -> assertEquals(Direction.WEST, direction)
            2 -> assertEquals(Direction.SOUTH, direction)
            3 -> assertEquals(Direction.EAST, direction)
        }
    }

    @Test
    fun move_up(){
        val robot = Robot()
        robot.move()
        assertEquals("0,1,NORTH" , robot.report())
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4 , 5 , 6]) // Number of times to move up
    fun stop_moving_up_when_robot_reaches_the_edge(movement: Int){
        val robot = Robot()
        repeat(movement) {
            robot.move()
        }
        when (movement % 7) {
            1 -> assertEquals("0,1,NORTH", robot.report())
            2 -> assertEquals("0,2,NORTH", robot.report())
            3 -> assertEquals("0,3,NORTH", robot.report())
            4 -> assertEquals("0,4,NORTH", robot.report())
            5 -> assertEquals("0,4,NORTH", robot.report())
            6 -> assertEquals("0,4,NORTH", robot.report())
        }
    }
}