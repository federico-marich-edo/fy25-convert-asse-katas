package com.edreamsodigeo.convert.katas.rover.entities.impl;

import com.edreamsodigeo.convert.katas.rover.entities.Coordinate;
import com.edreamsodigeo.convert.katas.rover.entities.PlanetGrid;
import com.edreamsodigeo.convert.katas.rover.entities.Rover;

import java.util.List;

public class EarthPlanetGrid implements PlanetGrid {

    private int width = 10;
    private int height = 10;
    private String name = "Earth";
    private List<Rover> deployedRovers;

    public EarthPlanetGrid(int width, int height, String name) {
        this.width = width;
        this.height = height;
        this.name = name;
    }

    @Override
    public Integer getWidth() {
        return width;
    }

    @Override
    public Integer getHeight() {
        return height;
    }

    @Override
    public boolean isValid(Coordinate coordinate) {
        if (coordinate == null) {
            return false;
        }
        return coordinate.getHorizontalPosition() >= 0 && coordinate.getHorizontalPosition() < width &&
                coordinate.getVerticalPosition() >= 0 && coordinate.getVerticalPosition() < height;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Rover> getDeployedRovers() {
        return deployedRovers;
    }

    @Override
    public String toString() {
        return "PlanetGrid{" +
                "name='" + getName() + '\'' +
                "width=" + width +
                ", height=" + height +
                ", deployedRovers=" + deployedRovers +
                '}';
    }


}
