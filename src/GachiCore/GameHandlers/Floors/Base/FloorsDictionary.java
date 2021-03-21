package GachiCore.GameHandlers.Floors.Base;

import java.util.HashMap;
import java.util.Map;

public class FloorsDictionary {
    private final Map<Integer, FloorBuilder> floors = new HashMap<>();
    {

    }
    private static final FloorsDictionary instance = new FloorsDictionary();
    private FloorsDictionary(){}
    public static FloorsDictionary getInstance(){ return instance; }
    public FloorBuilder getFloor(int numberOfFloor){
        return floors.getOrDefault(numberOfFloor, null);
    }
}
