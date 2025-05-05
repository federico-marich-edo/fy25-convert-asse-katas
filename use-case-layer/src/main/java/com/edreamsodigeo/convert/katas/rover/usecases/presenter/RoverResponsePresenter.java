package com.edreamsodigeo.convert.katas.rover.usecases.presenter;

import com.edreamsodigeo.convert.katas.rover.model.response.RoverResponse;

public interface RoverResponsePresenter {

    RoverResponse prepareSuccessResponse(RoverResponse roverResponse);

    RoverResponse prepareErrorResponse(String errorMessage);

}
