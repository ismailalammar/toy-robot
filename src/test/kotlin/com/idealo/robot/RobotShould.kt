package com.idealo.robot

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RobotShould {
    @Autowired private lateinit var robot: Robot

    @BeforeEach
    fun before() {
        robot.place(2 , 2 , Direction.NORTH)
    }

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
        robot.move()
        assertEquals("2,3,NORTH" , robot.report())
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4]) // Number of times to move up
    fun stop_moving_up_when_robot_reaches_the_edge(movement: Int){
        repeat(movement) {
            robot.move()
        }
        when (movement % 5) {
            1 -> assertEquals("2,3,NORTH", robot.report())
            2 -> assertEquals("2,4,NORTH", robot.report())
            3 -> assertEquals("2,4,NORTH", robot.report())
            4 -> assertEquals("2,4,NORTH", robot.report())
        }
    }

    @Test
    fun move_right(){
        robot.right()
        robot.move()
        assertEquals("3,2,EAST" , robot.report())
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4]) // Number of times to move up
    fun stop_moving_right_when_robot_reaches_the_edge(movement: Int){
        robot.right()
        repeat(movement) {
            robot.move()
        }
        when (movement % 5) {
            1 -> assertEquals("3,2,EAST", robot.report())
            2 -> assertEquals("4,2,EAST", robot.report())
            3 -> assertEquals("4,2,EAST", robot.report())
            4 -> assertEquals("4,2,EAST", robot.report())
        }
    }

    @Test
    fun move_left() {
        robot.left()
        robot.move()
        assertEquals("1,2,WEST" , robot.report())
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4]) // Number of times to move up
    fun stop_moving_left_when_robot_reaches_the_edge(movement: Int){
        robot.left()
        repeat(movement) {
            robot.move()
        }
        when (movement % 5) {
            1 -> assertEquals("1,2,WEST", robot.report())
            2 -> assertEquals("0,2,WEST", robot.report())
            3 -> assertEquals("0,2,WEST", robot.report())
            4 -> assertEquals("0,2,WEST", robot.report())
        }
    }

    @Test
    fun move_down() {
        robot.left()
        robot.left()
        robot.move()
        assertEquals("2,1,SOUTH" , robot.report())
    }
}