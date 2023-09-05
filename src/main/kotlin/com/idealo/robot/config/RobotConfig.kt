package com.idealo.robot.config

import com.idealo.robot.model.Robot
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RobotConfig {
    @Bean
    fun robotData(): Robot {
        return Robot()
    }
}