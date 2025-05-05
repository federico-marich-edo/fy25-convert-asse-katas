package com.edreamsodigeo.convert.katas.rover.usecases.impl;

import com.edreamsodigeo.convert.katas.rover.entities.Direction;
import com.edreamsodigeo.convert.katas.rover.entities.Rover;
import com.edreamsodigeo.convert.katas.rover.entities.factory.RoverFactory;
import com.edreamsodigeo.convert.katas.rover.model.request.MoveRoverRequest;
import com.edreamsodigeo.convert.katas.rover.model.response.RoverResponse;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.MoveRoverBoundary;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.RoverInterfaceGateway;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.RoverRegisterDSGateway;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.RoverStatusDSGateway;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.RoverRequestDS;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.RoverResponseDS;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.impl.RoverRequestDSImpl;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverInterfaceRequest;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverInterfaceResponse;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.impl.CommonRoverInterfaceRequest;
import com.edreamsodigeo.convert.katas.rover.usecases.presenter.RoverResponsePresenter;

import java.time.LocalDateTime;

public class MoveRover implements MoveRoverBoundary {

    private final RoverStatusDSGateway roverStatusDSGateway;
    private final RoverRegisterDSGateway roverRegisterDSGateway;
    private final RoverInterfaceGateway roverInterfaceGateway;
    private final RoverFactory roverFactory;
    private final RoverResponsePresenter roverResponsePresenter;

    public MoveRover(RoverStatusDSGateway roverStatusDSGateway,
                     RoverInterfaceGateway roverInterfaceGateway,
                     RoverRegisterDSGateway roverRegisterDSGateway,
                     RoverFactory roverFactory,
                     RoverResponsePresenter roverResponsePresenter) {
        this.roverInterfaceGateway = roverInterfaceGateway;
        this.roverStatusDSGateway = roverStatusDSGateway;
        this.roverRegisterDSGateway = roverRegisterDSGateway;
        this.roverFactory = roverFactory;
        this.roverResponsePresenter = roverResponsePresenter;
    }

    @Override
    public RoverResponse move(MoveRoverRequest moveRoverRequest) {

        if (!roverRegisterDSGateway.existsByName(moveRoverRequest.getRoverName())) {
            throw new IllegalArgumentException("Rover " + moveRoverRequest.getRoverName() + " is not registered.");
        }

        RoverResponseDS roverResponseDS = roverRegisterDSGateway.findByName(moveRoverRequest.getRoverName());

        Rover rover = roverFactory.create(roverResponseDS.getName(), roverResponseDS.getDirection(), roverResponseDS.getHorizontalPosition(), roverResponseDS.getVerticalPosition(), roverResponseDS.isActive());

        if (!rover.isActive()) {
            throw new IllegalArgumentException("Rover " + rover.getName() + " is not active, check mission control.");
        }

        RoverInterfaceResponse roverInterfaceResponse = executeCommand(moveRoverRequest);
        updateRoverStatus(roverInterfaceResponse);

        RoverResponse roverResponse = new RoverResponse(
                rover.getName(),
                formatLocation(rover.getDirection(), roverInterfaceResponse.getHorizontalPosition(), roverInterfaceResponse.getVerticalPosition()),
                moveRoverRequest.getCommand(),
                "",
                true
        );

        return roverResponsePresenter.prepareSuccessResponse(roverResponse);
    }

    private String formatLocation(Direction direction, Integer horizontalPosition, Integer verticalPosition) {
        return String.format("%d:%d:%s", horizontalPosition, verticalPosition, direction.getValue());
    }

    private RoverInterfaceResponse executeCommand(MoveRoverRequest moveRoverRequest) {
        RoverInterfaceRequest roverInterfaceRequest = new CommonRoverInterfaceRequest(
                moveRoverRequest.getRoverName(),
                moveRoverRequest.getCommand()
        );
        return roverInterfaceGateway.command(roverInterfaceRequest);
    }

    private void updateRoverStatus(RoverInterfaceResponse roverInterfaceResponse) {
        RoverRequestDS roverRequestDS = new RoverRequestDSImpl(
                roverInterfaceResponse.getRoverName(),
                roverInterfaceResponse.getDirection(),
                roverInterfaceResponse.getHorizontalPosition(),
                roverInterfaceResponse.getVerticalPosition(),
                LocalDateTime.now()
        );
        roverStatusDSGateway.updateStatus(roverRequestDS);
    }

}
