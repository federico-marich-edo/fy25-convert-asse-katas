package com.edreamsodigeo.convert.katas.rover.entities.impl;

import com.edreamsodigeo.convert.katas.rover.entities.factory.CoordinateFactory;

public class StandardCoordinateFactory implements CoordinateFactory {
    @Override
    public StandardCoordinate create(int horizontalPosition, int verticalPosition) {
        return new StandardCoordinate(horizontalPosition, verticalPosition);
    }
}
