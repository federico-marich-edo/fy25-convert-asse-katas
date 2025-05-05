package com.edreamsodigeo.convert.katas.rover.entities;

import java.util.List;

public interface PlanetGrid {

    Integer getWidth();

    Integer getHeight();

    boolean isValid(Coordinate coordinate);

    String getName();

    List<Rover> getDeployedRovers();

}
