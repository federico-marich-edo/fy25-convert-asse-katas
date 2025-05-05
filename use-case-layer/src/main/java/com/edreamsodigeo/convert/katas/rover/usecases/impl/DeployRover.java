package com.edreamsodigeo.convert.katas.rover.usecases.impl;

import com.edreamsodigeo.convert.katas.rover.entities.Direction;
import com.edreamsodigeo.convert.katas.rover.entities.Rover;
import com.edreamsodigeo.convert.katas.rover.entities.factory.RoverFactory;
import com.edreamsodigeo.convert.katas.rover.model.response.RoverResponse;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.DeployRoverBoundary;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.RoverRegisterDSGateway;
import com.edreamsodigeo.convert.katas.rover.model.request.DeployRoverRequest;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.RoverRequestDS;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.impl.RoverRequestDSImpl;
import com.edreamsodigeo.convert.katas.rover.usecases.presenter.RoverResponsePresenter;

import java.time.LocalDateTime;

public class DeployRover implements DeployRoverBoundary {

    private final RoverRegisterDSGateway roverRegisterDSGateway;
    private final RoverFactory roverFactory;
    private final RoverResponsePresenter roverResponsePresenter;

    public DeployRover(RoverRegisterDSGateway roverRegisterDSGateway, RoverFactory roverFactory, RoverResponsePresenter roverResponsePresenter) {
        this.roverRegisterDSGateway = roverRegisterDSGateway;
        this.roverFactory = roverFactory;
        this.roverResponsePresenter = roverResponsePresenter;
    }

    @Override
    public RoverResponse deploy(DeployRoverRequest request) {
        if (roverRegisterDSGateway.existsByName(request.getRoverName())) {
            throw new IllegalArgumentException("Rover with name " + request.getRoverName() + " already exists.");
        }
        Rover rover = roverFactory.create(request.getRoverName(), request.getDirection(), request.getHorizontalPosition(), request.getVerticalPosition(), true);

        LocalDateTime now = LocalDateTime.now();
        RoverRequestDS roverDSModel = new RoverRequestDSImpl(
                rover.getName(),
                rover.getDirection().getValue(),
                rover.getHorizontalPosition(),
                rover.getVerticalPosition(),
                now,
                now,
                true
        );

        roverRegisterDSGateway.save(roverDSModel);

        RoverResponse roverResponse = new RoverResponse(rover.getName(),
                formatLocation(rover.getDirection(), rover.getHorizontalPosition(), rover.getVerticalPosition()),
                "Deployed", "", true);
        return roverResponsePresenter.prepareSuccessResponse(roverResponse);
    }

    private String formatLocation(Direction direction, int horizontalPosition, int verticalPosition) {
        return String.format("%s:%d:%d", direction.getValue(), horizontalPosition, verticalPosition);
    }
}

