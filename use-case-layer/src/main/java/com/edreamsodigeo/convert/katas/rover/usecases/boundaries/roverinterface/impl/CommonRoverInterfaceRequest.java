package com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.impl;

import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverInterfaceRequest;

public class CommonRoverInterfaceRequest implements RoverInterfaceRequest {

    private String roverName;
    private String command;

    public CommonRoverInterfaceRequest(String roverName, String command) {
        this.roverName = roverName;
        this.command = command;
    }

    @Override
    public String getRoverName() {
        return roverName;
    }

    @Override
    public String getCommand() {
        return command;
    }
}
