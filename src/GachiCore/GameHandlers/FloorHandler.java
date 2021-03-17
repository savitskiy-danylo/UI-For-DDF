package GachiCore.GameHandlers;

public class FloorHandler {
    private static final FloorHandler floorHandler = new FloorHandler();
    private GachiHandler gachiHandler = GachiHandler.getInstance();
    private FloorHandler(){}

    public static FloorHandler getInstance(){ return floorHandler; }

    public Floor getLeftSide(){
    }

    public Floor getRightSide(){

    }
}
