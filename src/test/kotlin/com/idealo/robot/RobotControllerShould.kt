package com.idealo.robot

import com.idealo.robot.domain.Direction
import org.hamcrest.Matchers.anyOf
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.hamcrest.Matchers.`is`
import org.springframework.test.annotation.DirtiesContext


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Integration Test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class RobotControllerShould {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    @DisplayName("""
        PLACE 0,0,NORTH
        MOVE
        REPORT
        Output: 0,1,NORTH        
    """)
    fun shouldMoveUpAndFaceNorth() {
        // init robot
        placeRobot(0 , 0 , Direction.NORTH)
        // move robot
        moveRobot()
        // get report
        val report = getRobotReport()
        assertEquals("0,1,NORTH" , report)
    }

    @Test
    @DisplayName("""
        PLACE 0,0,NORTH
        LEFT
        REPORT
        Output: 0,0,WEST       
    """)
    fun shouldFaceWest() {
        // init robot
        placeRobot(0 , 0 , Direction.NORTH)
        // move robot
        rotateRobotToLeft()
        // get report
        val report = getRobotReport()
        assertEquals("0,0,WEST" , report)
    }

    @Test
    @DisplayName("""
        PLACE 1,2,EAST
        MOVE
        MOVE
        LEFT
        MOVE
        REPORT
        Output: 3,3,NORTH  
    """)
    fun shouldMoveUpByThreeAndRightByThreeAndFaceNorth() {
        // init robot
        placeRobot(1 , 2 , Direction.EAST)
        // move robot
        moveRobot()
        moveRobot()
        rotateRobotToLeft()
        moveRobot()
        // get report
        val report = getRobotReport()
        assertEquals("3,3,NORTH" , report)
    }

    @Test
    fun `should return Robot is missing when try to move robot without placing it initially`() {
        moveRobot()
        val report = getRobotReport()
        assertEquals("Robot is missing" , report)
    }

    @Test
    fun `should throw error when trying to place robot outside the valid 5x5 range`() {
        val result = placeRobot(6 , 6, Direction.NORTH)
        assertEquals("Invalid robot coordinates: The robot's coordinates should be within the 5x5 tabletop.", result)
    }

    private fun placeRobot(x: Int ,y: Int, facing: Direction): String {
        val result= mockMvc.perform(MockMvcRequestBuilders.post("/robot/place")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {
                    "x": $x,
                    "y": $y,
                    "facing": "$facing"
                }
            """.trimIndent())
        )
            .andExpect(status().`is`(anyOf(`is`(200), `is`(400))))
            .andReturn()
        return result.response.contentAsString
    }

    private fun moveRobot() {
        mockMvc.perform(MockMvcRequestBuilders.post("/robot/move"))
            .andExpect(status().`is`(anyOf(`is`(200), `is`(400))))
    }

    private fun rotateRobotToLeft() {
        mockMvc.perform(MockMvcRequestBuilders.post("/robot/left"))
            .andExpect(status().`is`(anyOf(`is`(200), `is`(400))))
    }

    private fun rotateRobotToRight() {
        mockMvc.perform(MockMvcRequestBuilders.post("/robot/right"))
            .andExpect(status().`is`(anyOf(`is`(200), `is`(400))))
    }

    private fun getRobotReport(): String {
        val result = mockMvc.perform(MockMvcRequestBuilders.post("/robot/report"))
            .andExpect(status().`is`(anyOf(`is`(200), `is`(400))))
            .andReturn()
         return result.response.contentAsString
    }
}