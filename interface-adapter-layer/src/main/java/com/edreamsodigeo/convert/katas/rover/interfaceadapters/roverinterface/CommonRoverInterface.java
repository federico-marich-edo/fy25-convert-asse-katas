package com.edreamsodigeo.convert.katas.rover.interfaceadapters.roverinterface;

import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.impl.CommonRoverInterfaceResponse;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.RoverInterfaceGateway;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverInterfaceRequest;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverInterfaceResponse;

public class CommonRoverInterface implements RoverInterfaceGateway {

    @Override
    public RoverInterfaceResponse command(RoverInterfaceRequest roverInterfaceRequest) {
        //Dummy implementation
        RoverInterfaceResponse response = new CommonRoverInterfaceResponse();
        return response;
    }

}
