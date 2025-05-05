package com.edreamsodigeo.convert.katas.rover.model.response;

public class RoverResponse {

    private String name;
    private String location;
    private String lastCommand;
    private String errorMessage;
    private boolean success;

    public RoverResponse(String name, String location, String lastCommand, String errorMessage, boolean success) {
        this.name = name;
        this.location = location;
        this.lastCommand = lastCommand;
        this.errorMessage = errorMessage;
        this.success = success;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getLastCommand() {
        return lastCommand;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setLastCommand(String lastCommand) {
        this.lastCommand = lastCommand;
    }
    public void setLocation(String location) {
        this.location = location;
    }
}
