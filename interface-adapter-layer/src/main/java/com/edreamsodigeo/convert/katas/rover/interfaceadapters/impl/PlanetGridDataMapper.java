package com.edreamsodigeo.convert.katas.rover.interfaceadapters.impl;

public class PlanetGridDataMapper {
    private String planetName;
    private int width;
    private int height;

    public PlanetGridDataMapper(String planetName, int width, int height) {
        this.planetName = planetName;
        this.width = width;
        this.height = height;
    }

    public String getPlanetName() {
        return planetName;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
