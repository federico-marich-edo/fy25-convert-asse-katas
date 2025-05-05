package com.edreamsodigeo.convert.katas.rover.interfaceadapters.controllers;

import com.edreamsodigeo.convert.katas.rover.model.request.DeployRoverRequest;
import com.edreamsodigeo.convert.katas.rover.model.response.RoverResponse;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.DeployRoverBoundary;

public class DeployRoverController {

    // /mission-control/rovers/xxxx/deploy
    private DeployRoverBoundary deployRouterBoundary;

    //@PostMapping("/user")
    RoverResponse deploy(DeployRoverRequest deployRoverRequest) {
        return deployRouterBoundary.deploy(deployRoverRequest);
    }

}
