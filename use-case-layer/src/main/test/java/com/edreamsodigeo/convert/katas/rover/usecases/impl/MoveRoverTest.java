package com.edreamsodigeo.convert.katas.rover.usecases.impl;

import com.edreamsodigeo.convert.katas.rover.entities.Direction;
import com.edreamsodigeo.convert.katas.rover.entities.Rover;
import com.edreamsodigeo.convert.katas.rover.entities.factory.RoverFactory;
import com.edreamsodigeo.convert.katas.rover.model.request.MoveRoverRequest;
import com.edreamsodigeo.convert.katas.rover.model.response.RoverResponse;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.RoverInterfaceGateway;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.RoverRegisterDSGateway;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.RoverStatusDSGateway;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.RoverResponseDS;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverInterfaceRequest;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverInterfaceResponse;
import com.edreamsodigeo.convert.katas.rover.usecases.presenter.RoverResponsePresenter;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class MoveRoverTest {

    @Mock
    private RoverStatusDSGateway roverStatusDSGateway;
    @Mock
    private RoverRegisterDSGateway roverRegisterDSGateway;
    @Mock
    private RoverInterfaceGateway roverInterfaceGateway;
    @Mock
    private RoverFactory roverFactory;
    @Mock
    private RoverResponsePresenter roverResponsePresenter;
    @Mock
    private RoverResponseDS roverResponseDS;
    @Mock
    private RoverInterfaceResponse interfaceResponse;
    @Mock
    private Rover rover;

    private MoveRover moveRover;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.openMocks(this);
        moveRover = new MoveRover(roverStatusDSGateway, roverInterfaceGateway, roverRegisterDSGateway, roverFactory, roverResponsePresenter);
    }

    @Test
    public void givenARoverMovementRequestWhenWeCallToDeployItThenTheRoverIsMovedSuccessfully() {
        // Given
        MoveRoverRequest request = new MoveRoverRequest("Rover1", "MMRMMLM");
        givenASuccessfullyRoverMovement(request);

        // When
        RoverResponse response = moveRover.move(request);

        // Then
        assertNotNull(response);
        assertEquals(response.getName(), "Rover1");
        assertEquals(response.getLocation(), "2:3:N");
        assertEquals(response.getLastCommand(), "MMRMMLM");
        verify(roverStatusDSGateway).updateStatus(any());
    }

    private void givenASuccessfullyRoverMovement(MoveRoverRequest request) {
        when(roverRegisterDSGateway.existsByName(request.getRoverName())).thenReturn(true);
        when(roverRegisterDSGateway.findByName("Rover1")).thenReturn(roverResponseDS);
        when(roverInterfaceGateway.command(any(RoverInterfaceRequest.class))).thenReturn(interfaceResponse);
        when(roverResponsePresenter.prepareSuccessResponse(any(RoverResponse.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(rover.getName()).thenReturn(request.getRoverName());
        when(rover.getDirection()).thenReturn(Direction.NORTH);
        when(rover.getHorizontalPosition()).thenReturn(0);
        when(rover.getVerticalPosition()).thenReturn(0);
        when(rover.isActive()).thenReturn(true);
        when(roverResponseDS.getName()).thenReturn(request.getRoverName());
        when(roverResponseDS.getDirection()).thenReturn("N");
        when(roverResponseDS.getHorizontalPosition()).thenReturn(0);
        when(roverResponseDS.getVerticalPosition()).thenReturn(0);
        when(roverResponseDS.isActive()).thenReturn(true);
        when(roverFactory.create(request.getRoverName(), "N", 0, 0, true)).thenReturn(rover);
        when(interfaceResponse.getHorizontalPosition()).thenReturn(2);
        when(interfaceResponse.getVerticalPosition()).thenReturn(3);
        when(interfaceResponse.getDirection()).thenReturn("N");
    }

}