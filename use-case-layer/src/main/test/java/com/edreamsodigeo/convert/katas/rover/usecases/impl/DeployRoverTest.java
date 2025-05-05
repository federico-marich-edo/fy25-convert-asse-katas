package com.edreamsodigeo.convert.katas.rover.usecases.impl;

import com.edreamsodigeo.convert.katas.rover.entities.Direction;
import com.edreamsodigeo.convert.katas.rover.entities.Rover;
import com.edreamsodigeo.convert.katas.rover.entities.factory.RoverFactory;
import com.edreamsodigeo.convert.katas.rover.model.request.DeployRoverRequest;
import com.edreamsodigeo.convert.katas.rover.model.response.RoverResponse;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.RoverRegisterDSGateway;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.RoverRequestDS;
import com.edreamsodigeo.convert.katas.rover.usecases.presenter.RoverResponsePresenter;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class DeployRoverTest {

    private DeployRover deployRover;
    @Mock
    private RoverRegisterDSGateway roverRegisterDSGateway;
    @Mock
    private RoverFactory roverFactory;
    @Mock
    private RoverResponsePresenter roverResponsePresenter;
    @Mock
    private Rover rover;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.openMocks(this);
        deployRover = new DeployRover(roverRegisterDSGateway, roverFactory, roverResponsePresenter);
    }

    @Test
    public void givenARoverDeploymentRequestWhenWeCallToDeployItThenTheRoverIsDeployedSuccessfully() {
        DeployRoverRequest request = new DeployRoverRequest("Rover1", "N", 0, 0);
        givenASuccessfullyRoverRegistration(request);
        ArgumentCaptor<RoverRequestDS> captor = ArgumentCaptor.forClass(RoverRequestDS.class);

        RoverResponse response = deployRover.deploy(request);

        assertNotNull(response);
        assertEquals(response.getName(), "Rover1");
        assertEquals(response.getLocation(), "N:0:0");
        assertTrue(response.isSuccess());
        verify(roverRegisterDSGateway).save(any(RoverRequestDS.class));
        verify(roverRegisterDSGateway).save(captor.capture());
        RoverRequestDS savedData = captor.getValue();
        assertEquals(savedData.getName(), "Rover1");
        assertEquals(savedData.getDirection(), "N");
        assertEquals(savedData.getHorizontalPosition(), 0);
        assertEquals(savedData.getVerticalPosition(), 0);
        assertTrue(savedData.isActive());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void givenAnExistingRoverDeploymentRequestWhenWeCallToDeployItThenAnExceptionIsThrown() {
        // Given
        DeployRoverRequest request = new DeployRoverRequest("Rover1", "E", 3, 0);
        givenASuccessfullyRoverRegistration(request);
        when(roverRegisterDSGateway.existsByName(request.getRoverName())).thenReturn(true);

        deployRover.deploy(request);
    }


    private void givenASuccessfullyRoverRegistration(DeployRoverRequest request) {
        when(roverRegisterDSGateway.existsByName(request.getRoverName())).thenReturn(false);
        when(roverFactory.create(request.getRoverName(), request.getDirection(), request.getHorizontalPosition(), request.getVerticalPosition(), true)).thenReturn(rover);
        when(roverResponsePresenter.prepareSuccessResponse(any(RoverResponse.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(rover.getName()).thenReturn(request.getRoverName());
        when(rover.getDirection()).thenReturn(Direction.fromValue(request.getDirection()));
        when(rover.getHorizontalPosition()).thenReturn(0);
        when(rover.getVerticalPosition()).thenReturn(0);
        when(rover.isActive()).thenReturn(true);
    }


}