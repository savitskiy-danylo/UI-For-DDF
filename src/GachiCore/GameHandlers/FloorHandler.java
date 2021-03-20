package GachiCore.GameHandlers;

import GachiCore.GameHandlers.Floors.FirstFloorLeft;
import GachiCore.GameHandlers.Floors.FirstFloorRight;

public class FloorHandler {
    private static final FloorHandler floorHandler = new FloorHandler();
    private GachiHandler gachiHandler = GachiHandler.getInstance();
    private FloorHandler(){}

    public static FloorHandler getInstance(){ return floorHandler; }

    public Floor getLeftSide(){
        return new FirstFloorLeft().getFloor();
    }

    public Floor getRightSide(){
        return new FirstFloorRight().getFloor();
    }
}
