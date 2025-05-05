package com.edreamsodigeo.convert.katas.rover.entities.impl;

import com.edreamsodigeo.convert.katas.rover.entities.Coordinate;
import com.edreamsodigeo.convert.katas.rover.entities.Direction;
import com.edreamsodigeo.convert.katas.rover.entities.factory.RoverFactory;

public class MarsRoverFactory implements RoverFactory {
    @Override
    public MarsRover create(String name, String direction, int horizontalPosition, int verticalPosition, boolean active) {
        Direction roverDirection = Direction.valueOf(direction.toUpperCase());
        Coordinate coordinate = new StandardCoordinate(horizontalPosition, verticalPosition);
        return new MarsRover(name, roverDirection, coordinate, active);
    }
}
