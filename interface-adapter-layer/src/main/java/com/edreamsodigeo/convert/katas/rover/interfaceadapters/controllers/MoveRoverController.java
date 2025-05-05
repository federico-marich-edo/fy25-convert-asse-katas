package com.edreamsodigeo.convert.katas.rover.interfaceadapters.controllers;

import com.edreamsodigeo.convert.katas.rover.model.request.MoveRoverRequest;
import com.edreamsodigeo.convert.katas.rover.model.response.RoverResponse;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.MoveRoverBoundary;

public class MoveRoverController {

    private MoveRoverBoundary moveRoverBoundary;

    // /mission-control/rovers/xxxx/move
    public RoverResponse move(MoveRoverRequest moveRoverRequest) {
        return moveRoverBoundary.move(moveRoverRequest);
    }



}
