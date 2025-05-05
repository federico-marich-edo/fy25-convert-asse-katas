package com.edreamsodigeo.convert.katas.rover.entities.impl;

import com.edreamsodigeo.convert.katas.rover.entities.factory.PlanetGridFactory;

public class EarthPlanedGridFactory implements PlanetGridFactory {

    @Override
    public EarthPlanetGrid create(int width, int height, String name) {
        return new EarthPlanetGrid(width, height, name);
    }

}
