package GachiCore.GameHandlers;

import GachiCore.GameHandlers.Floors.Base.FloorBuilder;
import GachiCore.GameHandlers.Floors.Base.FloorsDictionary;

public class FloorHandler {
    private static final FloorHandler floorHandler = new FloorHandler();
    private final FloorsDictionary floorsDictionary = FloorsDictionary.getInstance();
    private GachiHandler gachiHandler = GachiHandler.getInstance();
    private int numberOfFloor = 1;
    private FloorHandler(){}

    public static FloorHandler getInstance(){ return floorHandler; }

    public void setFloor(int number){ numberOfFloor = number; }

    public int getNumberOfFloor() {
        return numberOfFloor;
    }

    public Floor getCurrentFloor(){
        FloorBuilder floorBuilder = floorsDictionary.getFloor(numberOfFloor);
        if(floorBuilder == null) return null;
        return floorBuilder.getFloor();
    }

    public Floor getNextFloor(){
        numberOfFloor++;
        return getCurrentFloor();
    }
}
