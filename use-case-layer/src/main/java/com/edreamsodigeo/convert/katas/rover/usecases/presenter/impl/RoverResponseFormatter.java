package com.edreamsodigeo.convert.katas.rover.usecases.presenter.impl;

import com.edreamsodigeo.convert.katas.rover.model.response.RoverResponse;
import com.edreamsodigeo.convert.katas.rover.usecases.exception.RoverResponseException;
import com.edreamsodigeo.convert.katas.rover.usecases.presenter.RoverResponsePresenter;

public class RoverResponseFormatter implements RoverResponsePresenter {

    @Override
    public RoverResponse prepareSuccessResponse(RoverResponse response) {
        response.setLastCommand("Command: " + response.getLastCommand());
        response.setLocation("Location: " + response.getLocation());
        return response;
    }

    @Override
    public RoverResponse prepareErrorResponse(String errorMessage) {
        throw new RoverResponseException(errorMessage);
    }
}
