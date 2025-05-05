package com.edreamsodigeo.convert.katas.rover.usecases.boundaries;

import com.edreamsodigeo.convert.katas.rover.model.request.MoveRoverRequest;
import com.edreamsodigeo.convert.katas.rover.model.response.RoverResponse;

public interface MoveRoverBoundary {
    RoverResponse move(MoveRoverRequest moveRoverRequest);
}
