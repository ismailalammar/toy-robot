package com.idealo.robot

import com.idealo.robot.domain.Direction
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.junit.jupiter.api.Assertions.assertEquals


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Integration Test")
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

    private fun placeRobot(x: Int ,y: Int, facing: Direction) {
        mockMvc.perform(MockMvcRequestBuilders.post("/robot/place")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {
                    "x": $x,
                    "y": $y,
                    "facing": "$facing"
                }
            """.trimIndent())
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    private fun moveRobot() {
        mockMvc.perform(MockMvcRequestBuilders.post("/robot/move"))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    private fun rotateRobotToLeft() {
        mockMvc.perform(MockMvcRequestBuilders.post("/robot/left"))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    private fun rotateRobotToRight() {
        mockMvc.perform(MockMvcRequestBuilders.post("/robot/right"))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    private fun getRobotReport(): String {
        val result = mockMvc.perform(MockMvcRequestBuilders.post("/robot/report"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
            .andReturn()

         return result.response.contentAsString
    }
}