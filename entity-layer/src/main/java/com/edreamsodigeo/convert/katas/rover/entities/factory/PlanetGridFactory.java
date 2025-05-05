package com.edreamsodigeo.convert.katas.rover.entities.factory;

import com.edreamsodigeo.convert.katas.rover.entities.PlanetGrid;

public interface PlanetGridFactory {
    PlanetGrid create(int width, int height, String name);
}
