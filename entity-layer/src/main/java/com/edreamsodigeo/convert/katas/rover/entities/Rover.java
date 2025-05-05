package com.edreamsodigeo.convert.katas.rover.entities;

public interface Rover {

    void changeDirection(Direction direction);

    void changePosition(Coordinate coordinate);

    Coordinate getPosition();

    Integer getHorizontalPosition();

    Integer getVerticalPosition();

    Direction getDirection();

    String getName();

    String getStatus();

    boolean isActive();
}
