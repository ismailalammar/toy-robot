package com.idealo.robot

import org.springframework.stereotype.Component

@Component
class GridTable {
    companion object {
        const val MAX_X = 4
        const val MAX_Y = 4
    }

    fun nextMoveFor(coordinate: Coordinate , direction: Direction): Coordinate {
        if(direction == Direction.NORTH)
            coordinate.y = (coordinate.y + 1).coerceAtMost(MAX_Y)
        else if(direction == Direction.EAST)
            coordinate.x = (coordinate.x + 1).coerceAtMost(MAX_X)
        else if(direction == Direction.WEST)
            coordinate.x = (coordinate.x - 1).coerceAtLeast(0)

        return coordinate
    }
}