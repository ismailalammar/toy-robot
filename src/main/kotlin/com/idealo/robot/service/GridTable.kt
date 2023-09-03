package com.idealo.robot.service

import com.idealo.robot.domain.Direction
import com.idealo.robot.model.Coordinate
import org.springframework.stereotype.Service

@Service
class GridTable {
    companion object {
        const val MAX_X = 4
        const val MAX_Y = 4
    }

    fun nextMoveFor(coordinate: Coordinate, direction: Direction): Coordinate {
        return when (direction) {
            Direction.NORTH -> coordinate.copy(y = (coordinate.y + 1).coerceAtMost(MAX_Y))
            Direction.EAST -> coordinate.copy(x = (coordinate.x + 1).coerceAtMost(MAX_X))
            Direction.WEST -> coordinate.copy(x = (coordinate.x - 1).coerceAtLeast(0))
            Direction.SOUTH -> coordinate.copy(y = (coordinate.y - 1).coerceAtLeast(0))
        }
    }
}