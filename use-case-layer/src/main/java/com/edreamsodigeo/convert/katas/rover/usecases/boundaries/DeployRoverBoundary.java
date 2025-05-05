package com.edreamsodigeo.convert.katas.rover.usecases.boundaries;

import com.edreamsodigeo.convert.katas.rover.model.request.DeployRoverRequest;
import com.edreamsodigeo.convert.katas.rover.model.response.RoverResponse;

public interface DeployRoverBoundary {
    RoverResponse deploy(DeployRoverRequest request);
}
