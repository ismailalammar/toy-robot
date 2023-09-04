# Toy Robot Coding Puzzle

This application is a simulation of a toy robot moving on a square tabletop with dimensions of 5 x 5 units. The robot is free to move around the surface but must avoid falling off. It can take commands in the form of a REST API and respond accordingly.

## Per-requisite:
* JDK 17
* Spring boot 3
* Gradle 8.2.1

## Problem Description

The goal of this application is to create a Spring Boot application that simulates the movement of a toy robot on a 5x5 unit table. The robot can respond to the following commands:

- `PLACE X,Y,F`: Place the robot on the table at coordinates X,Y and facing (NORTH, SOUTH, EAST, or WEST).
- `MOVE`: Move the robot one unit forward in the current direction.
- `LEFT`: Rotate the robot 90 degrees to the left without changing its position.
- `RIGHT`: Rotate the robot 90 degrees to the right without changing its position.
- `REPORT`: Display the current position (X,Y) and facing direction (F) of the robot.

## Application Details

- The application is implemented as a Spring Boot application.
- Input commands are provided via a REST API.
- The robot will ignore MOVE, LEFT, RIGHT, and REPORT commands if it is not initially placed on the table.
- The robot will not fall off the table during movement; commands that would result in falling off are ignored.
- The initial placement of the robot must also ensure it stays on the table.
- The application uses the Command Design Pattern to handle and execute the commands.

## REST API Endpoints

### `POST /robot/place`

This endpoint allows you to place the robot on the table. The request body should be in JSON format with the following structure:

```json
{
  "x": 1,
  "y": 2,
  "facing": "NORTH"
}
```

### `POST /robot/move`
This endpoint allows the robot to move one unit forward in the current direction.

### `POST /robot/left`
This endpoint rotates the robot 90 degrees to the left without changing its position.

### `POST /robot/right`
This endpoint rotates the robot 90 degrees to the right without changing its position.

### `POST /robot/report`
This endpoint returns the current position and facing direction of the robot. For example: (0,1,NORTH)
In this example, the robot is located at coordinates (0, 1) and is facing to the NORTH direction.

## Usage

1. Start the Spring Boot application.
2. Use the provided REST API endpoints to interact with the robot.
3. Place the robot on the table using the `POST /robot/place` endpoint.
4. Send commands to the robot using the appropriate endpoints (`move`, `left`, `right`, `report`).
5. Please ensure that you follow the constraints mentioned in the problem description while using the application.

## Setup & Run Application
* Start application:
```
./gradlew bootRun
```
* Run Unit Tests:
```
./gradlew test
```

## Implementation Details
- This application uses the Command Design Pattern to encapsulate each command (PLACE, MOVE, LEFT, RIGHT, REPORT) as an object.
- These command objects are executed based on user input, and the robot's state is updated accordingly.

**Note:** This application does not provide graphical output for the movement of the toy robot, as it focuses solely on the simulation and REST API interaction.
