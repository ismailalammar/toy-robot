package com.idealo.robot

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals


class RobotShould {

    @Test
    fun rotate_right(){
        val robot = Robot()
        robot.right()
        assertEquals("0,0,East" , robot.report())
    }
}