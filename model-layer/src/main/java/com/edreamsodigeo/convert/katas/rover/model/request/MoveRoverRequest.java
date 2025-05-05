package com.edreamsodigeo.convert.katas.rover.model.request;

public class MoveRoverRequest {
    private String roverName;
    private String command;

    public MoveRoverRequest(String roverName, String command) {
        this.roverName = roverName;
        this.command = command;
    }

    public String getRoverName() {
        return roverName;
    }
    public String getCommand() {
        return command;
    }

    public void setRoverName(String roverName) {
        this.roverName = roverName;
    }
    public void setCommand(String command) {
        this.command = command;
    }


}
