package com.idealo.robot.model

import com.idealo.robot.domain.Direction

data class Robot(var coordinate: Coordinate? = null, var facing: Direction? = null)
