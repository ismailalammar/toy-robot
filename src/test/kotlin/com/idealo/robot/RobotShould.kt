package com.idealo.robot

import com.idealo.robot.domain.Direction
import com.idealo.robot.model.Robot
import com.idealo.robot.service.RobotService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RobotShould {
    @Autowired private lateinit var robotService: RobotService
    private lateinit var robot: Robot

    @BeforeEach
    fun before() {
        robot = Robot()
        robotService.place(
            robot,
            2,
            2,
            Direction.NORTH
        )
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4]) // Number of times to rotate right
    fun `rotate right multiple times`(rotations: Int){
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
    fun `rotate left multiple times`(rotations: Int){
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
    fun `move up when the direction is north`(){
        robotService.move(robot)
        assertEquals("2,3,NORTH" , robotService.report(robot))
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4]) // Number of times to move up
    fun `stop moving up when robot reaches the edge`(movement: Int){
        repeat(movement) {
            robotService.move(robot)
        }
        when (movement % 5) {
            1 -> assertEquals("2,3,NORTH", robotService.report(robot))
            2 -> assertEquals("2,4,NORTH", robotService.report(robot))
            3 -> assertEquals("2,4,NORTH", robotService.report(robot))
            4 -> assertEquals("2,4,NORTH", robotService.report(robot))
        }
    }

    @Test
    fun `move right when direction is east`(){
        robotService.right(robot)
        robotService.move(robot)
        assertEquals("3,2,EAST" , robotService.report(robot))
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4]) // Number of times to move right
    fun `stop moving right when robot reaches the edge`(movement: Int){
        robotService.right(robot)
        repeat(movement) {
            robotService.move(robot)
        }
        when (movement % 5) {
            1 -> assertEquals("3,2,EAST", robotService.report(robot))
            2 -> assertEquals("4,2,EAST", robotService.report(robot))
            3 -> assertEquals("4,2,EAST", robotService.report(robot))
            4 -> assertEquals("4,2,EAST", robotService.report(robot))
        }
    }

    @Test
    fun `move left when direction is west`() {
        robotService.left(robot)
        robotService.move(robot)
        assertEquals("1,2,WEST" , robotService.report(robot))
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4]) // Number of times try to move up
    fun `stop moving left when robot reaches the edge`(movement: Int){
        robotService.left(robot)
        repeat(movement) {
            robotService.move(robot)
        }
        when (movement % 5) {
            1 -> assertEquals("1,2,WEST", robotService.report(robot))
            2 -> assertEquals("0,2,WEST", robotService.report(robot))
            3 -> assertEquals("0,2,WEST", robotService.report(robot))
            4 -> assertEquals("0,2,WEST", robotService.report(robot))
        }
    }

    @Test
    fun `move down when direction is south`() {
        robotService.left(robot)
        robotService.left(robot)
        robotService.move(robot)
        assertEquals("2,1,SOUTH" , robotService.report(robot))
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4]) // Number of times try to move down
    fun `stop moving down when robot reaches the edge`(movement: Int){
        robotService.left(robot)
        robotService.left(robot)
        repeat(movement) {
            robotService.move(robot)
        }
        when (movement % 5) {
            1 -> assertEquals("2,1,SOUTH", robotService.report(robot))
            2 -> assertEquals("2,0,SOUTH", robotService.report(robot))
            3 -> assertEquals("2,0,SOUTH", robotService.report(robot))
            4 -> assertEquals("2,0,SOUTH", robotService.report(robot))
        }
    }
}