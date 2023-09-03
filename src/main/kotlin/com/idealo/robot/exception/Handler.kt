package com.idealo.robot.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class Handler {

    @ExceptionHandler(RobotException::class)
    fun handleRobotException(e: RobotException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(e.message)
    }

    @ExceptionHandler(RuntimeException::class)
    fun handleGeneralException(e: RuntimeException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(e.message)
    }
}