package com.edreamsodigeo.convert.katas.rover.model.request;

public class DeployRoverRequest {

    private final String roverName;
    private final String direction;
    private final int horizontalPosition;
    private final int verticalPosition;

    public DeployRoverRequest(String roverName, String direction, int horizontalPosition, int verticalPosition) {
        this.roverName = roverName;
        this.direction = direction;
        this.horizontalPosition = horizontalPosition;
        this.verticalPosition = verticalPosition;
    }

    public String getRoverName() {
        return roverName;
    }

    public String getDirection() {
        return direction;
    }

    public int getHorizontalPosition() {
        return horizontalPosition;
    }

    public int getVerticalPosition() {
        return verticalPosition;
    }
}
