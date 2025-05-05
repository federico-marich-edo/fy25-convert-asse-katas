package com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.impl;

import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverInterfaceResponse;

public class CommonRoverInterfaceResponse implements RoverInterfaceResponse {

    private String roverName;
    private String direction;
    private int horizontalPosition;
    private int verticalPosition;
    private String status;

    @Override
    public String getRoverName() {
        return roverName;
    }

    @Override
    public String getDirection() {
        return direction;
    }

    @Override
    public int getHorizontalPosition() {
        return horizontalPosition;
    }

    @Override
    public int getVerticalPosition() {
        return verticalPosition;
    }

    @Override
    public String getStatus() {
        return status;
    }
}
