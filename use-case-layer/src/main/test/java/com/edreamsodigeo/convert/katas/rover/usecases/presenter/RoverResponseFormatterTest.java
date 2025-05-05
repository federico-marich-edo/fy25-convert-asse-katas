package com.edreamsodigeo.convert.katas.rover.usecases.presenter;

import com.edreamsodigeo.convert.katas.rover.model.response.RoverResponse;
import com.edreamsodigeo.convert.katas.rover.usecases.exception.RoverResponseException;
import com.edreamsodigeo.convert.katas.rover.usecases.presenter.impl.RoverResponseFormatter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RoverResponseFormatterTest {

    private RoverResponseFormatter roverResponseFormatter;

    @BeforeMethod
    public void setUp() {
        roverResponseFormatter = new RoverResponseFormatter();
    }

    @Test
    public void testPrepareSuccessResponse() {
        // Given
        RoverResponse response = new RoverResponse("Rover1", "3:0:N", "MMM", "", true);

        // When
        RoverResponse formattedResponse = roverResponseFormatter.prepareSuccessResponse(response);

        // Then
        assertEquals(formattedResponse.getLastCommand(), "Command: MMM");
        assertEquals(formattedResponse.getLocation(), "Location: 3:0:N");
    }

    @Test(expectedExceptions = RoverResponseException.class)
    public void testPrepareErrorResponse() {
        // Given
        String errorMessage = "Rover not found";

        // When
        roverResponseFormatter.prepareErrorResponse(errorMessage);
    }

}